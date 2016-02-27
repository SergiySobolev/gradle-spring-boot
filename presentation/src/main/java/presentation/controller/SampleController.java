package presentation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @RequestMapping("/rest")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
