package net.datasa.web5.domain.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
	int boardNum;
	String memberId;
	String memberName;
	String title;
	String contents;
	int viewCount;
	int likeCount;
	String originalName;
	String fileName;
	LocalDateTime createDate;
	LocalDateTime updateDate;
	
	List<ReplyDTO> replyList;
}