package side.project.furni.api.controller.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import side.project.furni.ControllerTestSupport;
import side.project.furni.api.controller.member.request.LoginRequest;
import side.project.furni.common.error.custom.LoginFailedApiException;

import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MemberControllerTest extends ControllerTestSupport {

    @Test
    @DisplayName("회원이 로그인에 성공한다")
    void login() throws Exception {
        // given
        LoginRequest request = new LoginRequest("test", "test");

        // when // then
        mockMvc.perform(
                post("/member/login")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원이 로그인에 실패한다")
    void loginFailure() throws Exception {
        // given
        LoginRequest request = new LoginRequest("test1", "test1");

        // when
        Mockito.when(memberController.login(ArgumentMatchers.any(LoginRequest.class)))
                        .thenThrow(LoginFailedApiException.class);

        // then
        mockMvc.perform(
                post("/member/login")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(false))
                .andExpect(jsonPath("$.contents").value(nullValue()))
                .andExpect(jsonPath("$.error.status").value("UNAUTHORIZED"));
    }

}