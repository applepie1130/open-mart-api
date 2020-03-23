
package openmart.apiserver.api.model.criteria;

import lombok.Data;

import java.io.Serializable;

@Data
public class MartSearchCriteria implements Serializable {
	
	private static final long serialVersionUID = -6053645793274962419L;
	
	private String latitude; // 위도
	private String longitude; // 경도
	private String martName; // 마트명
	private Boolean isHandsfree; // 핸즈프리여부
}

