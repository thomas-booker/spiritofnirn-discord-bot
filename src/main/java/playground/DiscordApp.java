package playground;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.security.auth.login.LoginException;

@SpringBootApplication
@ComponentScan("playground")
public class DiscordApp {

    public static void main(String[] args) {
        SpringApplication.run(DiscordApp.class, args);
    }

    @Bean
    public JDA jda(ModalSetupListener modalSetupListener) throws LoginException {
        return JDABuilder.createDefault("OTgwNTQ4NTg3MTY1NDU0Mzk4.GbJFjY.o_KW-Ma-AVt4jW82EzECI2HpLFXcfOL83rvOf4")
                .addEventListeners(modalSetupListener)
                .build();
    }

}
