package net.datasa.web5.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web5.domain.dto.BoardDTO;
import net.datasa.web5.security.AuthenticatedUser;
import net.datasa.web5.service.BoardService;

@RequiredArgsConstructor
@RequestMapping("board")
@Slf4j
@Controller
public class BoardController {
	private final BoardService service;

	@GetMapping("list")
	public String list() {
		
		return "boardView/list";
	}
	
	@GetMapping("write")
	public String write() {
		
		return "boardView/write";
	}
	
	@PostMapping("write")
	public String write(@ModelAttribute BoardDTO dto, @AuthenticationPrincipal AuthenticatedUser user) {
		dto.setMemberid(user.getUsername());
		service.write(dto);
		
		return "redirect:list";
	}
}
