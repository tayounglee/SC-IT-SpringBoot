package net.datasa.web5.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web5.domain.dto.BoardDTO;
import net.datasa.web5.domain.dto.ReplyDTO;
import net.datasa.web5.domain.entity.BoardEntity;
import net.datasa.web5.domain.entity.MemberEntity;
import net.datasa.web5.domain.entity.ReplyEntity;
import net.datasa.web5.repository.BoardRepository;
import net.datasa.web5.repository.MemberRepository;


@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class BoardService {
	
	private final BoardRepository bRepo;
	private final MemberRepository mRepo;
	
	
	public void write(BoardDTO dto) {
		MemberEntity memberEntity = mRepo.findById(dto.getMemberId())
				.orElseThrow(() -> new EntityNotFoundException("아이디가 없습니다."));
/*		
		BoardEntity entity = new BoardEntity();
		
		entity.setTitle(dto.getTitle());
		entity.setContents(dto.getContents());
		entity.setMember(memberEntity);
*/	
		dto.setMemberName(memberEntity.getMemberName());
		
		BoardEntity entity = BoardEntity.builder()
				.boardNum(dto.getBoardNum())
				.member(memberEntity)
				.title(dto.getTitle())
				.contents(dto.getContents())
				.viewCount(dto.getViewCount())
				.likeCount(dto.getLikeCount())
				.originalName(dto.getOriginalName())
				.fileName(dto.getFileName())
				.createDate(dto.getCreateDate())
				.build();
		
		bRepo.save(entity);
	}
	
	public Page<BoardDTO> getList(int page, int pageSize, String searchType, String searchWord){
		
		Pageable p = PageRequest.of(page - 1, pageSize, Sort.Direction.DESC, "boardNum");
		Page<BoardEntity> entityPage = null;

		switch (searchType) {
        case "title" :
            entityPage = bRepo.findByTitleContaining(searchWord, p);
            break;
        case "contents" :
            entityPage = bRepo.findByContentsContaining(searchWord, p);
            break;
        case "id" :
            entityPage = bRepo.findByMember_MemberId(searchWord, p);
            break;
        default :
            entityPage = bRepo.findAll(p);
            break;
    }

		Page<BoardDTO> dtoPage = entityPage.map(this::convertToDTO);
				
		return dtoPage;
	}
	
	/**
     * BoardEntity 객체를 전달받아 BoardDTO객체로 변환하여 리턴
     * @param entity DB에서 읽은 정보를 담은 엔티티 객체
     * @return		출력용 정보를 담은 DTO객체
     */
    private BoardDTO convertToDTO(BoardEntity entity) {
    	return BoardDTO.builder()
              .boardNum(entity.getBoardNum())
              .memberId(entity.getMember().getMemberId())
              .memberName(entity.getMember().getMemberName())
              .title(entity.getTitle())
              .contents(entity.getContents())
              .viewCount(entity.getViewCount())
              .likeCount(entity.getLikeCount())
              .originalName(entity.getOriginalName())
              .fileName(entity.getFileName())
              .createDate(entity.getCreateDate())
              .updateDate(entity.getUpdateDate())
              .build();
    }
    
    
    //boardNum을 받아서 해당 게시글 번호의 정보(Entity안의 정보)를 dto에 넣어서 반환
    public BoardDTO getBoard(int boardNum) {
    	BoardEntity entity = bRepo.findById(boardNum)
				.orElseThrow(() -> new EntityNotFoundException("글이 없습니다."));
    	log.debug("{}",entity);
    	
    	BoardDTO dto = convertToDTO(entity);
    	List<ReplyDTO>replyList = new ArrayList<>();
    	
    	for(ReplyEntity replyEntity : entity.getReplyList()) {
    		ReplyDTO replyDTO =ReplyDTO.builder()
    	              .boardNum(replyEntity.getBoard().getBoardNum())
    	              .replyNum(replyEntity.getReplyNum())
    	              .memberName(replyEntity.getMember().getMemberName())
    	              .memberId(replyEntity.getMember().getMemberId())
    	              .contents(replyEntity.getContents())
    	              .createDate(replyEntity.getCreateDate())
    	              .build();
    		replyList.add(replyDTO);
    	}
    	
    	dto.setReplyList(replyList);
    	    	
    	return dto;
    }
}