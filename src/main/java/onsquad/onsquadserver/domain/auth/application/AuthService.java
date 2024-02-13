package onsquad.onsquadserver.domain.auth.application;

import lombok.RequiredArgsConstructor;
import onsquad.onsquadserver.domain.auth.exception.AuthException;
import onsquad.onsquadserver.domain.member.presentation.dto.MemberDto;
import onsquad.onsquadserver.domain.member.repository.MemberRepository;
import onsquad.onsquadserver.infra.mail.application.AuthMailService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthMailService authMailService;
    private final MemberRepository memberRepository;

    public void joinMember(MemberDto memberDto) {
        String email = memberDto.getEmail().getValue();
        if (!authMailService.authenticated(email)) {
            throw new AuthException.NonMailAuthenticatedUser();
        }

        memberRepository.findByEmail(email).ifPresentOrElse(
                member -> { throw new AuthException.AlreadyExistsUser(); },
                () -> memberRepository.save(memberDto)
        );
    }

    public boolean checkNicknameDuplicate(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }
}
