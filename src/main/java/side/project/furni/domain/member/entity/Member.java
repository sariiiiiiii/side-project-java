package side.project.furni.domain.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import side.project.furni.api.service.member.request.CreateServiceRequest;
import side.project.furni.common.BaseTimeEntity;
import side.project.furni.domain.cart.entity.Cart;
import side.project.furni.util.SHA256;

import java.util.List;

@Entity
@Getter
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true, length = 10)
    private String userId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", length = 10)
    private String name;

    @OneToMany(mappedBy = "member")
    private List<Cart> carts;

    @Builder
    public Member(String userId, String password, String name) {
        this.userId = userId;
        this.password = password;
        this.name = name;
    }

    public Member(CreateServiceRequest request) {
        this.userId = request.id();
        this.password = SHA256.encrypt(request.password());
        this.name = request.name();
    }

}
