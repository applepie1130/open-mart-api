
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
		Mono<List<MartHolidayInfosTuple>> searchMartList = martService.findMartHolidayInfos(martSearchCriteria);

		// Generate Message
		StringBuffer sbf = new StringBuffer();

		return searchMartList.flatMap(t->{
			MartHolidayResponseTuple result = new MartHolidayResponseTuple();

			if (StringUtils.isNotBlank(martSearchCriteria.getMartName()) && !CollectionUtils.isEmpty(t)) {
				if (t.size() > 1) {
					// message default
					sbf.append("어떤 마트에 대해 알려줄까요?");

					// handsFree
					String searchName = martSearchCriteria.getMartName();
					if (BooleanUtils.isTrue(martSearchCriteria.getIsHandsfree())) {
						String holidaysInfo = t.get(0).getHolidaysInfo();
						String name = t.get(0).getName();
						String distance = t.get(0).getDisplayDistance();
						Boolean isOpen = t.get(0).getIsOpen();

						sbf.setLength(0);
						sbf.append("가장 가까운 마트인 ");
						sbf.append(name);
						sbf.append("으로 검색된 결과입니다. ");
						sbf.append(name);
						sbf.append("의 쉬는날은 ");
						sbf.append(holidaysInfo);
						sbf.append("이며,");
						if (BooleanUtils.isTrue(isOpen)) {
							sbf.append(" 오늘은 정상 영업일 입니다.");
						} else {
							sbf.append(" 오늘은 휴무일 입니다.");
						}
						sbf.append(" 현재 위치로부터 ");
						sbf.append(distance);
						sbf.append(" 거리에 있습니다.");
					}
				} else {
					String searchName = martSearchCriteria.getMartName();
					String holidaysInfo = t.get(0).getHolidaysInfo();
					String name = t.get(0).getName();
					Boolean isOpen = t.get(0).getIsOpen();

					sbf.setLength(0);
					sbf.append(searchName);
					sbf.append("으로 검색된 결과입니다. ");
					sbf.append(name);
					sbf.append("의 쉬는날은 ");
					sbf.append(holidaysInfo);
					sbf.append("이며,");
					if (BooleanUtils.isTrue(isOpen)) {
						sbf.append(" 오늘은 정상 영업일 입니다.");
					} else {
						sbf.append(" 오늘은 휴무일 입니다.");
					}

					// handsFree
					if (BooleanUtils.isTrue(martSearchCriteria.getIsHandsfree())) {
						String distance = t.get(0).getDisplayDistance();

						sbf.setLength(0);
						sbf.append(searchName);
						sbf.append("으로 검색된 결과입니다. ");
						sbf.append(name);
						sbf.append("의 쉬는날은 ");
						sbf.append(holidaysInfo);
						sbf.append("이며,");
						if (BooleanUtils.isTrue(isOpen)) {
							sbf.append(" 오늘은 정상 영업일 입니다.");
						} else {
							sbf.append(" 오늘은 휴무일 입니다.");
						}
						sbf.append(" 현재 위치로부터 ");
						sbf.append(distance);
						sbf.append(" 거리에 있습니다.");
					}
				}

			} else if (StringUtils.isBlank(martSearchCriteria.getMartName()) && !CollectionUtils.isEmpty(t)) {
				sbf.setLength(0);
				sbf.append("근처 마트로 검색된 결과입니다.");

				// handsFree
				if (BooleanUtils.isTrue(martSearchCriteria.getIsHandsfree())) {
					String holidaysInfo = t.get(0).getHolidaysInfo();
					String name = t.get(0).getName();
					String distance = t.get(0).getDisplayDistance();
					Boolean isOpen = t.get(0).getIsOpen();

					sbf.setLength(0);
					sbf.setLength(0);
					sbf.append("가장 가까운 마트인 ");
					sbf.append(name);
					sbf.append("으로 검색된 결과입니다. ");
					sbf.append(name);
					sbf.append("의 쉬는날은 ");
					sbf.append(holidaysInfo);
					sbf.append("이며,");
					if (BooleanUtils.isTrue(isOpen)) {
						sbf.append(" 오늘은 정상 영업일 입니다.");
					} else {
						sbf.append(" 오늘은 휴무일 입니다.");
					}
					sbf.append(" 현재 위치로부터 ");
					sbf.append(distance);
					sbf.append(" 거리에 있습니다.");
				}

			} else if (StringUtils.isNotBlank(martSearchCriteria.getMartName()) && CollectionUtils.isEmpty(t)) {
				// 근처 마트정보로 재조회
				String searchName = martSearchCriteria.getMartName();
				martSearchCriteria.setMartName(null);

				sbf.setLength(0);
				sbf.append(searchName);
				sbf.append("으로 검색된 결과가 없네요, 대신 근처에 있는 마트정보를 알려줄게요.");

				// handsFree
				if (BooleanUtils.isTrue(martSearchCriteria.getIsHandsfree())) {
					String holidaysInfo = t.get(0).getHolidaysInfo();
					String name = t.get(0).getName();
					String distance = t.get(0).getDisplayDistance();
					Boolean isOpen = t.get(0).getIsOpen();

					sbf.setLength(0);
					sbf.append(searchName);
					sbf.append("으로 검색된 결과가 없네요, 대신 가장 가까운 마트정보로 알려줄게요. ");
					sbf.append(name);
					sbf.append("의 쉬는날은 ");
					sbf.append(holidaysInfo);
					sbf.append("이며,");
					if (BooleanUtils.isTrue(isOpen)) {
						sbf.append(" 오늘은 정상 영업일 입니다.");
					} else {
						sbf.append(" 오늘은 휴무일 입니다.");
					}
					sbf.append(" 현재 위치로부터 ");
					sbf.append(distance);
					sbf.append(" 거리에 있습니다.");
				}

			} else if (StringUtils.isBlank(martSearchCriteria.getMartName()) && !CollectionUtils.isEmpty(t)) {
				sbf.setLength(0);
				sbf.append("어떤 마트에 대해 알려줄까요?");

				// handsFree
				if (BooleanUtils.isTrue(martSearchCriteria.getIsHandsfree())) {
					String holidaysInfo = t.get(0).getHolidaysInfo();
					String name = t.get(0).getName();
					String distance = t.get(0).getDisplayDistance();
					Boolean isOpen = t.get(0).getIsOpen();

					sbf.setLength(0);
					sbf.append("가장 가까운 마트인 ");
					sbf.append(name);
					sbf.append("으로 검색된 결과입니다. ");
					sbf.append(name);
					sbf.append("의 쉬는날은 ");
					sbf.append(holidaysInfo);
					sbf.append("이며,");
					if (BooleanUtils.isTrue(isOpen)) {
						sbf.append(" 오늘은 정상 영업일 입니다.");
					} else {
						sbf.append(" 오늘은 휴무일 입니다.");
					}
					sbf.append(" 현재 위치로부터 ");
					sbf.append(distance);
					sbf.append(" 거리에 있습니다.");
				}

			} else if (StringUtils.isBlank(martSearchCriteria.getMartName()) && CollectionUtils.isEmpty(t)) {
				sbf.setLength(0);
				sbf.append("검색된 마트정보가 없네요.");
			}

			String message = sbf.toString();

			t.forEach(s-> {
				resultList.add(s.of());
			});

			result.setSearchMartList(resultList);
			result.setMessage(message);

			return Mono.just(result);
		});
	}
}
