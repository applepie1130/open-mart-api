
package openmart.apiserver.api.model.tuple.lottemart;

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
public class LotteMartSubResponseTuple implements Serializable {

	private static final long serialVersionUID = -8902867706905779787L;
	
	private LotteMartDetailResponseTuple data;
}
