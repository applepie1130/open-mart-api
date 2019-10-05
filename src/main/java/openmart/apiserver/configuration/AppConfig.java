package openmart.apiserver.configuration;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configurable
public class AppConfig { 

	/**
	 *	요청을 보낼 때 사용할 문자 코드를 UTF-8로 설정 (서브릿 필터)
	 *	인코딩 설 부분이므로, Bean 우선순위 최 상위로 설정
	 */
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	CharacterEncodingFilter characterEncodingFilter() {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		
		return filter;
	}
	
}
