package side.project.furni.api.service.member.request;

import side.project.furni.api.controller.member.request.LoginRequest;

public record LoginServiceRequest(
        String userId,
        String password
) {

    public LoginServiceRequest(LoginRequest request) {
        this(
                request.userId(),
                request.password()
        );
    }

}
