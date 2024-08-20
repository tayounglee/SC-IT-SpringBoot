package net.datasa.ajax.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.ajax.domain.dto.CommentDTO;
import net.datasa.ajax.service.CommentService;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("comment")
@RestController
public class CommentRestController {
	
	private final CommentService service;
	
	/**
	 * 사용자가 입력한 이름과 댓글내용을 전달받아 저장
	 * @param dto
	 */
	
	@PostMapping("write")
	public void write(@ModelAttribute CommentDTO dto){
		service.write(dto);
	}
	
	 /**
     * 댓글 목록 조회
     * @return 댓글 목록
     */
    @GetMapping("list")
    public ResponseEntity<?> list() {
        List<CommentDTO> list = service.getList();
     // 클라이언트에 JSON 형식으로 댓글 목록을 반환
        return ResponseEntity.ok(list);
    }
	
	/**
     * 댓글 삭제
     * @param num 삭제할 댓글 번호
     */
    @PostMapping("delete")
    public void delete(@RequestParam("num") Integer num) {
    	// 지정된 번호에 해당하는 댓글을 삭제
        service.delete(num);
    }

    /**
     * 댓글 수정
     * @param dto 수정할 댓글 정보
     */
    @PostMapping("update")
    public void update(CommentDTO dto) {
        log.debug("수정할 댓글 정보 : {}", dto);
     // 전달받은 정보로 댓글을 수정
        service.update(dto);
    }

	
}