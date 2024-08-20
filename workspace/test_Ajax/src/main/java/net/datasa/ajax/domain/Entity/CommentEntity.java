package net.datasa.ajax.domain.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * 학생 정보 엔티티
 */

@Data
@Entity
@Table(name="ajax_comment")
public class CommentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "num", nullable = false)
	private int num;
	
	@Column(name = "name", nullable = false, length = 30)
	private String name;
	
	@Column(name = "comment", length = 1000)
	private String comment;
}
