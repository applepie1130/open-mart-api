
package openmart.apiserver.api.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import openmart.apiserver.api.model.criteria.MartSearchCriteria;
import openmart.apiserver.api.model.tuple.MartHolidayInfosTuple;
import openmart.apiserver.api.model.tuple.MartHolidayResponseTuple;
import openmart.apiserver.api.service.MartService;

@Api(tags = "MartAPIController", value = "마트정보 API", produces = "application/json")
@RestController
@RequestMapping(path = "/v1/mart", produces = "application/json")
public class MartAPIContoller {
	
	@Autowired
	private MartService martService;
	
	@GetMapping(path="/martHolidayInfos")
	@ApiOperation(
			httpMethod = "GET",
			value = "마트정보 조회",
			notes = "위치기반 주변 마트정보 조회, 특정마트명을 넘길경우 해당 마트정보로만 조회",
			response = Map.class
			)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "latitude", value = "위도", required = true, dataType = "string", paramType = "query", example = "37.4967885"),
		@ApiImplicitParam(name = "longitude", value = "경도", required = true, dataType = "string", paramType = "query", example = "127.0272884"),
		@ApiImplicitParam(name = "martName", value = "마트명", required = false, dataType = "string", paramType = "query", example = "이마트")
	})
	public MartHolidayResponseTuple findMartHolidayInfos (@ModelAttribute MartSearchCriteria martSearchCriteria) {
		
		if (StringUtils.isNotBlank(martSearchCriteria.getMartName())) {
			if (!martSearchCriteria.getMartName().contains("롯데마트") && 
					!martSearchCriteria.getMartName().contains("이마트") && 
					!martSearchCriteria.getMartName().contains("코스트코") && 
					!martSearchCriteria.getMartName().contains("홈플러스")) {
				martSearchCriteria.setMartName("");
			}
		}
		
		MartHolidayResponseTuple result = new MartHolidayResponseTuple();
		List<MartHolidayInfosTuple> searchMartList = martService.findMartHolidayInfos(martSearchCriteria);
		String message = "";
		
		if (StringUtils.isNotBlank(martSearchCriteria.getMartName()) && !CollectionUtils.isEmpty(searchMartList)) {
			if (searchMartList.size() > 1) {
				message = "어떤 마트에 대해 알려줄까요?";
				
			} else {
				String holidaysInfo = searchMartList.get(0).getHolidaysInfo();
				String name = searchMartList.get(0).getName();
				message = martSearchCriteria.getMartName() + "으로 검색된 결과입니다. " + name + "의 쉬는날은 " + holidaysInfo + "이네요.";	
			}
		} else if (StringUtils.isBlank(martSearchCriteria.getMartName()) && !CollectionUtils.isEmpty(searchMartList)) {
			message = "근처 마트로 검색된 결과입니다.";
			
		} else if (StringUtils.isNotBlank(martSearchCriteria.getMartName()) && CollectionUtils.isEmpty(searchMartList)) {
			message = martSearchCriteria.getMartName() + "으로 검색된 결과가 없네요, 대신 근처에 있는 마트정보를 알려줄게요.";
			// 근처 마트정보로 재조회
			martSearchCriteria.setMartName(null);
			searchMartList = martService.findMartHolidayInfos(martSearchCriteria);
			
		} else if (StringUtils.isBlank(martSearchCriteria.getMartName()) && !CollectionUtils.isEmpty(searchMartList)) {
			message = "어떤 마트에 대해 알려줄까요?";
			
		} else if (StringUtils.isBlank(martSearchCriteria.getMartName()) && CollectionUtils.isEmpty(searchMartList)) {
			message = "검색된 마트정보가 없네요.";
		}
		
		result.setSearchMartList(searchMartList);
		result.setMessage(message);
		
		return result; 
	}
	
	@PostMapping(path="/martHolidayInfos")
	@ApiOperation(
			httpMethod = "POST",
			value = "마트정보 데이터 생성",
			notes = "대형마트 정보에 대해 사전 데이터 생성",
			response = String.class
			)
	public String saveMartHolidayInfos () {
		return martService.saveMartHolidayInfos();
	}
}
