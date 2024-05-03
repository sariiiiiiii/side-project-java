package side.project.furni.domain.member.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import side.project.furni.IntegrationTestSupport;
import side.project.furni.common.error.custom.DuplicateMemberException;
import side.project.furni.common.error.custom.LoginFailedApiException;
import side.project.furni.common.error.custom.MemberNotFoundException;
import side.project.furni.domain.member.entity.Member;
import side.project.furni.util.SHA256;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest extends IntegrationTestSupport {

    @Test
    @DisplayName("member id로 회원을 조회한다")
    void findByMemberId_success() {
        // given
        Long memberId = 1L;

        // when
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        // then
        Member member = optionalMember.orElseThrow(MemberNotFoundException::new);
        assertThat(member.getId()).isEqualTo(memberId);
    }

    @Test
    @DisplayName("member id로 회원을 조회하지만 실패한다")
    void findByMemberId_failure() {
        // given
        Long memberId = 2L;

        // when
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        // then
        assertThat(optionalMember).isEmpty();
    }

    @Test
    @DisplayName("id로 회원을 조회한다")
    void findById_success() {
        // given
        String id = "test";

        // when
        Optional<Member> optionalMember = memberRepository.findByUserId(id);

        // then
        Member member = optionalMember.orElseThrow(DuplicateMemberException::new);
        Assertions.assertThat(member.getUserId()).isEqualTo(id);
    }

    @Test
    @DisplayName("id로 회원을 조회하지만 실패한다")
    void findById_failure() {
        // given
        String id = "test1";

        // when
        Optional<Member> optionalMember = memberRepository.findByUserId(id);

        // then
        Assertions.assertThat(optionalMember).isEmpty();
    }

    @Test
    @DisplayName("id와 password로 회원을 조회한다")
    void findByIdAndPassword_success() {
        // given
        String userId = "test";
        String password = SHA256.encrypt("test");

        // when
        Optional<Member> optionalMember = memberRepository.findByUserIdAndPassword(userId, password);

        // then
        Member findMember = optionalMember.orElseThrow(LoginFailedApiException::new);
        assertThat(findMember)
                .extracting("userId", "password")
                .containsExactly(userId, password);
    }

    @Test
    @DisplayName("id와 password로 회원을 조회하지만 실패한다")
    void findByIdAndPassword_failure() {
        // given
        String id = "test1";
        String password = "test1";

        // when
        Optional<Member> optionalMember = memberRepository.findByUserIdAndPassword(id, password);

        // then
        assertThat(optionalMember).isEmpty();
    }

}