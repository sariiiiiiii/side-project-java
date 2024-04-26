package side.project.furni.domain.member.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import side.project.furni.IntegrationTestSupport;
import side.project.furni.common.error.custom.LoginFailedApiException;
import side.project.furni.domain.member.entity.Member;
import side.project.furni.util.SHA256;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest extends IntegrationTestSupport {

    @Test
    @DisplayName("회원이 로그인에 성공한다")
    void login() {
        // given
        String id = "test";
        String password = SHA256.encrypt("test");

        // when
        Optional<Member> optionalMember = memberRepository.findByIdAndPassword(id, password);

        // then
        Member findMember = optionalMember.orElseThrow(LoginFailedApiException::new);
        assertThat(findMember)
                .extracting("id", "password")
                .containsExactly(id, password);
    }

    @Test
    @DisplayName("회원이 로그인에 실패한다")
    void failedLogin() {
        // given
        String id = "test1";
        String password = "test1";

        // when
        Optional<Member> optionalMember = memberRepository.findByIdAndPassword(id, password);

        // then
        assertThat(optionalMember).isEmpty();
    }

}