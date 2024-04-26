package side.project.furni.api.service.member.request;

import side.project.furni.api.controller.member.request.LoginRequest;

public record LoginServiceRequest(
        String id,
        String password
) {

    public LoginServiceRequest(LoginRequest request) {
        this(
                request.id(),
                request.password()
        );
    }

}
