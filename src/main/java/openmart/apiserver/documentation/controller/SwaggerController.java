
package openmart.apiserver.documentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerController {
	@RequestMapping("/api-docs")
	public String documents() {
		return "/swagger/index.html";
	}
}
