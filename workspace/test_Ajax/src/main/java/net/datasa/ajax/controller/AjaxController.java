package net.datasa.ajax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
}
