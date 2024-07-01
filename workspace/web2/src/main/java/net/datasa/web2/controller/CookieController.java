package net.datasa.web2.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("ck")
@Slf4j
public class CookieController {
	
	@GetMapping("cookie1")
	public String cookie1(
			HttpServletRequest request,
			HttpServletResponse response) {
		
		//같은 와이파이 사용. cmd -> ipconfig -> IP주소 확인
		log.debug(request.getRemoteAddr());
		
		//쿠키 객체 생성
		Cookie a = new Cookie("str", "abcde");
		Cookie b = new Cookie("num", "123");
		//쿠키의 유지 시간
		a.setMaxAge(60*60*24*100);
		b.setMaxAge(60*60*24*100);
		//쿠키의 경로 설정
		a.setPath("/");
		b.setPath("/");
		//쿠키 내보내기
		response.addCookie(a);
		response.addCookie(b);
		
		log.debug("쿠키 저장됨");
		return "redirect:/";
	}
	
	@GetMapping("cookie2")
	public String cookie2(
			@CookieValue(name="num", defaultValue = "0") int n,
			@CookieValue(name="str", defaultValue = "없음") String s
			) {
		log.debug("num쿠키:{}", n);
		log.debug("str쿠키:{}", s);
		
		return "redirect:/";
	}
	
	@GetMapping("cookie3")
	public String cookie3(HttpServletResponse response) {
		//같은 이름으로 쿠키 생성 (값은 아무거나, 또는 null)
		Cookie a = new Cookie("str", "");
		Cookie b = new Cookie("num", "");
		//유지시간을 0초로
		a.setMaxAge(0);
		b.setMaxAge(0);
		//쿠키의 경로 설정
		a.setPath("/");
		b.setPath("/");
		//쿠키를 클라이언트로 보내서 저장
		response.addCookie(a);
		response.addCookie(b);
		
		return "redirect:/";
	}

}
