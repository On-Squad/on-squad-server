package onsquad.onsquadserver.infra.mail.presentation;

import onsquad.onsquadserver.common.restdocs.ApiDocumentTest;
import onsquad.onsquadserver.infra.mail.application.AuthMailService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.BDDMockito.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthMailController.class)
@DisplayName("[MAIL] 메일 전송 테스트")
class AuthMailControllerTest extends ApiDocumentTest {

    @MockBean private AuthMailService authMailService;

    @Test
    @DisplayName("[Email] 회원가입 이메일 인증코드가 전송되는지 테스트한다.")
    public void sendEmailTest() throws Exception {
        // given
        String testEmail = "david122123@gmail.com";
        String testAuthCode = "auth_code";
        given(authMailService.generateAuthCode()).willReturn(testAuthCode);
        willDoNothing().given(authMailService).sendAuthCodeToEmail(testEmail);

        // when
        mockMvc.perform(get("/api/mail/auth")
                        .queryParam("email", testEmail))
                .andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                queryParameters(
                                        parameterWithName("email").description("이메일")
                                )
                        )
                );

        // then
        then(authMailService).should().sendAuthCodeToEmail(testEmail);
    }

    @Test
    @DisplayName("[Email] 회원가입 이메일 인증코드가 검증되는지 테스트한다.")
    public void verifyEmailTest() throws Exception {
        // given
        String testEmail = "david122123@gmail.com";
        String testAuthCode = "auth_code";
        given(authMailService.generateAuthCode()).willReturn(testAuthCode);

        // when
        mockMvc.perform(get("/api/mail/auth")
                        .queryParam("email", testEmail)
                        .queryParam("confirm", testAuthCode))
                .andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                queryParameters(
                                        parameterWithName("email").description("이메일"),
                                        parameterWithName("confirm").description("인증코드")
                                )
                        )
                );

        // then
        then(authMailService).should().verifyAuthCode(testEmail, testAuthCode);
    }
}
