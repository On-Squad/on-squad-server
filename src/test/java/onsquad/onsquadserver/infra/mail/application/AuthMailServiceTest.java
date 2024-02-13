package onsquad.onsquadserver.infra.mail.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import java.time.Duration;

import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class AuthMailServiceTest {

    @InjectMocks private AuthMailService authMailService;
    @Mock StringRedisTemplate stringRedisTemplate;
    @Mock private EmailSender emailSender;
    @Mock private ValueOperations<String, String> valueOperations;

    @Test
    public void generateAuthCodeTest() {
        String testEmail = "david122123@gmail.com";
        given(stringRedisTemplate.opsForValue()).willReturn(valueOperations);
        willDoNothing().given(valueOperations).set(anyString(), anyString(), any(Duration.class));

        authMailService.sendAuthCodeToEmail(testEmail);

        then(emailSender)
                .should().sendEmail(anyString(), anyString(), anyString());
        then(valueOperations)
                .should().set(anyString(), anyString(), any(Duration.class));
    }
}