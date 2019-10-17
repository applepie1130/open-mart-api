
package openmart.apiserver.api.model.tuple;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationsTuple implements Serializable {
	
	private static final long serialVersionUID = 6515821010931301713L;
	
	private String longitude;
	private String latitude;
}
