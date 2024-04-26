package side.project.furni.api.service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.project.furni.api.service.member.request.LoginServiceRequest;
import side.project.furni.common.dto.ApiResponse;
import side.project.furni.common.error.custom.LoginFailedApiException;
import side.project.furni.domain.member.repository.MemberRepository;
import side.project.furni.util.SHA256;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public ApiResponse<?> login(final LoginServiceRequest request) {
        memberRepository.findByIdAndPassword(request.id(), SHA256.encrypt(request.password()))
                .orElseThrow(LoginFailedApiException::new);

        return ApiResponse.OK();
    }

}
