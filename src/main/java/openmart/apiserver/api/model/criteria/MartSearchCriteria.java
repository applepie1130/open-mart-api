
package openmart.apiserver.api.model.criteria;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class MartSearchCriteria implements Serializable {
	
	private static final long serialVersionUID = -6053645793274962419L;
	
	private String latitude; // 위도
	private String longitude; // 경도
	private String martName; // 마트명
	
	public String getMartName() {
		
		if (StringUtils.isNotBlank(this.martName)) {
			try {
				this.martName = URLDecoder.decode(this.martName, "UTF-8");
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
		
		return this.martName;
	}
}

