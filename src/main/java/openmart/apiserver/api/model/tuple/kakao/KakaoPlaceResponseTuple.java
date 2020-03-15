
package openmart.apiserver.api.model.tuple.kakao;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoPlaceResponseTuple implements Serializable {
	
	private static final long serialVersionUID = 2778288225821507384L;
	
	private String address_name; // 서울 강남구 역삼동 833-4
	/**
	 * MT1	대형마트
	 * CS2	편의점
	 * PS3	어린이집, 유치원
	 * SC4	학교
	 * AC5	학원
	 * PK6	주차장OL7	주유소, 충전소
	 * SW8	지하철역
	 * BK9	은행
	 * CT1	문화시설
	 * AG2	중개업소
	 * PO3	공공기관
	 * AT4	관광명소
	 * AD5	숙박
	 * FD6	음식점
	 * CE7	카페
	 * HP8	병원
	 * PM9	약국
	 */
	private String category_group_code; // MT1
    private String category_group_name; // 대형마트
    private String category_name; // 가정,생활 \u003e 슈퍼마켓 \u003e 대형슈퍼 \u003e 이마트 에브리데이
    private String distance; // 621,
    private String id; // 2115939257
    private String phone; // 02-6951-3011
    private String place_name; // 이마트에브리데이 역삼점
    private String place_url; /* http://place.map.kakao.com/2115939257 */
    private String road_address_name; // 서울 강남구 역삼로 124
    private String x; // 127.03326371111147
    private String y; // 37.49384213144032
}

