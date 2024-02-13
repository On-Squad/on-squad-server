package onsquad.onsquadserver.domain.auth.presentation;

import onsquad.onsquadserver.common.restdocs.ApiDocumentTest;
import onsquad.onsquadserver.domain.auth.application.AuthService;
import onsquad.onsquadserver.domain.auth.presentation.dto.MemberJoinRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@DisplayName("[AUTH] Auth RestDocs 테스트")
class AuthControllerTest extends ApiDocumentTest {

    @MockBean private AuthService authService;

    @Test
    @DisplayName("[AUTH] 이메일 중복이 잘 검증되는지 테스트한다.")
    public void checkDuplicateNicknameTest() throws Exception {
        // given
        String testNickname = "testNickname";

        // when
        mockMvc.perform(get("/api/auth/check")
                    .queryParam("nickname", testNickname))
                .andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                queryParameters(
                                        parameterWithName("nickname").description("닉네임")
                                )
                        )
                );

        // then
        then(authService).should().checkNicknameDuplicate(testNickname);
    }

    @Test
    @DisplayName("[AUTH] 회원가입이 잘 되는지 테스트한다.")
    public void joinMemberTest() throws Exception {
        // given
        String testEmail = "test@email.com";
        String testPassword = "testPassword";
        String testPasswordConfirm = "testPassword";
        String testNickname = "testNickname";
        MemberJoinRequest memberJoinRequest = new MemberJoinRequest(testEmail, testPassword, testPasswordConfirm, testNickname);


        // when
        mockMvc.perform(post("/api/auth/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberJoinRequest)))
                .andExpect(status().isCreated())
                .andDo(
                        restDocs.document(
                                requestFields(
                                        fieldWithPath("email").description("이메일"),
                                        fieldWithPath("password").description("비밀번호"),
                                        fieldWithPath("passwordConfirm").description("비밀번호 확인"),
                                        fieldWithPath("nickname").description("닉네임")
                                )
                        )
                );
    }
}