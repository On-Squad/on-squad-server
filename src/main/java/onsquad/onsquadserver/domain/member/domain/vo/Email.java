package onsquad.onsquadserver.domain.member.domain.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import onsquad.onsquadserver.domain.member.exception.MemberException;
import java.util.regex.Pattern;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Email {

    private static final String EMAIL_REGEX = "^([\\w\\.\\_\\-])*[a-zA-Z0-9]+([\\w\\.\\_\\-])*([a-zA-Z0-9])+([\\w\\.\\_\\-])+@([a-zA-Z0-9]+\\.)+[a-zA-Z0-9]{2,8}$";

    @Column(name = "email")
    private String value;

    public Email(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String value) {
        if (value == null) {
            throw new NullPointerException("이메일은 null 일 수 없습니다.");
        }
        if (invalidEmailFormat(value)) {
            throw new MemberException.InvalidEmailFormat();
        }
    }

    private boolean invalidEmailFormat(String value) {
        return !Pattern.matches(EMAIL_REGEX, value);
    }
}

