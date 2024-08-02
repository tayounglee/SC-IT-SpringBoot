package net.datasa.web5.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	@Value("${board.pageSize}")
    int pageSize;				//페이지당 글 수
    
    @Value("${board.linkSize}")
    int linkSize;				//페이지 이동 링크 수
    
    @Value("${board.uploadPath}")
    String uploadPath;			//첨부파일 저장 경로
	
	@GetMapping("list")
	public String list(Model model
    		, @RequestParam(name="page", defaultValue="1") int page
    		, @RequestParam(name="searchType", defaultValue="") String searchType
    		, @RequestParam(name="searchWord", defaultValue="") String searchWord) {
		//서비스에서 전체 글목록 전달받음
		Page<BoardDTO> boardPage = service.getList(
				page, pageSize, searchType, searchWord);
		//글목록을 모델에 저장하고 HTML로 포워딩
		model.addAttribute("boardPage", boardPage); //출력할 글정보
		model.addAttribute("page", page);			//현재 페이지
		model.addAttribute("linkSize", linkSize);	//페이지 이동링크 수
		model.addAttribute("searchType", searchType);	//검색기준
		model.addAttribute("searchWord", searchWord);	//검색어

		
		return "boardView/list";
	}
	
	@GetMapping("write")
	public String write() {
		
		return "boardView/write";
	}
	
	//@AuthenticationPrincipal
	@PostMapping("write")
	public String write(@ModelAttribute BoardDTO dto, @AuthenticationPrincipal AuthenticatedUser user) {
		dto.setMemberId(user.getUsername());
		service.write(dto);
		
		return "redirect:list";
	}
	
	@GetMapping("read")
	public String read(@RequestParam("boardNum") int boardNum, Model model) {
		try {
			BoardDTO dto = service.getBoard(boardNum);
			model.addAttribute("board", dto);
			return "boardView/read";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "redirect:list";
		}
	}
	
	@GetMapping("delete")
	public String delete(@RequestParam("boardNum") int boardNum, @AuthenticationPrincipal AuthenticatedUser user) {
		//service.delete(boardNum, user.getUsername());
		return "redirect:list";
	}
}
