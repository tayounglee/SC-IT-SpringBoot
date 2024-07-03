package net.datasa.web2.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.datasa.web2.domain.Person;

@Controller
@RequestMapping("th")
@Slf4j
public class ThymeleafController {
	
	@GetMapping("thymeleaf1")
	public String thymeleaf1(
			Model model) {
		String str = "문자열";
		int num = 3;
		Person p = new Person("홍길동", 20, "010-1111-2222");
		String tag = "<marquee>HTML 태그가 포함된 문자열</marquee>";
		String url = "https://google.com";
		
		model.addAttribute("str", str);
		model.addAttribute("num", num);
		model.addAttribute("person", p);
		model.addAttribute("tag", tag);
		model.addAttribute("url", url);
		
		int n1 = 1000000;
		double n2 = 123.45678;
		double n3 = 0.5;
		Date today = new Date();
		
		model.addAttribute("n1", n1);
		model.addAttribute("n2", n2);
		model.addAttribute("n3", n3);
		model.addAttribute("today", today);
		
		String values = "Java,HTML,CSS";
		model.addAttribute("values", values);
		
		
		
		return "thView/thymeleaf1";
	}
	
	@GetMapping("thymeleaf2")
	public String thymeleaf2(
			Model model) {
		ArrayList<String> list = new ArrayList<>();
		list.add("aaaa");
		list.add("가나다라");
		list.add("12345");
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("product", "키보드");
		map.put("price", 10000);
		
		ArrayList<Person> personlist = new ArrayList<>();
		personlist.add(new Person("김", 10, "010-1111-2222"));
		personlist.add(new Person("이", 20, "010-3333-2222"));
		personlist.add(new Person("박", 30, "010-4444-2222"));
		
		model.addAttribute("list", list);
		model.addAttribute("map", map);
		model.addAttribute("personlist", personlist);
		
		return "thView/thymeleaf2";
	}
}
