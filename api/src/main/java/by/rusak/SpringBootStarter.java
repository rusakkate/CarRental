package by.rusak;

import by.rusak.configuration.PersistenceProvidersConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "by.rusak")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableCaching
@EnableSwagger2
@EnableTransactionManagement
//@Import({PersistenceProvidersConfiguration.class})
public class SpringBootStarter {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarter.class, args);
    }
}