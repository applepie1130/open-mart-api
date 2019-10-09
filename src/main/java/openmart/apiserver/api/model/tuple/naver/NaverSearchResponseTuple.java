
package openmart.apiserver.api.model.tuple.naver;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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
public class NaverSearchResponseTuple implements Serializable {
	
	private static final long serialVersionUID = 5600639064791771578L;
	private String status;
	private Map<String, String> meta;
	private List<NaverPlaceResponseTuple> places;
}

