package onsquad.onsquadserver.domain.member.repository;

import onsquad.onsquadserver.domain.member.domain.Member;
import onsquad.onsquadserver.domain.member.presentation.dto.MemberDto;

import java.util.Optional;

public interface MemberRepository {

    Member save(MemberDto memberDto);

    Member getById(Long memberId);

    Member getByEmail(String string);

    Optional<Member> findById(Long memberId);

    Optional<Member> findByEmail(String email);

    boolean existsByNickname(String nickname);

}