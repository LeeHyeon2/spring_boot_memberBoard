package com.its.memberboard.dto;

import com.its.memberboard.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private Long id;
    private String boardTitle;
    private String boardWriter;
    private String boardContents;
    private int boardHits;
    private LocalDateTime boardCreatedTime;
    private LocalDateTime updateTime;

    private MultipartFile boardFile; // 실제 파일
    private String boardFileName; // 파일 이름

    public static BoardDTO toDTO(BoardEntity entity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(entity.getId());
        boardDTO.setBoardTitle(entity.getBoardTitle());
        boardDTO.setBoardWriter(entity.getBoardWriter());
        boardDTO.setBoardContents(entity.getBoardContents());
        boardDTO.setBoardHits(entity.getBoardHits());
        boardDTO.setBoardCreatedTime(entity.getBoardCreatedTime());
        boardDTO.setBoardFileName(entity.getBoardFileName());
        return boardDTO;
    }
}
