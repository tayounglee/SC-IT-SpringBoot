package net.datasa.web2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
	@GetMapping({"", "/"})
	public String web2() {
		return "web2";
	}
}
