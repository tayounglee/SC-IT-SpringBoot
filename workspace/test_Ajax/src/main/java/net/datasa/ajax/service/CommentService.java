package net.datasa.ajax.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.datasa.ajax.domain.Entity.CommentEntity;
import net.datasa.ajax.domain.dto.CommentDTO;
import net.datasa.ajax.repository.CommentRepository;

/**
 * 학생 정보를 관리하는 서비스 클래스
 */

@RequiredArgsConstructor
@Service
@Transactional
public class CommentService {

	private final CommentRepository commentRepository;
	
	public void write(CommentDTO dto) {
		//DB의 정보를 조회
		CommentEntity entity = new CommentEntity();
		//값 수정
		
		entity.setName(dto.getName());
		entity.setComment(dto.getComment());
		//저장
		commentRepository.save(entity);
	}
	
	/**
     * 댓글 목록 조회
     * @return 댓글 목록
     */
    public List<CommentDTO> getList() {
    	// 모든 댓글을 조회
        List<CommentEntity> entityList = commentRepository.findAll();
        List<CommentDTO> dtoList = new ArrayList<>();

        for (CommentEntity entity : entityList) {
            CommentDTO dto = CommentDTO.builder()
                    .num(entity.getNum())
                    .name(entity.getName())
                    .comment(entity.getComment())
                    .build();
            dtoList.add(dto);
        }
        return dtoList;
    }

    /**
     * 댓글 삭제
     * @param num 삭제할 번호
     */
    public void delete(int num) {
    	// 해당 번호의 댓글을 삭제
        commentRepository.deleteById(num);
    }

    /**
     * 댓글 수정
     * @param dto 수정할 댓글 내용
     * @throws EntityNotFoundException 해당 번호의 댓글이 없을 때 예외
     */
    public void update(CommentDTO dto) {
        CommentEntity entity = commentRepository.findById(dto.getNum())
                .orElseThrow(() -> new EntityNotFoundException("수정할 댓글이 없습니다."));
        // 새로운 댓글 내용으로 업데이트
        entity.setComment(dto.getComment());
        // 변경 내용을 저장
        commentRepository.save(entity);
    }

}
