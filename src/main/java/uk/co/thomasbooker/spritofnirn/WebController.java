package uk.co.thomasbooker.spritofnirn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @Autowired
    MessageService messageService;

    @Autowired
    SpiritOfNirnBot spiritOfNirnBot;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/test")
    public String testWeb() {
        messageService.sendMessage(826903298250113094L, "Web portal test");
        return "test";
    }

    @RequestMapping("/readme")
    public String readme() {
        return "readme";
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }
}
