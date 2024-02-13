package onsquad.onsquadserver.domain.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import onsquad.onsquadserver.domain.member.domain.vo.Email;
import onsquad.onsquadserver.domain.member.domain.vo.Nickname;
import onsquad.onsquadserver.domain.member.domain.vo.Password;
import onsquad.onsquadserver.domain.member.domain.vo.UserType;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;

    @Embedded
    private Email email;

    @Embedded
    private Nickname nickname;

    @Embedded
    private Password password;

    @Builder
    private Member(
        Long id,
        UserType userType,
        Email email,
        Nickname nickname,
        Password password
    ) {
        this.id = id;
        this.userType = userType;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }
}
