
package openmart.apiserver.api.model.tuple;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MartHolidayTuple implements Serializable {

	private static final long serialVersionUID = -1528972018506500747L;
	
	String telNo;
	List<MartHolidayDetailTuple> martHolidayDetailTupleList;
}
