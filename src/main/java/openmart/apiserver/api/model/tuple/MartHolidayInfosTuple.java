
package openmart.apiserver.api.model.tuple;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import openmart.apiserver.api.model.type.CostcoConstants;
import openmart.apiserver.api.model.type.EmartConstants;
import openmart.apiserver.api.model.type.HomeplusConstants;
import openmart.apiserver.api.model.type.LotteMartConstants;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MartHolidayInfosTuple implements Serializable {
	
	private static final long serialVersionUID = -8006724660080966343L;

	private String code;
	private String name;
	private String telNo;
	private String address;
	private BigDecimal distance;
	private String displayDistance;
	private String holidaysInfo;
	private LocationsTuple userLocations;
	private LocationsTuple martLocations;
	private Boolean isOpen;
	
	public String getCode() {
		if (StringUtils.isNotBlank(this.name)) {
			if (this.name.contains(EmartConstants.name)) {
				this.code = EmartConstants.code;
			} else if (this.name.contains(LotteMartConstants.name)) {
				this.code = LotteMartConstants.code;
			} else if (this.name.contains(HomeplusConstants.name)) {
				this.code = HomeplusConstants.code;
			} else if (this.name.contains(CostcoConstants.name)) {
				this.code = CostcoConstants.code;
			}
		}
		return this.code;
	}

	/**
	 * convert response
	 * @return
	 */
	public MartHolidayInfosResponseTuple of () {
		return MartHolidayInfosResponseTuple.builder()
				.code(this.getCode())
				.name(this.getName())
				.telNo(this.getTelNo())
				.address(this.getAddress())
				.distance(this.getDistance())
				.displayDistance(this.getDisplayDistance())
				.holidaysInfo(this.getHolidaysInfo())
				.userLocations(this.getUserLocations())
				.martLocations(this.getMartLocations())
				.build();
	}
}