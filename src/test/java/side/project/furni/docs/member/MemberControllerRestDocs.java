package side.project.furni.docs.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import side.project.furni.api.controller.member.MemberController;
import side.project.furni.api.controller.member.request.LoginRequest;
import side.project.furni.api.service.member.MemberService;
import side.project.furni.api.service.member.request.LoginServiceRequest;
import side.project.furni.common.dto.ApiResponse;
import side.project.furni.docs.RestDocsSupport;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MemberControllerRestDocs extends RestDocsSupport {

    private final MemberService memberService = mock(MemberService.class);

    @Override
    protected Object initController() {
        return new MemberController(memberService);
    }

    @Test
    @DisplayName("로그인을 하는 API")
    void login() throws Exception {
        // stub
        given(memberService.login(any(LoginServiceRequest.class)))
                .will(invocation -> ApiResponse.OK());

        LoginRequest loginRequest = new LoginRequest("honggildong", "honggildong1234");

        mockMvc.perform(
                MockMvcRequestBuilders.post("/member/login")
                        .content(objectMapper.writeValueAsString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document(
                        "login",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("id").type(JsonFieldType.STRING)
                                        .description("아이디"),
                                fieldWithPath("password").type(JsonFieldType.STRING)
                                        .description("비밀번호")
                        ),
                        responseFields(
                                fieldWithPath("result").type(JsonFieldType.BOOLEAN)
                                        .description("결과"),
                                fieldWithPath("contents").type(JsonFieldType.NULL)
                                        .description("결과 데이터").optional(),
                                fieldWithPath("error").type(JsonFieldType.NULL)
                                        .description("에러").optional()
                        )
                ));
    }

}
