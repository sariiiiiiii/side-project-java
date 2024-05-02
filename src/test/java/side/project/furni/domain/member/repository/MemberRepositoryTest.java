package side.project.furni.domain.member.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import side.project.furni.IntegrationTestSupport;
import side.project.furni.common.error.custom.DuplicateMemberException;
import side.project.furni.common.error.custom.LoginFailedApiException;
import side.project.furni.domain.member.entity.Member;
import side.project.furni.util.SHA256;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest extends IntegrationTestSupport {

    @Test
    @DisplayName("id로 회원을 조회한다")
    void findById_success() {
        // given
        String id = "test";

        // when
        Optional<Member> optionalMember = memberRepository.findById(id);

        // then
        Member member = optionalMember.orElseThrow(DuplicateMemberException::new);
        Assertions.assertThat(member.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("id로 회원을 조회하지만 실패한다")
    void findById_failure() {
        // given
        String id = "test1";

        // when
        Optional<Member> optionalMember = memberRepository.findById(id);

        // then
        Assertions.assertThat(optionalMember).isEmpty();
    }

    @Test
    @DisplayName("id와 password로 회원을 조회한다")
    void findByIdAndPassword_success() {
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
    @DisplayName("id와 password로 회원을 조회하지만 실패한다")
    void findByIdAndPassword_failure() {
        // given
        String id = "test1";
        String password = "test1";

        // when
        Optional<Member> optionalMember = memberRepository.findByIdAndPassword(id, password);

        // then
        assertThat(optionalMember).isEmpty();
    }

}