package net.datasa.ajax.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.ajax.domain.dto.PersonDTO;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AjaxController {
	
	@GetMapping("ajax1")
	public String ajax1() {
		return "ajax1";
	}
	
	@ResponseBody				//
	@GetMapping("ajaxtest1")
	public void ajaxtest1() {
		log.debug("AjaxController의 ajaxtest1()메소드 실행함");
	}
	
	@ResponseBody				//
	@PostMapping("ajaxtest2")
	public void ajaxtest2(@RequestParam("str") String s) {
		log.debug("전달된 값 :{}", s);
	}
	
	@ResponseBody				//
	@GetMapping("ajaxtest3")
	public String ajaxtest3() {
		String s = "문자열";
		return s;
	}
	
	@ResponseBody				//
	@PostMapping("ajaxtest4")
	public int ajaxtest4(@RequestParam("num1") int num1, @RequestParam("num2") int num2) {
		return num1 + num2;
	}
	
	@ResponseBody				//
	@PostMapping("ajaxtest5")
	public ResponseEntity<?> ajaxtest5(@RequestParam("num1") String a, @RequestParam("num2") String b, @RequestParam("oper") String c) {
		
		try {
			int num1 = Integer.parseInt(a);
			int num2 = Integer.parseInt(b);
			int num3 = 0;
			switch(c) {
				case "+":
					num3 = num1 + num2;
					break;
				case "-":
					num3 = num1 - num2;
					break;
				case "*":
					num3 = num1 * num2;
					break;
				case "/":
					if(num2 == 0) {
						return ResponseEntity.badRequest().body("0으로 나눌 수 없습니다.");
					}
					num3 = num1 / num2;
					break;
				default:
					return ResponseEntity.badRequest().body("유효한 선택이 아닙니다.");
			}
			return ResponseEntity.ok(num3);
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("정수가 아닙니다");
		}
		catch(ArithmeticException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("0으로 나눌 수 없습니다.");
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("실행 실패");
		}

	}
	
	@GetMapping("ajax2")
	public String ajax2() {
		return "ajax2";
	}
	
	@ResponseBody
	@PostMapping("input1")
	public void input1(PersonDTO dto) {
		log.debug("전달된 객체 : {}", dto);
	}
	
	@ResponseBody
	@GetMapping("getObject")
	public PersonDTO getObject() {
		PersonDTO dto = new PersonDTO("김", 22, "000-1111-2222");
		return dto;
	}
	
	@ResponseBody
	@GetMapping("getList")
	public List<PersonDTO> getList() {
		List<PersonDTO> list = new ArrayList<>();
		list.add(new PersonDTO("김OO", 11, "010-1111-2222"));
		list.add(new PersonDTO("박OO", 22, "010-1111-3333"));
		list.add(new PersonDTO("이OO", 33, "010-1111-4444"));
		return list;
	}
	
	@ResponseBody
	@PostMapping("sendArray")
	public void sendArray(@RequestParam("ar") String [] ar) {
		for(String s: ar) {
			log.debug("배열 요소 : {}", s);
		}
		
	}
	
	@ResponseBody
	@PostMapping("sendObjectArray")
	public void sendObjectArray(@RequestParam("ar") String ar) throws Exception {
		log.debug("전달된 값 : {}", ar);
		
		ObjectMapper objectMapper = new ObjectMapper();
		ArrayList<PersonDTO> list =
			objectMapper.readValue(ar, new TypeReference<ArrayList<PersonDTO>>() {});
		
	}
}
