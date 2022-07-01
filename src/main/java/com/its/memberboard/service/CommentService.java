package com.its.memberboard.service;

import com.its.memberboard.dto.BoardDTO;
import com.its.memberboard.dto.CommentDTO;
import com.its.memberboard.entity.BoardEntity;
import com.its.memberboard.entity.CommentEntity;
import com.its.memberboard.entity.MemberEntity;
import com.its.memberboard.repository.BoardRepository;
import com.its.memberboard.repository.CommentRepository;
import com.its.memberboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    public void save(CommentDTO commentDTO) {
        // toEntity 메서드에 회원 엔티티를 같이 전달해야 함. (로그인 이메일이 작성자와 동일하다는 전제조건)
        Optional<MemberEntity> optionalMemberEntity =
                memberRepository.findByMemberEmail(commentDTO.getCommentWriter());
        Optional<BoardEntity> optionalBoardEntity=
                boardRepository.findById(commentDTO.getBoardId());

        MemberEntity memberEntity = optionalMemberEntity.get();
        BoardEntity boardEntity = optionalBoardEntity.get();

        commentRepository.save(CommentEntity.toSaveEntity(commentDTO,memberEntity,boardEntity));
    }

    public List<CommentDTO> findByBoardId(Long id) {
        List<CommentEntity> commentEntityList = commentRepository.findByBoardEntity_IdOrderByIdDesc(id);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity : commentEntityList){
            commentDTOList.add(CommentDTO.toDTO(commentEntity));
        }
        return commentDTOList;
    }
}
