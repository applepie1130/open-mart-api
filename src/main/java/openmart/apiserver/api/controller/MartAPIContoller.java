
package openmart.apiserver.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import openmart.apiserver.api.model.criteria.MartSearchCriteria;
import openmart.apiserver.api.model.tuple.MartHolidayInfosTuple;
import openmart.apiserver.api.model.tuple.MartHolidayResponseTuple;
import openmart.apiserver.api.service.MartService;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
		@ApiImplicitParam(name = "martName", value = "마트명", required = false, dataType = "string", paramType = "query", example = "이마트"),
		@ApiImplicitParam(name = "isHandsfree", value = "핸즈프리여부", required = false, dataType = "boolean", paramType = "query", example = "false")
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
			// 근처 마트정보로 재조회
			String searchName = martSearchCriteria.getMartName();
			martSearchCriteria.setMartName(null);
			searchMartList = martService.findMartHolidayInfos(martSearchCriteria);

			if (BooleanUtils.isTrue(martSearchCriteria.getIsHandsfree())) {
				String holidaysInfo = searchMartList.get(0).getHolidaysInfo();
				String name = searchMartList.get(0).getName();
				String distance = searchMartList.get(0).getDisplayDistance();
				message = searchName + "으로 검색된 결과가 없네요, 대신 가장 가까운 마트정보로 알려줄게요. "
						+ name + "의 쉬는날은 " + holidaysInfo + "이며, 현재 위치로부터 " + distance + " 거리에 있습니다.";
			} else {
				message = searchName + "으로 검색된 결과가 없네요, 대신 근처에 있는 마트정보를 알려줄게요.";
			}
			
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
	@ApiImplicitParams({
		@ApiImplicitParam(name = "YYYYMMDD", value = "기준일자", required = true, dataType = "string", paramType = "query", example = "20200201")
	})
	public String saveMartHolidayInfos (String YYYYMMDD) {
		return martService.saveMartHolidayInfos(YYYYMMDD);
	}
}
