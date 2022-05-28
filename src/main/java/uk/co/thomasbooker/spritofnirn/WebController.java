package uk.co.thomasbooker.spritofnirn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @Autowired
    MessageService messageService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/test")
    public String testWeb() {
        messageService.sendMessage(826903298250113094L, "Web portal test");
        return "test";
    }

    @RequestMapping("/instructions")
    public String readme() {
        return "instructions";
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }

    @RequestMapping("/admins")
    public String admins() {
        return "admins";
    }
}
