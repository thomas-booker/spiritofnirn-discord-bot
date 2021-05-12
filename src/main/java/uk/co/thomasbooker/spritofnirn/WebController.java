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

    @RequestMapping("/test")
    public String testWeb() {
//        spiritOfNirnBot.jda.getTextChannelById(826903298250113094L).sendMessage("test").queue();
        messageService.sendMessage(826903298250113094L, "Web portal test");
        return "test";
    }
}
