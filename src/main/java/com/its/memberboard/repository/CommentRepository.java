package com.its.memberboard.repository;

import com.its.memberboard.entity.CommentEntity;
import com.its.memberboard.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {

    List<CommentEntity> findByBoardEntity_IdOrderByIdDesc(Long id);
}
