package presentation.controller.values;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import presentation.IT;

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
}
