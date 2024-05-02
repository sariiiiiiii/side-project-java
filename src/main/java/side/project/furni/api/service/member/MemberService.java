package side.project.furni.api.service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.project.furni.api.service.member.request.CreateServiceRequest;
import side.project.furni.api.service.member.request.LoginServiceRequest;
import side.project.furni.api.service.member.response.LoginResponse;
import side.project.furni.common.dto.ApiResponse;
import side.project.furni.common.error.custom.DuplicateMemberException;
import side.project.furni.common.error.custom.LoginFailedApiException;
import side.project.furni.domain.member.entity.Member;
import side.project.furni.domain.member.repository.MemberRepository;
import side.project.furni.util.SHA256;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public ApiResponse<?> create(final CreateServiceRequest request) {
        Optional<Member> optionalMember = memberRepository.findById(request.id());
        if (optionalMember.isPresent()) {
            throw new DuplicateMemberException();
        }

        memberRepository.save(CreateServiceRequest.toEntity(request));
        return ApiResponse.OK();
    }

    public ApiResponse<?> login(final LoginServiceRequest request) {
        Member findMember = memberRepository.findByIdAndPassword(request.id(), SHA256.encrypt(request.password()))
                .orElseThrow(LoginFailedApiException::new);
        return ApiResponse.from(new LoginResponse(findMember.getMemberId(), findMember.getId(), findMember.getName()));
    }

}
