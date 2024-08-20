package net.datasa.ajax.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.datasa.ajax.domain.Entity.CommentEntity;

/**
 * 학생 정보 관련 Repository
 */
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

}