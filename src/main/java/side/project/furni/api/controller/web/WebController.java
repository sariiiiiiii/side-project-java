package side.project.furni.api.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping({"/", "/docs"})
    public String docs() {
        return "index.html";
    }

}
