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

    public BoardDTO(Long id, String boardTitle, String boardWriter, int boardHits, LocalDateTime boardCreatedTime) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardWriter = boardWriter;
        this.boardHits = boardHits;
        this.boardCreatedTime = boardCreatedTime;
    }


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
