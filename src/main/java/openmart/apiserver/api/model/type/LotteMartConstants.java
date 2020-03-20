package openmart.apiserver.api.model.type;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Data
@Component
public class LotteMartConstants {

	public static final String code = "lottemart";
	
	public static final String name = "롯데마트";
	
	public static final String apiUrl = "http://company.lottemart.com/bc/branch/regnstorelist.json";
	
	public static final String subApiUrl = "http://company.lottemart.com/bc/branch/storeinfo.json";
	
	public static final List<String> regionCodeList = Arrays.asList("BC0101", "BC0102", "BC0103", "BC0104", "BC0105", 
																	"BC0106", "BC0107", "BC0108", "BC0109", "BC0110",
																	"BC0111", "BC0112", "BC0113", "BC0114", "BC0116");

	public static String filePath;

	@Value("${LOTTEMART_FILE_PATH}")
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
