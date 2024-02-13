package onsquad.onsquadserver.domain.auth.presentation.dto;

import jakarta.validation.constraints.NotEmpty;
import onsquad.onsquadserver.domain.auth.presentation.validator.PasswordComparator;
import onsquad.onsquadserver.domain.auth.presentation.validator.PasswordValidator;
import onsquad.onsquadserver.domain.member.domain.vo.Email;
import onsquad.onsquadserver.domain.member.domain.vo.Nickname;
import onsquad.onsquadserver.domain.member.domain.vo.Password;
import onsquad.onsquadserver.domain.member.presentation.dto.MemberDto;

import java.util.Objects;

@PasswordValidator
public record MemberJoinRequest(
        @jakarta.validation.constraints.Email String email,
        @NotEmpty String password,
        @NotEmpty String passwordConfirm,
        @NotEmpty String nickname
) implements PasswordComparator {

    @Override
    public boolean compare() {
        return Objects.equals(password, passwordConfirm);
    }

    public MemberDto toDto() {
        return MemberDto.builder()
                .email(new Email(email))
                .password(new Password(password))
                .nickname(new Nickname(nickname))
                .build();
    }
}
