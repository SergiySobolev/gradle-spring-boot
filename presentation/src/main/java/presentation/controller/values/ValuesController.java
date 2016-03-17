package presentation.controller.values;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class ValuesController {

    private Logger log = LoggerFactory.getLogger(ValuesController.class);

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @RequestMapping("/rest")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @MessageMapping("/value")
    public void receiveColor(Integer value){
        log.info("Value received = " + value);
    }

    @Scheduled(fixedDelay = 1000)
    private void newValue(){
        Random r = new Random();
        Integer newValue = r.nextInt(100);
        simpMessagingTemplate.convertAndSend("/topic/value", newValue);
        log.info("Send value: " + newValue);
    }

}
