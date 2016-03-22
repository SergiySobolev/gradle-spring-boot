package presentation.rest.controller.values;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.joda.time.DateTime;
import org.junit.Test;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.util.JsonPathExpectationsHelper;
import presentation.IT;
import presentation.config.TestPrincipal;
import presentation.rest.resources.ValuesResource;

import java.nio.charset.Charset;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

public class ValuesControllerIT extends IT {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplateSpy;

    @Test
    public void testSendValues() throws InterruptedException {
        assertNotNull(simpMessagingTemplateSpy);
        Thread.sleep(3000);
        BaseMatcher<ValuesResource> valuesResourceBaseMatcher = new BaseMatcher<ValuesResource>(){
            @Override
            public void describeTo(Description description) {
                // nothing
            }
            @Override
            public boolean matches(Object item) {
                return item instanceof ValuesResource;
            }
        };
        verify(simpMessagingTemplateSpy, atLeast(2))
                .convertAndSend(eq("/topic/sequencevalue"), argThat(valuesResourceBaseMatcher));
    }

    @Test
    public void getValue() throws Exception {

        StompHeaderAccessor headers = StompHeaderAccessor.create(StompCommand.SUBSCRIBE);
        headers.setSubscriptionId("0");
        headers.setDestination("/app/getvalue");
        headers.setSessionId("0");
        headers.setSessionAttributes(new HashMap<>());
        Message<byte[]> message = MessageBuilder.createMessage(new ObjectMapper().writeValueAsBytes(123), headers.getMessageHeaders());

        this.clientOutboundChannelInterceptor.setIncludedDestinations("/app/getvalue");
        this.clientInboundChannel.send(message);

        Message<?> reply = this.clientOutboundChannelInterceptor.awaitMessage(5);
        assertNotNull(reply);

        StompHeaderAccessor replyHeaders = StompHeaderAccessor.wrap(reply);
        assertEquals("0", replyHeaders.getSessionId());
        assertEquals("0", replyHeaders.getSubscriptionId());
        assertEquals("/app/getvalue", replyHeaders.getDestination());

        String json = new String((byte[]) reply.getPayload(), Charset.forName("UTF-8"));
        new JsonPathExpectationsHelper("$.dateTime.era").assertValue(json, new DateTime().getEra());
    }

    @Test
    public void getValue2() throws Exception {

        StompHeaderAccessor headers = StompHeaderAccessor.create(StompCommand.SEND);
        headers.setDestination("/app/getsinglevalue");
        headers.setSessionId("0");
        headers.setSessionAttributes(new HashMap<>());
        headers.setUser(new TestPrincipal("fabrice"));
        Message<byte[]> message = MessageBuilder.createMessage(
                            new ObjectMapper().writeValueAsBytes(123), headers.getMessageHeaders());
        this.clientInboundChannel.send(message);
        this.brokerChannelInterceptor.setIncludedDestinations("/topic/singlevalue");
        Message<?> positionUpdate = this.brokerChannelInterceptor.awaitMessage(5);
        assertNotNull(positionUpdate);

        StompHeaderAccessor positionUpdateHeaders = StompHeaderAccessor.wrap(positionUpdate);

        String json = new String((byte[]) positionUpdate.getPayload(), Charset.forName("UTF-8"));
        new JsonPathExpectationsHelper("$.dateTime.era").assertValue(json, new DateTime().getEra());
        new JsonPathExpectationsHelper("$.user").assertValue(json, "fabrice");
    }
}
