package openmart.apiserver.api.model.type;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class HomeplusConstants {
	
	public static final String code = "homeplus";

	public static final String name = "홈플러스";

	public static String filePath;

	@Value("${HOMEPLUS_FILE_PATH}")
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
