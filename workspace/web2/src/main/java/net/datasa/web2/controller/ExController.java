package net.datasa.web2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web2.domain.CalcDTO;
import net.datasa.web2.service.CalcService;

/**
 * 연습문제
 * 클래스에 대한 설명.
 */

@Slf4j
@RequestMapping("/ex")
@Controller
public class ExController {
	
	@Autowired
	CalcService calcService;
		
	/**
	 * 계산기2 폼 화면으로 이동
	 */
	
	@GetMapping("calc1")
	public String calc1() {
		return "exView/calcForm1";
	}
	
	@PostMapping("calc1")
	public String calcoutput1(@ModelAttribute CalcDTO dto, Model model) {
		
		int res = 0;
		try {
			switch(dto.getOp()) {
			case "+" : res = dto.getNum1() + dto.getNum2(); break;
			case "-" : res = dto.getNum1() - dto.getNum2(); break;
			case "*" : res = dto.getNum1() * dto.getNum2(); break;
			case "/" : res = dto.getNum1() / dto.getNum2(); break;
			default: throw new Exception("연산자 오류");
			}
			model.addAttribute("calc", dto);
			model.addAttribute("res", res);
		}
		catch (Exception e) {
			e.printStackTrace();
			return "/exView/calcForm1"; // 예외 발생시 계산폼으로 다시 이동
		}
		
		return "exView/calcOutput1";
	}

	
	@GetMapping("calc2")
	public String calc2() {
		return "exView/calcForm2";
	}
	
	@PostMapping("calc2")
	public String calcOutput2(@ModelAttribute CalcDTO dto, Model model) {
		try {
			int res = calcService.calc(dto);
			model.addAttribute("calc", dto);
			model.addAttribute("res", res);
			return "exView/calcOutput2";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "/exView/calcForm1"; // 예외 발생시 계산폼으로 다시 이동
		}
	}
	
	/**
	 * 쿠키를 이용하여 방문 횟수 카운트
	 * @param count : 쿠키에서 읽은 이전 방문 횟수 
	 * @param response : 응답 정보 객체
	 * @param model : HTML로 값을 전달할 객체
	 * @return 템플릿 HTML페이지 경로
	 */
		
	@GetMapping("count")
	public String count(
		@CookieValue(name="count", defaultValue = "0") int count,
		HttpServletResponse response,
		Model model,
		HttpSession session) {
		/*
      		위의 링크를 클릭하면 콘트롤러의 메소드 실행
      		쿠키를 읽어서 없으면 숫자를 0으로 처리
      		방문횟수를 1증가
      		"첫 방문 환영합니다" 또는 "X번째 방문입니다" 메시지를 모델에 저장
      		쿠키도 저장
      		HTML로 포워딩해서 메시지 출력
		*/
		
		String id = (String) session.getAttribute("id");
		if (id == null || id.isEmpty()) {
			return "redirect:/ss/nickname";
		}
		
		count++;
		if(count == 1) {
			model.addAttribute("msg", " 첫번째 방문 환영합니다.");
		}
		else {
			model.addAttribute("msg", count + "번째 방문 환영합니다.");
		}
		Cookie c = new Cookie("count", Integer.toString(count));
		c.setMaxAge(60*60*24*365);
		response.addCookie(c);
		
		return "exView/count";
	}
}
