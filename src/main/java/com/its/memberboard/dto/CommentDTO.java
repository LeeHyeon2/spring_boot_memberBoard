package com.its.memberboard.dto;

import com.its.memberboard.entity.BoardEntity;
import com.its.memberboard.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private Long boardId;
    private String commentWriter;
    private String commentContents;
    private LocalDateTime createdTime;


    public static CommentDTO toDTO(CommentEntity entity){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(entity.getId());
        commentDTO.setBoardId(entity.getBoardEntity().getId());
        commentDTO.setCommentWriter(entity.getCommentWriter());
        commentDTO.setCommentContents(entity.getCommentContents());
        commentDTO.setCreatedTime(entity.getCommentCreatedTime());
        return commentDTO;
    }
}
