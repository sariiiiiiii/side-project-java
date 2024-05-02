package side.project.furni.api.controller.member;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import side.project.furni.api.controller.member.request.CreateRequest;
import side.project.furni.api.controller.member.request.LoginRequest;
import side.project.furni.api.service.member.MemberService;
import side.project.furni.api.service.member.request.LoginServiceRequest;
import side.project.furni.common.dto.ApiResponse;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/create")
    public ApiResponse<?> create(@RequestBody final CreateRequest request) {
        return memberService.create(new side.project.furni.api.service.member.request.CreateServiceRequest(request));
    }

    @PostMapping("/login")
    public ApiResponse<?> login(@RequestBody final LoginRequest request) {
        return memberService.login(new LoginServiceRequest(request));
    }

}
