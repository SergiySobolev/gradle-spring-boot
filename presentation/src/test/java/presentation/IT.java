package presentation;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.messaging.support.AbstractSubscribableChannel;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import presentation.config.TestChannelInterceptor;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(PresentationTestApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
abstract public class IT {

    @Autowired
    protected AbstractSubscribableChannel clientInboundChannel;

    @Autowired
    protected AbstractSubscribableChannel clientOutboundChannel;

    @Autowired
    protected AbstractSubscribableChannel brokerChannel;

    protected TestChannelInterceptor clientOutboundChannelInterceptor;

    protected TestChannelInterceptor brokerChannelInterceptor;

    @Before
    public void setUp() throws Exception {

        this.brokerChannelInterceptor = new TestChannelInterceptor();
        this.clientOutboundChannelInterceptor = new TestChannelInterceptor();

        this.brokerChannel.addInterceptor(this.brokerChannelInterceptor);
        this.clientOutboundChannel.addInterceptor(this.clientOutboundChannelInterceptor);
    }


}
