package openmart.apiserver.api.model.type;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Data
@Component
public class EmartConstants {

	public final static String code = "emart";
	
	public final static String name = "이마트";
	
	public final static String apiUrl = "https://store.emart.com/branch/holidayList.do";
	
	// (서울:A, 인천:C, 경기:I, 대전:F, 세종:Q, 충청:N, 대구:D, 경상:J, 울산:G, 부산:B, 전라:L, 광주:E, 강원:H, 제주:P)
	public final static List<String> areaCodeList = Arrays.asList("A", "C", "I", "F", "Q", "N", "D", "J", "G", "B", "L", "E", "H", "P");

	public static String filePath;

	@Value("${EMART_FILE_PATH}")
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
