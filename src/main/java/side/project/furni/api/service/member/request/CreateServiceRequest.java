package side.project.furni.api.service.member.request;

import side.project.furni.api.controller.member.request.CreateRequest;
import side.project.furni.domain.member.entity.Member;

public record CreateServiceRequest(
        String id,
        String password,
        String name
) {

    public CreateServiceRequest(CreateRequest request) {
        this(
                request.id(),
                request.password(),
                request.name()
        );
    }

    public static Member toEntity(CreateServiceRequest request) {
        return new Member(request);
    }

}
