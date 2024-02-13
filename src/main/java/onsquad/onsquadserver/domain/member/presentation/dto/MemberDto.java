package onsquad.onsquadserver.domain.member.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import onsquad.onsquadserver.domain.member.domain.Member;
import onsquad.onsquadserver.domain.member.domain.vo.Email;
import onsquad.onsquadserver.domain.member.domain.vo.Nickname;
import onsquad.onsquadserver.domain.member.domain.vo.Password;
import onsquad.onsquadserver.domain.member.domain.vo.UserType;

@Getter
public class MemberDto {

    private Long id;
    private UserType userType;
    private Email email;
    private Nickname nickname;
    private Password password;

    @Builder
    public MemberDto(
        Long id,
        UserType userType,
        Email email,
        Nickname nickname,
        Password password
    ) {
        this.id = id;
        this.userType = userType;
        this.email = email ;
        this.nickname = nickname;
        this.password = password;
    }

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .userType(userType)
                .email(email)
                .nickname(nickname)
                .password(password)
                .build();
    }
}

