package net.datasa.web5.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web5.domain.dto.BoardDTO;
import net.datasa.web5.domain.entity.BoardEntity;
import net.datasa.web5.repository.BoardRepository;


@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class BoardService {
	
	private final BoardRepository repo;
	
	
	
	public void write(BoardDTO dto) {
		
		BoardEntity entity = BoardEntity.builder()
				.num(dto.getNum())
				.memberId(dto.getMemberid())
				.title(dto.getTitle())
				.contents(dto.getContents())
				.viewCount(dto.getViewcount())
				.likeCount(dto.getLikecount())
				.originalName(dto.getOriginalname())
				.fileName(dto.getFilename())
				.createDate(dto.getCreatedate())
				.build();
			log.debug(dto.getMemberid());
		repo.save(entity);
	}
}