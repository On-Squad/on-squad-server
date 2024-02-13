package onsquad.onsquadserver.domain.member.repository;

import lombok.RequiredArgsConstructor;
import onsquad.onsquadserver.domain.member.domain.Member;
import onsquad.onsquadserver.domain.member.domain.vo.Email;
import onsquad.onsquadserver.domain.member.domain.vo.Nickname;
import onsquad.onsquadserver.domain.member.exception.MemberException;
import onsquad.onsquadserver.domain.member.presentation.dto.MemberDto;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Member save(MemberDto memberDto) {
        return memberJpaRepository.save(memberDto.toEntity());
    }

    @Override
    public Member getById(Long memberId) {
        return memberJpaRepository.findById(memberId)
                .orElseThrow(MemberException.NotFound::new);
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        return memberJpaRepository.findById(memberId);
    }

    @Override
    public Member getByEmail(String email) {
        return memberJpaRepository.findByEmail(new Email(email))
                .orElseThrow(MemberException.NotFound::new);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return memberJpaRepository.findByEmail(new Email(email));
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return memberJpaRepository.existsByNickname(new Nickname(nickname));
    }
}
