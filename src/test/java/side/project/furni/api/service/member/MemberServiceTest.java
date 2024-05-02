package side.project.furni.api.service.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import side.project.furni.IntegrationTestSupport;
import side.project.furni.api.service.member.request.CreateServiceRequest;
import side.project.furni.api.service.member.request.LoginServiceRequest;
import side.project.furni.api.service.member.response.LoginResponse;
import side.project.furni.common.dto.ApiResponse;
import side.project.furni.common.error.custom.DuplicateMemberException;
import side.project.furni.common.error.custom.LoginFailedApiException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberServiceTest extends IntegrationTestSupport {

    @Test
    @DisplayName("회원가입에 성공한다")
    void create_SuccessfulRegistration() {
        // given
        CreateServiceRequest request = new CreateServiceRequest("admin", "admin", "홍길동");

        // when // then
        ApiResponse<?> apiResponse = memberService.create(request);
        assertThat(apiResponse.isResult()).isTrue();
    }

    @Test
    @DisplayName("회원가입에 실패한다")
    void create_FailedRegistration_DuplicateMember() {
        // given
        CreateServiceRequest request = new CreateServiceRequest("test", "test", "관리자");

        // when // then
        assertThatThrownBy(() -> memberService.create(request))
                .isInstanceOf(DuplicateMemberException.class);
    }
    
    @Test
    @DisplayName("회원이 로그인을 한다.")
    void login_SuccessfulLogin() {
        // given
        String id = "test";
        String password = "test";
        LoginServiceRequest request = new LoginServiceRequest(id, password);

        // when // then
        ApiResponse<?> result = memberService.login(request);
        assertThat(result.isResult()).isTrue();
        assertThat(result.getContents()).isNotNull();
    }

    @Test
    @DisplayName("회원이 로그인에 실패한다")
    void login_FailedLogin_IncorrectCredentials() {
        // given
        String id = "test1";
        String password = "test1";
        LoginServiceRequest request = new LoginServiceRequest(id, password);

        // when // then
        assertThatThrownBy(() -> memberService.login(request))
                .isInstanceOf(LoginFailedApiException.class);
    }

}