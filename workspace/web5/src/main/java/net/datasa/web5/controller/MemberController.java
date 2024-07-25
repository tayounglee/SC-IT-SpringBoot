package net.datasa.web5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web5.domain.dto.MemberDTO;
import net.datasa.web5.service.MemberService;

/**
 * 회원정보 관련 컨트롤러
 */

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberController {

	private final MemberService service;
	/**
	 * 회원가입양식으로 이동
	 */
	
	@GetMapping("joinForm")
	public String join() {
	      
	   //templates/paramView/view1.html 파일로 포워딩
	   return "memberView/joinForm";
	}
	
	@PostMapping("join")
	public String join(@ModelAttribute MemberDTO member) {
		service.join(member);
		
		return "redirect:/";
	}
	
	@GetMapping("idCheck")
	public String idCheck() {
		
		return "memberView/idCheck";
	}
	
	@PostMapping("idCheck")
	public String idCheck(@RequestParam("searchId") String searchId, Model model) {
		//ID중복확인 폼에서 전달된 검색할 아이디를 받아서 log출력
		//서비스의 메소드로 검색할 아이디를 전달받아서 조회
		//해당 아이디를 쓰는 회원이 있으면 false, 없으면 true 리턴받음
		//검색한 아이디와 조회결과를 모델에 저장
		//검색 페이지로 다시 이동
		boolean result = service.findId(searchId);
		model.addAttribute("searchId", searchId);
		model.addAttribute("result", result);
		return "memberView/idCheck";
	}	
}