package net.datasa.web2.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.datasa.web2.domain.CalcDTO;
import net.datasa.web2.service.CalcService;

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
		
		int res = 0, n1, n2;
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
}
