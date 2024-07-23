package net.datasa.test.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import net.datasa.test.AuthenticatedUser;

@Slf4j
@Controller
public class HomeController {
	
	@GetMapping({"", "/"})
	public String Home(){
		
		return "home";
	}
	
	@GetMapping({"view1"})
	public String View1(){
		
		return "view1";
	}
	
	@GetMapping({"view2"})
	public String View2(@AuthenticationPrincipal AuthenticatedUser user){
		
		log.debug("사용자 아이디 : {}", user.getId());
		return "view2";
	}
	
	@GetMapping({"loginForm"})
	public String loginForm() {
		
		return "loginForm";
	}
	
	@GetMapping({"thymeleaf"})
	public String thymeleaf() {
		
		return "thymeleaf";
	}
}
