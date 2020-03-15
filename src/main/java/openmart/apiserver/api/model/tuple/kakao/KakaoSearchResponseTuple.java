
package openmart.apiserver.api.model.tuple.kakao;

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
public class KakaoSearchResponseTuple implements Serializable {
	
	private static final long serialVersionUID = 8317444478406116960L;
	
	private Map<String, Object> meta;
	private List<KakaoPlaceResponseTuple> documents;
}

