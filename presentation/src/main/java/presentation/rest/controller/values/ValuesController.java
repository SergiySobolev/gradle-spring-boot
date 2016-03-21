package presentation.rest.controller.values;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import presentation.rest.resources.ValuesResource;

import java.util.Random;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;

@RestController
public class ValuesController {

    private Logger log = LoggerFactory.getLogger(ValuesController.class);

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @RequestMapping("/rest")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @SubscribeMapping("/value")
    public ValuesResource receiveValue(Integer value){
        log.info("Value received = " + value);
        Random r = new Random();
        Integer firstNewValue = r.nextInt(100);
        Integer secondNewValue = r.nextInt(100);
        ValuesResource valuesResource = new ValuesResource();
        valuesResource.setDateTime(new DateTime());
        valuesResource.setValues(of(firstNewValue, secondNewValue).collect(toList()));
        return valuesResource;
    }

    @Scheduled(fixedDelay = 1000)
    private void newValue(){
        Random r = new Random();
        Integer firstNewValue = r.nextInt(100);
        Integer secondNewValue = r.nextInt(100);
        ValuesResource valuesResource = new ValuesResource();
        valuesResource.setDateTime(new DateTime());
        valuesResource.setValues(of(firstNewValue, secondNewValue).collect(toList()));
        simpMessagingTemplate.convertAndSend("/topic/value", valuesResource);
        log.info("Send value: " + valuesResource.toString());
    }

}
