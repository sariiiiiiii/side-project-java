package side.project.furni.api.controller.member.request;

public record CreateRequest(
        String id,
        String password,
        String name
) {
}
