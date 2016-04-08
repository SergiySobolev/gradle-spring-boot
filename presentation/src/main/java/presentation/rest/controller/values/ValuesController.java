package presentation.rest.controller.values;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import presentation.rest.resources.MyMessage;
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

    @SubscribeMapping("/getvalue")
    public ValuesResource receiveValue(Integer value){
        log.info("Value received = " + value);
        return getValuesResource();
    }

    @MessageMapping("/singlevalue")
    @SendTo("/topic/singlevalue")
    public ValuesResource prepareAndSendSingleValue() {
        return getValuesResource();
    }

    @Scheduled(fixedDelay = 5000)
    private void newSequenceValue(){
        ValuesResource valuesResource = getValuesResource();
        simpMessagingTemplate.convertAndSend("/topic/sequencevalue", valuesResource);
        log.info("Send value: " + valuesResource.toString());
    }

    private ValuesResource getValuesResource() {
        Random r = new Random();
        Integer firstNewValue = r.nextInt(100);
        Integer secondNewValue = r.nextInt(100);
        ValuesResource valuesResource = new ValuesResource();
        valuesResource.setDateTime(new DateTime());
        valuesResource.setValues(of(firstNewValue, secondNewValue).collect(toList()));
        return valuesResource;
    }

}
