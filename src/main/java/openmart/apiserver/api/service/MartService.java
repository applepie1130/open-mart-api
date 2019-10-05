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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import openmart.apiserver.api.comonent.FileCommonUtils;
import openmart.apiserver.api.model.criteria.MartSearchCriteria;
import openmart.apiserver.api.model.tuple.MartHolidayDetailTuple;
import openmart.apiserver.api.model.tuple.emart.EmartResponseTuple;
import openmart.apiserver.api.model.type.EmartConstants;

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
		
	}
	
	/**
	 * 마트별 휴일정보데이터 생성  
	 */
	public void saveMartHolidayInfos() {
		
		// 이마트
		Map<String, MartHolidayDetailTuple> emartHolidayInfo = this.getEmartHolidayInfo();
		fileCommonUtils.writeFileToJSONMap(EmartConstants.filePath, emartHolidayInfo);

		// 롯데마트 
		Map<String, MartHolidayDetailTuple> lotteMartHolidayInfo = this.getLotteMartHolidayInfo();
		fileCommonUtils.writeFileToJSONMap("/tank0/holidays/lotte.json", lotteMartHolidayInfo);
		
		// 홈플러스
		Map<String, MartHolidayDetailTuple> homePlusMartHolidayInfo = this.getHomePlusMartHolidayInfo();
		fileCommonUtils.writeFileToJSONMap("/tank0/holidays/homeplus.json", homePlusMartHolidayInfo);
	
		// 코스트코어
		Map<String, MartHolidayDetailTuple> costcoMartHolidayInfo = this.getCostcoMartHolidayInfo();
		fileCommonUtils.writeFileToJSONMap("/tank0/holidays/costco.json", costcoMartHolidayInfo);
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
				
				if (log.isErrorEnabled()) {
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
							//holidayYYYYMMDD.add(t.get("HOLIDAY_DAY3_YMD"));
							
							result.put(telNo, MartHolidayDetailTuple.builder()
									.holidayYYYYMMDD(holidayYYYYMMDD)
									.telno(telNo)
									.region(t.get("AREA"))
									.martName(EmartConstants.name)
									.martFullName(t.get("NAME"))
									.build());
						}
					});
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
			System.out.println(result);
		});
		
		return result;
	}
	
	/**
	 * 롯데마트 휴일정보 생성을 위한 조회
	 * @return
	 */
	private Map<String, MartHolidayDetailTuple> getLotteMartHolidayInfo() {
		return null;
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
