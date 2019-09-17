package it.smartcommunitylab.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	@RequestMapping("/hello")
	public @ResponseBody String ciao() {
		return "ciao";
	}
	
	@RequestMapping("/")
	public String index() {
		return "index.html";
	}
}
