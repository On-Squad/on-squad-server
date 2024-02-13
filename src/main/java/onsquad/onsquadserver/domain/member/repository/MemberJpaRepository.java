package onsquad.onsquadserver.domain.member.repository;

import onsquad.onsquadserver.domain.member.domain.Member;
import onsquad.onsquadserver.domain.member.domain.vo.Email;
import onsquad.onsquadserver.domain.member.domain.vo.Nickname;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    Member getByEmail(Email email);

    Optional<Member> findByEmail(Email email);

    boolean existsByNickname(Nickname nickname);

}
