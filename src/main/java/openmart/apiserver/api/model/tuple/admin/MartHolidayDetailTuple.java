
package openmart.apiserver.api.model.tuple.admin;

import java.io.Serializable;
import java.util.List;

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
public class MartHolidayDetailTuple implements Serializable {

	private static final long serialVersionUID = 8363811291429413638L;
	
	private String region; // 지역 : 서울, 인천, 경기 ...
	private String martName; // 마트명 : 이마트, 홈플러스 ...
	private String martFullName; // 마트명 : 이마트 강남점, 홈플러스 송도점...
	private String regionMartName; // 지점명 : 강남점, 송도점
	private String telno; // 전화번호
	private List<String> holidayYYYYMMDD; // 휴일리스트 (지점당 일자별 휴일 리스트가 있는경우) 
	private String holidayInfos; // 휴일정보 (지점당 휴일정보가 TEXT로 되어있는경우)
}
