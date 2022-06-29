package com.its.memberboard.service;

import com.its.memberboard.dto.BoardDTO;
import com.its.memberboard.entity.BoardEntity;
import com.its.memberboard.entity.MemberEntity;
import com.its.memberboard.repository.BoardRepository;
import com.its.memberboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    public void save(BoardDTO boardDTO) throws IOException {
        MultipartFile boardFile = boardDTO.getBoardFile();
        String boardFileName = boardFile.getOriginalFilename();
        boardFileName = System.currentTimeMillis() + "_" + boardFileName;
        String savePath = "C:\\spring_img\\" + boardFileName;
        if(!boardFile.isEmpty()){
            boardFile.transferTo(new File(savePath));
        }
        boardDTO.setBoardFileName(boardFileName);

        // toEntity 메서드에 회원 엔티티를 같이 전달해야 함. (로그인 이메일이 작성자와 동일하다는 전제조건)
        Optional<MemberEntity> optionalMemberEntity =
                memberRepository.findByMemberEmail(boardDTO.getBoardWriter());
        if (optionalMemberEntity.isPresent()){
            MemberEntity memberEntity = optionalMemberEntity.get();
            boardRepository.save(BoardEntity.toEntity(boardDTO,memberEntity));
        }
    }

    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntities = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();

        for (BoardEntity boardEntity : boardEntities){
            boardDTOList.add(BoardDTO.toDTO(boardEntity));
        }
        return boardDTOList;
    }

    public BoardDTO findById(Long id) {
        Optional<BoardEntity> boardEntity = boardRepository.findById(id);
        return BoardDTO.toDTO(boardEntity.get());
    }

    @Transactional
    public void hits(Long id) {
        boardRepository.hits(id);
    }
}
