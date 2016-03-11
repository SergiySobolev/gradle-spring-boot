package presentation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebMvc
@Profile("dev")
public class DevWebConfig extends WebConfig {

    @PostConstruct
    public void initDev() throws Exception {
        location = "file:///" + getProjectRootRequired() + "/web/dist/";
        cachePeriod = 0;
        useResourceCache = false;
        version = "dev";
    }

}
