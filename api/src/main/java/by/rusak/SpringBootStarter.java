package by.rusak;

import by.rusak.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "by.rusak")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableCaching
@EnableSwagger2
@EnableTransactionManagement
public class SpringBootStarter {

    @Autowired
    private OrderService service;
    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarter.class, args);
    }
}