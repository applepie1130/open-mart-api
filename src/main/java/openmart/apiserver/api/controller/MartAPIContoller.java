
package openmart.apiserver.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
		@ApiImplicitParam(name = "latitude", value = "위도", required = true, dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "longitude", value = "경도", required = true, dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "martName", value = "마트명", required = false, dataType = "string", paramType = "query")
	})
	public List<Map> findMartHolidayInfos (@ModelAttribute MartSearchCriteria martSearchCriteria) {
		martService.findMartHolidayInfos(martSearchCriteria);
		return null;
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
