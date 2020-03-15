
package openmart.apiserver.api.model.tuple.kakao;

import java.io.Serializable;
import java.math.BigDecimal;

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
public class KakaoSearchTuple implements Serializable {
	
	private static final long serialVersionUID = -2233649324772242231L;
	
	private String name;
	private String address;
	private String telNoKey;
	private String telNo;
	private BigDecimal distance;
	private String latitude; // y
	private String longitude; // x
}

