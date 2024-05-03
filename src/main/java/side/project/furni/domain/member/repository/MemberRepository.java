package side.project.furni.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import side.project.furni.domain.member.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findById(Long id);

    Optional<Member> findByUserId(String userId);

    Optional<Member> findByUserIdAndPassword(String userId, String password);

}
