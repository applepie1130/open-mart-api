package openmart.apiserver.api.comonent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import openmart.apiserver.api.model.tuple.admin.MartHolidayDetailTuple;

@Slf4j
@Component
public class FileCommonUtils {
	
	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * @Desc	: JSON Map형식으로 File Write
	 * @Author	: 김성준
	 * @Create	: 2015년 05월 16일 
	 * @stereotype Utils
	 */
	public void writeFileToJSONMap (String sFile, Map<String, MartHolidayDetailTuple> mTmpData) {
		// Write JSON File
		try {
			File file = new File(sFile);
			File filePath = new File(file.getParent()); 
			
			// 파일 디렉토리가 없으면 디렉토리 생성, 있으면 파일쓰기
			if ( !filePath.exists() ) {
				filePath.mkdirs();
			}
			
			mapper.writeValue(file, mTmpData);
			
		} catch (IOException e) {
			log.error(e.getMessage(), e);
//			throw new BusinessException("오류가 발생했습니다.<br/>잠시 후 다시 시도해주세요.");
		}
	}
	
	/**
	 * @Desc	: JSON List형식으로 File Write
	 * @Author	: 김성준
	 * @Create	: 2015년 05월 16일 
	 * @stereotype Utils
	 */
	public void writeFileToJSONList (String sFile, List<Object> rTmpData) {
		// Write JSON File
		try {
			File file = new File(sFile);
			File filePath = new File(file.getParent()); 
			
			// 파일 디렉토리가 없으면 디렉토리 생성, 있으면 파일쓰기
			if ( !filePath.exists() ) {
				filePath.mkdirs();
			}
			
			mapper.writeValue(file, rTmpData);
			
		} catch (IOException e) {
//			throw new BusinessException("오류가 발생했습니다.<br/>잠시 후 다시 시도해주세요.");
		}
	}
	
	/**
	 * @Desc	: JSON Map형식 File Read
	 * @Author	: 김성준
	 * @Create	: 2015년 05월 16일 
	 * @stereotype Utils
	 */
	public Map<String, Object> readFileFromJSONMap (String sFile) {
		// Read JSON File
		Map<String, Object> mRtnData = new HashMap<String, Object>();
		
		try {
			mRtnData = mapper.readValue(new File(sFile), new TypeReference<HashMap<String,Object>>(){});
		} catch (IOException e) {
//			throw new BusinessException("오류가 발생했습니다.<br/>잠시 후 다시 시도해주세요.");
		}
		
		return mRtnData;
	}
	
	/**
	 * @Desc	: JSON List형식 File Read
	 * @Author	: 김성준
	 * @Create	: 2015년 05월 16일 
	 * @stereotype Utils
	 */
	public List<Object> readFileFromJSONList (String sFile) {
		// Read JSON File
		List<Object> rRtnData = new ArrayList<Object>();
		
		try {
			rRtnData = mapper.readValue(new File(sFile), new TypeReference<List<Object>>(){});
		} catch (IOException e) {
//			throw new BusinessException("오류가 발생했습니다.<br/>잠시 후 다시 시도해주세요.");
		}
		
		return rRtnData;
	}

}
