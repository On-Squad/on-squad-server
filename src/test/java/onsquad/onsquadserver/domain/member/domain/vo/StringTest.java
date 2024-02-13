package onsquad.onsquadserver.domain.member.domain.vo;

import onsquad.onsquadserver.domain.member.exception.MemberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StringTest {

    private static final String INVALID_EMAIL = "invalid_email";

    @Test
    @DisplayName("[Email] email 포맷이 Invalid 할 때의 테스트")
    public void invalidEmailFormatTest() {
        assertThatThrownBy(() -> new Email(INVALID_EMAIL))
                .isExactlyInstanceOf(MemberException.InvalidEmailFormat.class)
                .isInstanceOf(RuntimeException.class)
                .hasMessage("이메일 형식과 일치하지 않습니다.");
    }
}