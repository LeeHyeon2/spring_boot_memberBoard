package com.its.memberboard.dto;

import com.its.memberboard.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private Long id;
    private String memberEmail;

    private String memberName;

    private String memberPassword;
    private MultipartFile memberFile; // 실제 파일
    private String memberProfile; // 파일 이름

    public MemberDTO(String memberEmail, String memberName, String memberPassword, MultipartFile memberFile, String memberProfile) {
        this.memberEmail = memberEmail;
        this.memberName = memberName;
        this.memberPassword = memberPassword;
        this.memberFile = memberFile;
        this.memberProfile = memberProfile;
    }

    public static MemberDTO toDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setMemberProfile(memberEntity.getMemberProfile());
        return memberDTO;
    }
}
