package com.its.memberboard.entity;

import com.its.memberboard.dto.CommentDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "memberBoard_comment")
public class CommentEntity {
    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;
    @Column(name = "comment_writer")
    private String commentWriter;
    @Column
    private String commentContents;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime commentCreatedTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    public static CommentEntity toSaveEntity(CommentDTO commentDTO, MemberEntity memberEntity, BoardEntity boardEntity) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentWriter(commentDTO.getCommentWriter());
        commentEntity.setCommentContents(commentDTO.getCommentContents());
        commentEntity.setBoardEntity(boardEntity);
        commentEntity.setMemberEntity(memberEntity);
        return commentEntity;
    }
}
