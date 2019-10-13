
package openmart.apiserver.api.model.criteria;

import java.io.Serializable;

import lombok.Data;

@Data
public class MartSearchCriteria implements Serializable {
	
	private static final long serialVersionUID = -6053645793274962419L;
	
	private String latitude; // 위도
	private String longitude; // 경도
	private String martName; // 마트명
}

