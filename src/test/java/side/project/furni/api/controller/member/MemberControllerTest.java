package side.project.furni.api.controller.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import side.project.furni.ControllerTestSupport;
import side.project.furni.api.controller.member.request.CreateRequest;
import side.project.furni.api.controller.member.request.LoginRequest;
import side.project.furni.api.service.member.response.LoginResponse;
import side.project.furni.common.dto.ApiResponse;
import side.project.furni.common.error.custom.DuplicateMemberException;
import side.project.furni.common.error.custom.LoginFailedApiException;

import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MemberControllerTest extends ControllerTestSupport {

    @Test
    @DisplayName("회원가입에 성공한다")
    void create_SuccessfulRegistration() throws Exception {
        // given
        CreateRequest request = new CreateRequest("test1", "test1", "홍길동");
        ApiResponse<?> response = ApiResponse.OK();
        given(memberController.create(any(CreateRequest.class)))
                .will(invocation -> response);

        // when // then
        mockMvc.perform(
                        post("/member/create")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(true))
                .andExpect(jsonPath("$.contents").value(nullValue()))
                .andExpect(jsonPath("$.error").value(nullValue()));
    }

    @Test
    @DisplayName("회원가입에 실패한다")
    void create_FailedRegistration_DuplicateMember() throws Exception {
        // given
        CreateRequest request = new CreateRequest("test", "test1", "홍길동");

        // stub
        when(memberController.create(any(CreateRequest.class)))
                .thenThrow(DuplicateMemberException.class);

        // when // then
        mockMvc.perform(
                        post("/member/create")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(jsonPath("$.result").value(false))
                .andExpect(jsonPath("$.contents").value(nullValue()))
                .andExpect(jsonPath("$.error.status").value("CONFLICT"));
    }

    @Test
    @DisplayName("회원이 로그인에 성공한다")
    void login_SuccessfulLogin() throws Exception {
        // given
        String id = "test";
        String password = "test";
        String name = "관리자";
        LoginRequest request = new LoginRequest(id, password);
        ApiResponse<?> response = ApiResponse.from(new LoginResponse(1L, id, name));
        given(memberController.login(any(LoginRequest.class)))
                .will(invocation -> response);

        // when // then
        mockMvc.perform(
                        post("/member/login")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(true))
                .andExpect(jsonPath("$.contents").isNotEmpty())
                .andExpect(jsonPath("$.contents.memberId").value(1L))
                .andExpect(jsonPath("$.contents.id").value(id))
                .andExpect(jsonPath("$.contents.name").value(name))
                .andExpect(jsonPath("$.error").value(nullValue()));
    }

    @Test
    @DisplayName("회원이 로그인에 실패한다")
    void login_FailedLogin_IncorrectCredentials() throws Exception {
        // given
        LoginRequest request = new LoginRequest("test1", "test1");

        // stub
        when(memberController.login(any(LoginRequest.class)))
                .thenThrow(LoginFailedApiException.class);

        // when // then
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