package net.datasa.web2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("local")
@Slf4j
public class LocalController {
	
	@GetMapping("save")
	public String save() {
		return "localView/save";
	}
	
	@GetMapping("read")
	public String read() {
		return "localView/read";
	}
	
	@GetMapping("delete")
	public String delete() {
		return "localView/delete";
	}
}
