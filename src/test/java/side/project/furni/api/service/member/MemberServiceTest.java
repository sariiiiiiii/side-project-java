package side.project.furni.api.service.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import side.project.furni.IntegrationTestSupport;
import side.project.furni.api.service.member.request.LoginServiceRequest;
import side.project.furni.common.dto.ApiResponse;
import side.project.furni.common.error.custom.LoginFailedApiException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberServiceTest extends IntegrationTestSupport {
    
    @Test
    @DisplayName("회원이 로그인을 한다.")
    void login() {
        // given
        String id = "test";
        String password = "test";
        LoginServiceRequest request = new LoginServiceRequest(id, password);

        // when // then
        ApiResponse<?> result = memberService.login(request);
        assertThat(result.isResult()).isTrue();
    }

    @Test
    @DisplayName("회원이 로그인에 실패한다")
    void loginFailure() {
        // given
        String id = "test1";
        String password = "test1";
        LoginServiceRequest request = new LoginServiceRequest(id, password);

        // when // then
        assertThatThrownBy(() -> memberService.login(request))
                .isInstanceOf(LoginFailedApiException.class);
    }

}