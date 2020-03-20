package openmart.apiserver.api.model.type;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class CostcoConstants {

	public static final String code = "costco";
	
	public static final String name = "코스트코";

	public static String filePath;

	@Value("${COSTCO_FILE_PATH}")
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}