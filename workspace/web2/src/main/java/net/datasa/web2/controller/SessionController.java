package net.datasa.web2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("ss")
@Slf4j
public class SessionController {
	
	//세션에 값 저장
	@GetMapping("session1")
	public String session1(HttpSession session) {
		session.setAttribute("name", "홍길동");
		
		log.debug("seesion1 지나감");
		return "redirect:/";
	}
	
	//세션 값 일기
	@GetMapping("session2")
	public String session2(HttpSession session) {
		String name = (String) session.getAttribute("name");
		//name이라는 key의 밸류의 타입은 Object이기 때문에 (String)으로 다운캐스팅이 필요.
		//map 형식으로 저장하는데, key는 다 String 타입으로 저장된다 하더라도,
		// value는 어떤 타입이 올지 모르기 때문에 Object 타입이다.
		
		log.debug("세션의 값: {}", name);
		return "ssView/session2";
	}
	
	//세션 값 삭제
	@GetMapping("session3")
	public String session3(HttpSession session) {
		session.removeAttribute("name");
		return "redirect:/";
	}
	
	//로그인 폼으로 이동
	@GetMapping("login")
	public String login() {
		return "ssView/login";
	}
	
	//로그인 처리
	@PostMapping("login")
	public String loginProcess(
			HttpSession session,
			@RequestParam("id") String id,
			@RequestParam("password") String pw) {
		
		log.debug("전송된값:{}/{}", id, pw);
		//id가 "abc" 비번이 "123"이면 로그인
		if (id.equals("abc") && pw.equals("123")) {
			session.setAttribute("loginId", id);
			// 세션에 loginId라는 key로 id를 저장.
			return "redirect:/";
			
		} else {
			return "ssView/login";
		}
	}
	
	//로그아웃
	@GetMapping("logout")
	public String logout(
			HttpSession session
			) {
		session.removeAttribute("loginId");
		return "redirect:/";
	}
	
	//로그인 폼으로 이동
	//http://localhost:8888/ss/loginTest
	@GetMapping("loginTest")
	public String loginTest(HttpSession session) {
		
		String id = (String) session.getAttribute("loginId");
		if (id == null) {
			return "redirect:/ss/login";
		}
		return "ssView/loginTest";
	}
}
