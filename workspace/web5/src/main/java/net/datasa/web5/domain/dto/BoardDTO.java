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
public class BoardDTO {
	int num;
	String memberid;
	String title;
	String contents;
	int viewcount;
	int likecount;
	String originalname;
	String filename;
	LocalDateTime createdate;
	LocalDateTime updatedate;
}