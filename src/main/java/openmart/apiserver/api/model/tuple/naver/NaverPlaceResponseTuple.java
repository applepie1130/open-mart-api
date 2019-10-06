
package openmart.apiserver.api.model.tuple.naver;

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
public class NaverPlaceResponseTuple implements Serializable {
	
	private static final long serialVersionUID = 2778288225821507384L;
	
	private String name;
	private String road_address;
	private String jibun_address;
	private String phone_number;
	private String x;
	private String y;
	private String distance;
	private String sessionId;
	
}

