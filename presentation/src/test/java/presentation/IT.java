package presentation;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(TestApplication.class)
@WebAppConfiguration
@org.springframework.boot.test.IntegrationTest("server.port:0")
public class IT {
}
