package net.datasa.web2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("jq")
public class JqueryController {
		@GetMapping("jquery1")
		public String jquery1() {
			return "jqView/jquery1";
		}
		
		@GetMapping("jquery2")
		public String jquery2() {
			return "jqView/jquery2";
		}
		
		@GetMapping("jquery3")
		public String jquery3() {
			return "jqView/jquery3";
		}
		
		@GetMapping("jquery4")
		public String jquery4() {
			return "jqView/jquery4";
		}
}
