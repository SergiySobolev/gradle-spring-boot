package dao;

import dao.config.TestDaoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@ComponentScan
@Import(TestDaoConfig.class)
public class DaoTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(DaoTestApplication.class, args);
    }
}
