package com.its.memberboard.service;

import com.its.memberboard.dto.MemberDTO;
import com.its.memberboard.entity.MemberEntity;
import com.its.memberboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public String dupCheck(String email) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(email);
        if (optionalMemberEntity.isPresent()){
            return "no";
        }else{
            return "ok";
        }
    }

    public void save(MemberDTO memberDTO) throws IOException {
        MultipartFile memberFile = memberDTO.getMemberFile();
        String memberFileName = memberFile.getOriginalFilename();
        memberFileName = System.currentTimeMillis() + "_" + memberFileName;
        String savePath = "C:\\spring_img\\" + memberFileName;
        if(!memberFile.isEmpty()){
            memberFile.transferTo(new File(savePath));
        }
        memberDTO.setMemberProfile(memberFileName);


        memberRepository.save(MemberEntity.toSaveEntity(memberDTO));
    }

    public MemberDTO loginCheck(String memberEmail, String memberPassword) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(memberEmail);
        if (optionalMemberEntity.isPresent()){
            MemberEntity loginEntity = optionalMemberEntity.get();
            if (loginEntity.getMemberPassword().equals(memberPassword)){
                return MemberDTO.toDTO(loginEntity);
            }else {
                return null;
            }

        }else {
            return null;
        }
    }

    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntities = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (MemberEntity memberEntity : memberEntities){
            memberDTOList.add(MemberDTO.toDTO(memberEntity));
        }
        return memberDTOList;
    }

    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

    public MemberDTO findById(Object loginId) {
        Long id = (Long) loginId;
        Optional<MemberEntity> memberEntity = memberRepository.findById(id);
        return MemberDTO.toDTO(memberEntity.get());
    }

    public void update(MemberDTO memberDTO) throws IOException {
        MultipartFile memberFile = memberDTO.getMemberFile();
        String memberFileName = memberFile.getOriginalFilename();
        memberFileName = System.currentTimeMillis() + "_" + memberFileName;
        String savePath = "C:\\spring_img\\" + memberFileName;
        if(!memberFile.isEmpty()){
            memberFile.transferTo(new File(savePath));
            memberDTO.setMemberProfile(memberFileName);
        }else {
        }

        memberRepository.save(MemberEntity.toUpdateEntity(memberDTO));
    }
}
