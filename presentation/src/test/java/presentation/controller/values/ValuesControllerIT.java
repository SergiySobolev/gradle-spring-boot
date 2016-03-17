package presentation.controller.values;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageBuilder;
import presentation.IT;

import java.nio.charset.Charset;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

public class ValuesControllerIT extends IT {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplateSpy;

    @Test
    public void testSendValues() throws InterruptedException {
        assertNotNull(simpMessagingTemplateSpy);
        Thread.sleep(3000);
        verify(simpMessagingTemplateSpy, atLeast(2)).convertAndSend(eq("/topic/value"), anyInt());
    }


    @Test
    public void getValue() throws Exception {

        StompHeaderAccessor headers = StompHeaderAccessor.create(StompCommand.SUBSCRIBE);
        headers.setSubscriptionId("0");
        headers.setDestination("/app/value");
        headers.setSessionId("0");
        headers.setSessionAttributes(new HashMap<>());
        Message<byte[]> message = MessageBuilder.createMessage(new ObjectMapper().writeValueAsBytes(123), headers.getMessageHeaders());

        this.clientOutboundChannelInterceptor.setIncludedDestinations("/app/value");
        this.clientInboundChannel.send(message);

        Message<?> reply = this.clientOutboundChannelInterceptor.awaitMessage(5);
        assertNotNull(reply);

        StompHeaderAccessor replyHeaders = StompHeaderAccessor.wrap(reply);
        assertEquals("0", replyHeaders.getSessionId());
        assertEquals("0", replyHeaders.getSubscriptionId());
        assertEquals("/app/value", replyHeaders.getDestination());

        String json = new String((byte[]) reply.getPayload(), Charset.forName("UTF-8"));
        assertThat(Integer.parseInt(json), equalTo(246));
    }
}
