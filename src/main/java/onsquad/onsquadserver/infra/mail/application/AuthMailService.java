package onsquad.onsquadserver.infra.mail.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.Objects;

import static onsquad.onsquadserver.infra.mail.application.vo.MailStatus.*;

@Slf4j
@Service
public class AuthMailService implements RandomCodeGenerator {

    private ValueOperations<String, String> stringValueOperations;

    private final EmailSender emailSender;
    private final StringRedisTemplate stringRedisTemplate;

    private static final String KEY_FORMAT = "onsquad:auth:mail:%s";
    private static final String SUBJECT = "[ONSQUD] 회원가입 인증코드 발송 안내";

    public AuthMailService(EmailSender emailSender, StringRedisTemplate stringRedisTemplate) {
        this.emailSender = emailSender;
        this.stringRedisTemplate = stringRedisTemplate;
        this.stringValueOperations = stringRedisTemplate.opsForValue();
    }

    public void sendAuthCodeToEmail(String email) {
        String authCode = generateAuthCode();
        emailSender.sendEmail(SUBJECT, authCode, email);
        log.info("[MAIL CODE] USER : {} --> PUBLISH : {}", email, authCode);
        stringRedisTemplate
                .opsForValue()
                .set(String.format(KEY_FORMAT, email), authCode, Duration.ofMinutes(3));
    }

    public void verifyAuthCode(String email, String confirm) {
        String redisAuthKey = String.format(KEY_FORMAT, email);
        if (validMailAuthCode(confirm, redisAuthKey)) {
            log.info("[MAIL CODE] USER : {} AUTHENTICATE : FAIL", email);
            throw new RuntimeException("이메일 인증코드가 일치하지 않습니다.");
        }
        log.info("[MAIL CODE] USER : {} AUTHENTICATE : SUCCESS", email);
        stringValueOperations.setIfPresent(redisAuthKey, SUCCESS);
    }

    private boolean validMailAuthCode(String confirm, String redisAuthKey) {
        return !Objects.equals(stringValueOperations.get(redisAuthKey), confirm);
    }

    public Boolean authenticated(String email) {
        String redisAuthKey = String.format(KEY_FORMAT, email);
        return Objects.equals(stringValueOperations.get(redisAuthKey), SUCCESS);
    }
}
