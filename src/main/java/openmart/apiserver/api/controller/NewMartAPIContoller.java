
package openmart.apiserver.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import openmart.apiserver.api.model.criteria.MartSearchCriteria;
import openmart.apiserver.api.model.tuple.MartHolidayInfosResponseTuple;
import openmart.apiserver.api.model.tuple.MartHolidayInfosTuple;
import openmart.apiserver.api.model.tuple.MartHolidayResponseTuple;
import openmart.apiserver.api.service.MartService2;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Mart api contoller.
 */
@Api(tags = "MartAPIController", value = "마트정보 API", produces = "application/json")
@RestController
@RequestMapping(path = "/v2/mart", produces = "application/json")
public class NewMartAPIContoller {

	private final MartService2 martService;

	@Autowired
	public NewMartAPIContoller(final MartService2 martService) {
		this.martService = martService;
	}
	/**
	 * Find mart holiday infos mart holiday response tuple.
	 *
	 * @param martSearchCriteria the mart search criteria
	 * @return the mart holiday response tuple
	 */
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
	public Mono<MartHolidayResponseTuple> findMartHolidayInfos (@ModelAttribute MartSearchCriteria martSearchCriteria) {

		if (StringUtils.isNotBlank(martSearchCriteria.getMartName())) {
			if (!martSearchCriteria.getMartName().contains("롯데마트") &&
					!martSearchCriteria.getMartName().contains("이마트") &&
					!martSearchCriteria.getMartName().contains("코스트코") &&
					!martSearchCriteria.getMartName().contains("홈플러스")) {
				martSearchCriteria.setMartName("");
			}
		}

		List<MartHolidayInfosResponseTuple> resultList = new ArrayList<>();
		Mono<List<MartHolidayInfosTuple>> searchMartList = martService.new_findMartHolidayInfos(martSearchCriteria);

		return searchMartList.flatMap(t->{
			MartHolidayResponseTuple result = new MartHolidayResponseTuple();
			t.forEach(s-> {
				resultList.add(s.of());
			});
			result.setSearchMartList(resultList);
			result.setMessage("");
			return Mono.just(result);
		});
	}
}
