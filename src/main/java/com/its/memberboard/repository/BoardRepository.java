package com.its.memberboard.repository;

import com.its.memberboard.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
    @Modifying
    @Query(value = "update BoardEntity b set b.boardHits = b.boardHits + 1 where b.id = :id")
    int hits(@Param("id") Long id);

    // 제목에서 검색
    Page<BoardEntity> findByBoardTitleContaining(String q, Pageable pageable);

    // 작성자 검색
    Page<BoardEntity> findByBoardWriterContaining(String q, Pageable pageable);

    // 전체검색
    Page<BoardEntity> findByBoardTitleContainingOrBoardWriterContaining(String q , String q2 , Pageable pageable);

}
