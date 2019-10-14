package openmart.apiserver.api.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import openmart.apiserver.api.comonent.FileCommonUtils;
import openmart.apiserver.api.model.criteria.MartSearchCriteria;
import openmart.apiserver.api.model.tuple.MartHolidayInfosTuple;
import openmart.apiserver.api.model.tuple.admin.MartHolidayDetailTuple;
import openmart.apiserver.api.model.tuple.emart.EmartResponseTuple;
import openmart.apiserver.api.model.tuple.lottemart.LotteMartDetailResponseTuple;
import openmart.apiserver.api.model.tuple.lottemart.LotteMartResponseTuple;
import openmart.apiserver.api.model.tuple.lottemart.LotteMartSubResponseTuple;
import openmart.apiserver.api.model.tuple.naver.NaverPlaceResponseTuple;
import openmart.apiserver.api.model.tuple.naver.NaverSearchResponseTuple;
import openmart.apiserver.api.model.tuple.naver.NaverSearchTuple;
import openmart.apiserver.api.model.type.CostcoConstants;
import openmart.apiserver.api.model.type.EmartConstants;
import openmart.apiserver.api.model.type.ExcludedMartNameConstants;
import openmart.apiserver.api.model.type.HomeplusConstants;
import openmart.apiserver.api.model.type.LotteMartConstants;
import openmart.apiserver.api.model.type.NaverConstants;

@Slf4j
@Service
public class MartService {
	
	@Autowired
	private FileCommonUtils fileCommonUtils;
	
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
			.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
			.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
	
	
	/**
	 * 위치정보를 판단하여, 주변 마트정보 조회<p>
	 * 네이버 API 위치정보 판단<p>
	 * 사전생성된 마트별 휴일정보 전화번호를 통한 조회
	 */
	public List<MartHolidayInfosTuple> findMartHolidayInfos(MartSearchCriteria martSearchCriteria) {
		
		String latitude = martSearchCriteria.getLatitude();
		String longitude = martSearchCriteria.getLongitude();
		String martName = martSearchCriteria.getMartName();
		
		// Validation
		if (StringUtils.isBlank(longitude) || StringUtils.isBlank(latitude)) {
			// TODO:에러처리
		}
		
		List<NaverSearchTuple> searchResult = new ArrayList<>(); 
		
		/**
		 * 위치기반 마트정보 조회 (Naver Place Search API)
		 */
		if (StringUtils.isNotBlank(martName)) { /** 마트 이름이 있는경우 **/
			try {
				martName = URLDecoder.decode(martName, "UTF-8");
			} catch (Exception e) {
				if (log.isErrorEnabled()) {
					log.error(e.getMessage(), e);
				}
			}
			
			// 위치기반 네이버API호출
			searchResult = this.callNaverApi(latitude, longitude, martName);
			
		} else { /** 마트이름이 없는경우, 대형마트 정보 전체 조회 **/ 
			// 위치기반 네이버API호출
			searchResult.addAll(this.callNaverApi(latitude,longitude, EmartConstants.name));
			searchResult.addAll(this.callNaverApi(latitude,longitude, LotteMartConstants.name));
			searchResult.addAll(this.callNaverApi(latitude,longitude, HomeplusConstants.name));
			searchResult.addAll(this.callNaverApi(latitude,longitude, CostcoConstants.name));
		}
		
		/**
		 * 마트별 휴일정보조회
		 */
		// 이마트
		Map<String, Object> emartHolidaysInfo = fileCommonUtils.readFileFromJSONMap(EmartConstants.filePath);
		
		// 롯데마트
		Map<String, Object> looteMartHolidaysInfo = fileCommonUtils.readFileFromJSONMap(LotteMartConstants.filePath);
		
		List<MartHolidayInfosTuple> result = new ArrayList<>();
		List<MartHolidayInfosTuple> sortedResult = new ArrayList<>();
		
		searchResult.forEach(s -> {
			String key = s.getTelNo();
			
			Map<String, Object> emartObject = (Map<String, Object>) emartHolidaysInfo.get(key);
			Map<String, Object> lotterMartObject = (Map<String, Object>) looteMartHolidaysInfo.get(key);
			MartHolidayDetailTuple emartTuple = null;
			MartHolidayDetailTuple lotterMartTuple = null;
			
			if (emartObject != null) {
				emartTuple = OBJECT_MAPPER.convertValue(emartObject, MartHolidayDetailTuple.class);
			}
			
			if (lotterMartObject != null) {
				lotterMartTuple = OBJECT_MAPPER.convertValue(lotterMartObject, MartHolidayDetailTuple.class);
			}
			
			if (log.isDebugEnabled()) {
				log.debug("emartTuple : {}", emartTuple);
				log.debug("lotterMartTuple : {}", lotterMartTuple);
			}
			
			String holidaysInfo = "";
			if (emartTuple != null) {
				holidaysInfo = emartTuple.getHolidayInfos();
			} else if (lotterMartTuple != null) {
				holidaysInfo = lotterMartTuple.getHolidayInfos();
			} else if (s.getName().contains(HomeplusConstants.name) || s.getName().contains(CostcoConstants.name)){
				holidaysInfo = "매월 둘째, 넷째 일요일 의무 휴무";
			} else {
				return;
			}
			
			BigDecimal distance = s.getDistance();
			String displayDistance = "";
			if (distance.compareTo(BigDecimal.valueOf(1000)) >= 0) {
				displayDistance = distance.divide(BigDecimal.valueOf(1000)).setScale(2, RoundingMode.CEILING) + "km";
			} else {
				displayDistance = distance.setScale(0, RoundingMode.CEILING) + "m";
			}
			
			result.add(MartHolidayInfosTuple.builder()
					.name(s.getName())
					.telNo(s.getTelNo())
					.address(s.getAddress())
					.distance(s.getDistance())
					.displayDistance(displayDistance)
					.holidaysInfo(holidaysInfo)
					.latitude(s.getLatitude())
					.longitiude(s.getLongitiude())
					.build());
		});
		
		// 거리순으로 sort
		if (CollectionUtils.isNotEmpty(result)) {
			sortedResult = result.stream()
								 .sorted(Comparator.comparing(MartHolidayInfosTuple::getDistance))
								 .collect(Collectors.toList());
		}
		
		if (log.isDebugEnabled()) {
			log.debug("### sortedResult ###");
			log.debug("{}", sortedResult);
		}
		
		return sortedResult;
	}
	
	
	/**
	 * 네이버 조회API 호출
	 * @param latitude
	 * @param longitude
	 * @param martName
	 * @return
	 */
	private List<NaverSearchTuple> callNaverApi(String latitude, String longitude, String martName) {
		
		List<NaverSearchTuple> result = new ArrayList<>();
		
		//TODO : 확인용변수
		Map<String, Object> before = new HashMap<String, Object>();
		Map<String, Object> after = new HashMap<String, Object>();
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			UriComponents mainBuilder = UriComponentsBuilder.fromHttpUrl(NaverConstants.apiUrl)
															.queryParam("query", martName)
															.queryParam("coordinate", longitude+","+latitude) // "경도,위도" 형식
															.build(false);
			
			String uri = mainBuilder.toUriString();
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("X-NCP-APIGW-API-KEY-ID", NaverConstants.X_NCP_APIGW_API_KEY_ID);
			headers.add("X-NCP-APIGW-API-KEY", NaverConstants.X_NCP_APIGW_API_KEY);
			
			final HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
			ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);        
			String body = response.getBody();
			
			if ( StringUtils.isBlank(body) ) {
				return null;
			}
			
			NaverSearchResponseTuple naverSearchResponseTuple = OBJECT_MAPPER.readValue(body, NaverSearchResponseTuple.class);
			
			if (naverSearchResponseTuple != null && StringUtils.equals(naverSearchResponseTuple.getStatus(), HttpStatus.OK.getReasonPhrase())) {
				List<NaverPlaceResponseTuple> places = naverSearchResponseTuple.getPlaces();
				
				if (CollectionUtils.isNotEmpty(places)) {
					places.forEach(s -> {
						String name = s.getName();
						String address = s.getRoad_address();
						String telNo = StringUtils.replaceChars(s.getPhone_number(), "-", "");
						BigDecimal distance = s.getDistance();
						
						before.put(telNo, Arrays.asList(name, address));
						
						if ( this.isApplicableName(name) ) {
							
							after.put(telNo, Arrays.asList(name, address));
							
							result.add(NaverSearchTuple.builder()
									.name(name)
									.address(address)
									.telNo(s.getPhone_number())
									.distance(distance)
									.latitude(s.getY())
									.longitiude(s.getX())
									.build());
						};
					});
				}
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		if (log.isDebugEnabled()) {
			log.debug("### 정제 전 응답 ###");
			log.debug("{}", before);
			log.debug("### 정제 후 응답 ###");
			log.debug("{}", after);
		}
		
		return result;
	}
	
	/**
	 * 마트명 중 제외처리되어야 할 명칭이 있는지 확인
	 * @param martName
	 * @return
	 */
	private Boolean isApplicableName(String martName) {
		boolean isExcludeAble = ExcludedMartNameConstants.excludedMartNames.stream()
										.filter(s -> StringUtils.contains(martName, s))
										.findFirst()
										.isPresent();
		boolean isIncludeAble = StringUtils.startsWithAny(martName, new String[] {EmartConstants.name, LotteMartConstants.name, HomeplusConstants.name, CostcoConstants.name});
		
		return (isExcludeAble == Boolean.FALSE && isIncludeAble == Boolean.TRUE); 
	}
	
	/**
	 * 마트별 휴일정보데이터 생성  
	 */
	public String saveMartHolidayInfos() {
		
		String resultCode = "OK";
		
		try {
			// 이마트
			Map<String, MartHolidayDetailTuple> emartHolidayInfo = this.getEmartHolidayInfo();
			fileCommonUtils.writeFileToJSONMap(EmartConstants.filePath, emartHolidayInfo);
			
			// 롯데마트 
			Map<String, MartHolidayDetailTuple> lotteMartHolidayInfo = this.getLotteMartHolidayInfo();
			fileCommonUtils.writeFileToJSONMap(LotteMartConstants.filePath, lotteMartHolidayInfo);
			
//			// 홈플러스
//			Map<String, MartHolidayDetailTuple> homePlusMartHolidayInfo = this.getHomePlusMartHolidayInfo();
//			fileCommonUtils.writeFileToJSONMap("/tank0/holidays/homeplus.json", homePlusMartHolidayInfo);
//			
//			// 코스트코어
//			Map<String, MartHolidayDetailTuple> costcoMartHolidayInfo = this.getCostcoMartHolidayInfo();
//			fileCommonUtils.writeFileToJSONMap("/tank0/holidays/costco.json", costcoMartHolidayInfo);
			
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(e.getMessage(), e);
			}
			resultCode = "ERROR";
		}
		
		return resultCode;
	}
	
	/**
	 * 이마트 휴일정보 생성을 위한 조회
	 * @return
	 */
	private Map<String, MartHolidayDetailTuple> getEmartHolidayInfo() throws ParseException {
		
		Map<String, MartHolidayDetailTuple> result = new HashMap<String, MartHolidayDetailTuple>();
		
		LocalDateTime currentDateTime = LocalDateTime.now();
		int year = currentDateTime.getYear();
		int month = currentDateTime.getMonthValue();
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		
		RestTemplate restTemplate = new RestTemplate();
		
		EmartConstants.areaCodeList.forEach(s -> {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			
			// A (서울:A, 인천:C, 경기:I, 대전:F, 세종:Q, 충청:N, 대구:D, 경상:J, 울산:G, 부산:B, 전라:L, 광주:E, 강원:H, 제주:P)
			MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
			param.add("areaCd", s);
			param.add("year", String.valueOf(year));
			param.add("month", String.valueOf(month));
			
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(param, headers);
			ResponseEntity<String> response = restTemplate.postForEntity(EmartConstants.apiUrl, request, String.class);
			String body = response.getBody();
			try {
				EmartResponseTuple readValue = OBJECT_MAPPER.readValue(body, new TypeReference<EmartResponseTuple>() {});
				
				if (log.isDebugEnabled()) {
					log.debug("#### EMART API RESULT ####");
					log.debug("{}", readValue);
					log.debug("#### EMART API RESULT ####");
				}

				if (readValue != null && CollectionUtils.isNotEmpty(readValue.getDateList())) {
					readValue.getDateList().forEach(t -> {
						String telNo = StringUtils.replaceChars(t.get("TEL"), "-", "");
						
						if (StringUtils.isNotBlank(telNo)) {
							List<String> holidayYYYYMMDD = new ArrayList<String>();
							
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
							SimpleDateFormat displayFormat = new SimpleDateFormat("MM/dd");
						
							Date date;
							String day1 = "";
							String day2 = "";
							
							try {
								date = dateFormat.parse(t.get("HOLIDAY_DAY1_YMD"));
								day1 = displayFormat.format(date);
							
								date = dateFormat.parse(t.get("HOLIDAY_DAY2_YMD"));
								day2 = displayFormat.format(date);
							} catch (ParseException e) {
								log.error(e.getMessage(), e);
							}
							
							holidayYYYYMMDD.add(day1);
							holidayYYYYMMDD.add(day2);
							
							result.put(telNo, MartHolidayDetailTuple.builder()
									.holidayYYYYMMDD(holidayYYYYMMDD)
									.holidayInfos(day1 + ", " + day2)
									.telno(telNo)
									.region(t.get("AREA"))
									.martName(EmartConstants.name)
									.martFullName(EmartConstants.name + " " +  t.get("NAME"))
									.regionMartName(t.get("NAME"))
									.build());
						}
					});
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		});
		
		return result;
	}
	
	/**
	 * 롯데마트 휴일정보 생성을 위한 조회
	 * @return
	 */
	private Map<String, MartHolidayDetailTuple> getLotteMartHolidayInfo() {
		
		Map<String, MartHolidayDetailTuple> result = new HashMap<String, MartHolidayDetailTuple>();
		
		RestTemplate restTemplate = new RestTemplate();
		
		LotteMartConstants.regionCodeList.forEach(s -> {
			UriComponentsBuilder mainBuilder = UriComponentsBuilder.fromHttpUrl(LotteMartConstants.apiUrl)
					   										   .queryParam("regionCode", s);
			
			String response = restTemplate.getForObject(mainBuilder.toUriString(), String.class);
			
			if (log.isDebugEnabled()) {
				log.debug("#### LotteMart API RESULT ####");
				log.debug("{}", response);
				log.debug("#### LotteMart API RESULT ####");
			}
			
			try {
				LotteMartResponseTuple lotteMartResponseTuple = OBJECT_MAPPER.readValue(response, new TypeReference<LotteMartResponseTuple>() {});

				if (lotteMartResponseTuple != null) {
					List<LotteMartDetailResponseTuple> list = lotteMartResponseTuple.getData();
					
					list.forEach(t -> {
						UriComponentsBuilder subApiBuilder = UriComponentsBuilder.fromHttpUrl(LotteMartConstants.subApiUrl)
																				 .queryParam("brnchCd", t.getBrnchCd());
						
						String subResponse = restTemplate.getForObject(subApiBuilder.toUriString(), String.class);

						try {
							LotteMartSubResponseTuple lotteMartSubResponseTuple = (LotteMartSubResponseTuple) OBJECT_MAPPER.readValue(subResponse, new TypeReference<LotteMartSubResponseTuple>() {});
							if (log.isDebugEnabled()) {
								log.debug("#### LotteMart API RESULT ####");
								log.debug("{}", subResponse);
								log.debug("#### LotteMart API RESULT ####");
							}
							
							if (lotteMartSubResponseTuple != null ) {
								LotteMartDetailResponseTuple lotteMartDetailResponseTuple = lotteMartSubResponseTuple.getData();
								
								if (lotteMartDetailResponseTuple != null && StringUtils.isNotBlank(lotteMartDetailResponseTuple.getRepTelNo())) {
									String telNo = StringUtils.replaceChars(lotteMartDetailResponseTuple.getRepTelNo(), "-", "");
									if (StringUtils.isNotBlank(telNo)) {
										result.put(telNo, MartHolidayDetailTuple.builder()
												.holidayInfos(lotteMartDetailResponseTuple.getHoliDate()).telno(telNo)
												.region(null)
												.martName(LotteMartConstants.name)
												.regionMartName(lotteMartDetailResponseTuple.getStrNm())
												.martFullName(LotteMartConstants.name + " " + lotteMartDetailResponseTuple.getStrNm())
												.build());
									}
								}
								
							}
						} catch (Exception e) {
							log.error(e.getMessage(), e);
						}
					});
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		});
		
		return result;
	}
	
	/**
	 * 홈플러스 휴일정보 생성을 위한 조회
	 * @return
	 */
	private Map<String, MartHolidayDetailTuple> getHomePlusMartHolidayInfo() {
		return null;
	}
	
	/**
	 * 코스트코 휴일정보 생성을 위한 조회
	 * @return
	 */
	private Map<String, MartHolidayDetailTuple> getCostcoMartHolidayInfo() {
		return null;
	}
}
