
package openmart.apiserver.api.model.tuple;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import openmart.apiserver.api.model.type.CostcoConstants;
import openmart.apiserver.api.model.type.EmartConstants;
import openmart.apiserver.api.model.type.HomeplusConstants;
import openmart.apiserver.api.model.type.LotteMartConstants;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationsTuple implements Serializable {
	
	private static final long serialVersionUID = 6515821010931301713L;
	
	private String longitiude;
	private String latitude;
}
