package presentation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebMvc
@Profile("prod")
public class ProdWebConfig extends WebConfig{

    @PostConstruct
    @Profile("prod")
    public void initProd() throws Exception {
        location = "classpath:static/";
        cachePeriod = null;
        useResourceCache = true;
        version = this.appVersion;
    }

}
