
package openmart.apiserver.api.model.tuple.emart;

import java.io.Serializable;

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
public class EmartDetailResponseTuple implements Serializable {
	
	private static final long serialVersionUID = -1928764476428023405L;
	
	private String NAME;
    private String AREA;
    private String HOLIDAY_YEAR;
    private String HOLIDAY_DAY3_YMD;
    private String HOLIDAY_MONTH;
    private String HOLIDAY_DAY1_YOIL;
    private String JIJUM_ID;
    private String HOLIDAY_DAY2_YOIL;
    private String HOLIDAY_DAY2_YMD;
    private String TEL;
    private String HOLIDAY_DAY2;
    private String HOLIDAY_DAY1;
    private String HOLIDAY_DAY1_YMD;
    private String HOLIDAY_DAY3;
    private String CODE;
}
