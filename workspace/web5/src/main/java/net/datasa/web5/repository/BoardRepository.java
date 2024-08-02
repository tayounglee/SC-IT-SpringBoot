package net.datasa.web5.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.datasa.web5.domain.entity.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer>{
	
	// List<BoardEntity> findByTitleContaining(String s, Sort sort);
	//전달된 문자열을 제목에서 검색한 후 지정한 한페이지 분량 리턴
		Page<BoardEntity> findByTitleContaining(String s, Pageable p);
		
		//전달된 문자열을 본문에서 검색한 후 지정한 한페이지 분량 리턴
		Page<BoardEntity> findByContentsContaining(String s, Pageable p);
		
		//전달된 문자열을 작성자 아이디에서 검색후 지정한 한페이지 분량 리턴
		Page<BoardEntity> findByMember_MemberId(String s, Pageable p);
	
}
