
package openmart.apiserver.api.model.tuple;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The type Mart holiday infos response tuple.
 */
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MartHolidayInfosResponseTuple implements Serializable {

	private static final long serialVersionUID = 1357814094529163633L;

	private String code;
	private String name;
	private String telNo;
	private String address;
	private BigDecimal distance;
	private String displayDistance;
	private String holidaysInfo;
	private LocationsTuple userLocations;
	private LocationsTuple martLocations;

}

