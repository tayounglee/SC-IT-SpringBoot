package net.datasa.ajax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("comment")
@Controller
public class CommentController {
	
	/**
	 * 댓글 테스트 페이지로 이동
	 * @return HTML 파일경로
	 */
	@GetMapping("main")
	public String main() {
		return "comment";
	}
}
