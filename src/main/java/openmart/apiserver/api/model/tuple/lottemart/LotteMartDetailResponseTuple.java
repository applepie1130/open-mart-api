
package openmart.apiserver.api.model.tuple.lottemart;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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
public class LotteMartDetailResponseTuple implements Serializable {

	private static final long serialVersionUID = -5605490551899969217L;
	
	private String martStrCd;
	private String strNm;
	private String strCd;
	private String brnchCd;
	private String brnchTypeCd;
	private String brnchHldyDivnCd;
	private String oldAddress;
	private String newAddress;
	private String repTelNo;
	private String openTime;
	private String closedTime;
	private String holiDate;
	private String xcoord;
	private String ycoord;
	private String storeSiteURL;
	
}

