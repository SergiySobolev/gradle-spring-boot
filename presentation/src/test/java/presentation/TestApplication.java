package presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.support.AbstractSubscribableChannel;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import presentation.config.TestWebSocketConfig;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@Import(TestWebSocketConfig.class)
public class TestApplication extends WebMvcConfigurerAdapter {

    @Autowired
    protected AbstractSubscribableChannel clientInboundChannel;

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

}
