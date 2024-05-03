package side.project.furni;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import side.project.furni.api.service.member.MemberService;
import side.project.furni.domain.cart.repository.CartRepository;
import side.project.furni.domain.member.repository.MemberRepository;
import side.project.furni.domain.product.repository.ProductRepository;

@SpringBootTest
public abstract class IntegrationTestSupport {

    @Autowired
    protected MemberService memberService;
    @Autowired
    protected MemberRepository memberRepository;
    @Autowired
    protected CartRepository cartRepository;
    @Autowired
    protected ProductRepository productRepository;

}
