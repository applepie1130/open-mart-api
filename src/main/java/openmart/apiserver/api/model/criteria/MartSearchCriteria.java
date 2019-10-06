
package openmart.apiserver.api.model.criteria;

import java.io.Serializable;

import lombok.Data;

@Data
public class MartSearchCriteria implements Serializable {
	
	private static final long serialVersionUID = -6053645793274962419L;
	
	private String latitude;
	private String longitude;
	private String martName;
}

