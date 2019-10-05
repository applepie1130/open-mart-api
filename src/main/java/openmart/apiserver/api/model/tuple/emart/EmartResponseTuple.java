
package openmart.apiserver.api.model.tuple.emart;

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
public class EmartResponseTuple implements Serializable {

	private static final long serialVersionUID = 8923910905142690651L;
	
	private Object param;
    private Integer dateListMinus;
    private Integer dateListPlus;
	private List<Map<String, String>> dateList;
//    private List<EmartDetailResponseTuple> dateList;
}
