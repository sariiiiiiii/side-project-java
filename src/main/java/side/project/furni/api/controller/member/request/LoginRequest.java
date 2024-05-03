package side.project.furni.api.controller.member.request;

public record LoginRequest(
        String userId,
        String password
) {
}
