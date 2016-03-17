package presentation.spy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static org.mockito.Mockito.spy;

@Configuration
public class SimpMessagingTemplateSpy {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Bean
    @Primary
    public SimpMessagingTemplate registerSimpMessagingTemplateSpy() {
        return spy(simpMessagingTemplate);
    }
}
