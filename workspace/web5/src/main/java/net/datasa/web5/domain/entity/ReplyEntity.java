package net.datasa.web5.domain.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString(exclude = "board")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="web5_reply")
@EntityListeners(AuditingEntityListener.class)
public class ReplyEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="reply_num")
	int replyNum;
	 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="member_id", referencedColumnName = "member_id")
	MemberEntity member;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="board_num", referencedColumnName = "board_num")
	BoardEntity board;
	
	//리플 내용
	@Column(name="contents", nullable = false, length = 2000)
	String contents;
	
	@CreatedDate
	@Column(name = "create_date", columnDefinition = "timestamp default current_timestamp", updatable = false)
	LocalDateTime createDate;
	
}
