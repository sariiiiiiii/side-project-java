package side.project.furni.domain.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import side.project.furni.common.BaseTimeEntity;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(name = "id", nullable = false, unique = true, length = 10)
    private String id;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", length = 10)
    private String name;

}
