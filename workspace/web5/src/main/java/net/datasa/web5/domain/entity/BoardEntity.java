package net.datasa.web5.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="web5_board")
@EntityListeners(AuditingEntityListener.class)
public class BoardEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="board_num")
	int boardNum;
	
	//FetchType.LAZY 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="member_id", referencedColumnName = "member_id")
	MemberEntity member;
		
	//@Column(name="member_id", length = 30)
	//String memberId;
	
	@Column(name="title", nullable = false, length = 1000)
	String title;
	@Column(name="contents", nullable = false, columnDefinition = "TEXT")
	String contents;
	@Column(name="view_count", columnDefinition = "int default 0")
	int viewCount;
	@Column(name="like_count", columnDefinition = "int default 0")
	int likeCount;
	@Column(name="original_name", length = 300)
	String originalName;
	@Column(name="file_name", length = 100)
	String fileName;
	@CreatedDate
	@Column(name = "create_date", columnDefinition = "timestamp default current_timestamp", updatable = false)
	LocalDateTime createDate;
	@LastModifiedDate
	@Column(name = "update_date", columnDefinition = "timestamp default current_timestamp on update current_timestamp")
	LocalDateTime updateDate;
	
	//mappedBy
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
	List<ReplyEntity> replyList;
}
