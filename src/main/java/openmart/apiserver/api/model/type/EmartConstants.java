package openmart.apiserver.api.model.type;

import java.util.Arrays;
import java.util.List;

public class EmartConstants {

	public static final String code = "emart";
	
	public static final String name = "이마트";
	
	public static final String apiUrl = "https://store.emart.com/branch/holidayList.do";
	
	// (서울:A, 인천:C, 경기:I, 대전:F, 세종:Q, 충청:N, 대구:D, 경상:J, 울산:G, 부산:B, 전라:L, 광주:E, 강원:H, 제주:P)
	public static final List<String> areaCodeList = Arrays.asList("A", "C", "I", "F", "Q", "N", "D", "J", "G", "B", "L", "E", "H", "P");
	
	public static final String filePath = "/tank0/holidays/emart.json";

}
