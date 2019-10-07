package openmart.apiserver.api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import openmart.apiserver.api.comonent.FileCommonUtils;
import openmart.apiserver.api.model.criteria.MartSearchCriteria;
import openmart.apiserver.api.model.tuple.MartHolidayDetailTuple;
import openmart.apiserver.api.model.tuple.emart.EmartResponseTuple;
import openmart.apiserver.api.model.tuple.lottemart.LotteMartDetailResponseTuple;
import openmart.apiserver.api.model.tuple.lottemart.LotteMartResponseTuple;
import openmart.apiserver.api.model.tuple.lottemart.LotteMartSubResponseTuple;
import openmart.apiserver.api.model.tuple.naver.NaverSearchResponseTuple;
import openmart.apiserver.api.model.type.CostcoConstants;
import openmart.apiserver.api.model.type.EmartConstants;
import openmart.apiserver.api.model.type.HomeplusConstants;
import openmart.apiserver.api.model.type.LotteMartConstants;
import openmart.apiserver.api.model.type.NaverConstants;

@Slf4j
@Service
public class MartService {
	
	@Autowired
	private FileCommonUtils fileCommonUtils;
	
	/**
	 * 위치정보를 판단하여, 주변 마트정보 조회<p>
	 * 네이버 API 위치정보 판단<p>
	 * 사전생성된 마트별 휴일정보 전화번호를 통한 조회
	 */
	public void findMartHolidayInfos(MartSearchCriteria martSearchCriteria) {
		
		String latitude = martSearchCriteria.getLatitude();
		String longitude = martSearchCriteria.getLongitude();
		String martName = martSearchCriteria.getMartName();
		
		// Validation
		if (StringUtils.isBlank(longitude) || StringUtils.isBlank(latitude)) {
			// TODO:에러처리
		}
		
		NaverSearchResponseTuple naverSearchResponseTuple = this.callNaverApi(latitude, longitude, martName);
		
//		if (StringUtils.isNotBlank(martName)) {
//			// 마트 이름이 있는경우
//			
//			// 위치기반 네이버API호출
//			NaverSearchResponseTuple naverSearchResponseTuple = this.callNaverApi(latitude, longitude, martName);
//			
//			
//		} else { 
//			// 마트이름이 없는경우, 대형마트 정보 전체 조회
//			
//			// 위치기반 네이버API호출
//			NaverSearchResponseTuple emartSearchResponseTuple = this.callNaverApi(longitude, latitude, EmartConstants.name);
//			NaverSearchResponseTuple lotteSearchMartResponseTuple = this.callNaverApi(longitude, latitude, LotteMartConstants.name);
//			NaverSearchResponseTuple homeplusSearchResponseTuple = this.callNaverApi(longitude, latitude, HomeplusConstants.name);
//			NaverSearchResponseTuple costcoSearchResponseTuple = this.callNaverApi(longitude, latitude, CostcoConstants.name);
//		}
	}
	
	/**
	 * 네이버 조회API 호출
	 * @param latitude
	 * @param longitude
	 * @param martName
	 * @return
	 */
	private NaverSearchResponseTuple callNaverApi(String latitude, String longitude, String martName) {
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		RestTemplate restTemplate = new RestTemplate();
		UriComponentsBuilder mainBuilder = UriComponentsBuilder.fromHttpUrl(NaverConstants.apiUrl)
															   .queryParam("query", martName)
															   .queryParam("coordinate", longitude+","+latitude) // "경도,위도" 형식
															   .queryParam("orderBy","popularity");
		
		String uri = mainBuilder.toUriString();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-NCP-APIGW-API-KEY-ID", NaverConstants.X_NCP_APIGW_API_KEY_ID);
		headers.add("X-NCP-APIGW-API-KEY", NaverConstants.X_NCP_APIGW_API_KEY);
		
		ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<Object>(headers), String.class);
		log.debug("{}", response);
		
		return null;
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
	private Map<String, MartHolidayDetailTuple> getEmartHolidayInfo() {
		
		Map<String, MartHolidayDetailTuple> result = new HashMap<String, MartHolidayDetailTuple>();
		
		LocalDateTime currentDateTime = LocalDateTime.now();
		int year = currentDateTime.getYear();
		int month = currentDateTime.getMonthValue();
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
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
				EmartResponseTuple readValue = mapper.readValue(body, new TypeReference<EmartResponseTuple>() {});
				
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
							holidayYYYYMMDD.add(t.get("HOLIDAY_DAY1_YMD"));
							holidayYYYYMMDD.add(t.get("HOLIDAY_DAY2_YMD"));
							
							result.put(telNo, MartHolidayDetailTuple.builder()
									.holidayYYYYMMDD(holidayYYYYMMDD)
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
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

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
				LotteMartResponseTuple lotteMartResponseTuple = mapper.readValue(response, new TypeReference<LotteMartResponseTuple>() {});

				if (lotteMartResponseTuple != null) {
					List<LotteMartDetailResponseTuple> list = lotteMartResponseTuple.getData();
					
					list.forEach(t -> {
						UriComponentsBuilder subApiBuilder = UriComponentsBuilder.fromHttpUrl(LotteMartConstants.subApiUrl)
																				 .queryParam("brnchCd", t.getBrnchCd());
						
						String subResponse = restTemplate.getForObject(subApiBuilder.toUriString(), String.class);

						try {
							LotteMartSubResponseTuple lotteMartSubResponseTuple = (LotteMartSubResponseTuple) mapper.readValue(subResponse, new TypeReference<LotteMartSubResponseTuple>() {});
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
	
	
//	public static void main(String[] args) {
//		LocalDateTime currentDateTime = LocalDateTime.now();
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
//		
//		Calendar c = Calendar.getInstance();
//		Integer year = currentDateTime.getYear();
//		Integer month = currentDateTime.getMonthValue();
//		c.set(Calendar.YEAR, year);
//		c.set(Calendar.MONTH, month+1);
//		c.set(Calendar.WEEK_OF_MONTH, 3);
//		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
//		System.out.println(formatter.format(c.getTime()));
//		
//		c = Calendar.getInstance();
//		c.set(Calendar.YEAR, year);
//		c.set(Calendar.MONTH, month+1);
//		c.set(Calendar.WEEK_OF_MONTH, 5);
//		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
//		System.out.println(formatter.format(c.getTime()));
//	}
}
