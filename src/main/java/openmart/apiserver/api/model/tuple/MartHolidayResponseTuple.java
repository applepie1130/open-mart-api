
package openmart.apiserver.api.model.tuple;

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
public class MartHolidayResponseTuple implements Serializable {
	
	private static final long serialVersionUID = -876844924275520060L;
	private List<MartHolidayInfosTuple> searchMartList;
	private String message;
}
