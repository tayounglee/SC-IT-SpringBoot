package net.datasa.web5.domain.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO {
	int replyNum;
	int boardNum;
	String memberId;
	String memberName;
	String contents;
	LocalDateTime createDate;
}
