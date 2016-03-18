package presentation.config;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.simp.annotation.support.SimpAnnotationMethodMessageHandler;

import java.util.List;

@Configuration
@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
@ComponentScan("presentation")
public class TestAppConfig implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private List<SubscribableChannel> channels;

    @Autowired
    private List<MessageHandler> handlers;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        for (MessageHandler handler : handlers) {
            if (handler instanceof SimpAnnotationMethodMessageHandler) {
                continue;
            }
            for (SubscribableChannel channel :channels) {
                channel.unsubscribe(handler);
            }
        }
    }

    @Bean
    public DateTimeFormatter dateTimeFormatter(){
        String PATTERN = "MM/dd/yyyy HH:mm:ss";
        return DateTimeFormat.forPattern(PATTERN);
    }
}
