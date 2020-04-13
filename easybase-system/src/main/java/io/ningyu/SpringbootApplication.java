package io.ningyu;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName: SpringbootApplication
 * @Description: 指定项目为springboot，由此类当作程序入口，自动装配 web 依赖的环境
 */
@SpringBootApplication
@ComponentScan(basePackages = {"io.ningyu.**"})
@EnableCaching
@Slf4j
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
        log.info(">>>>>>>>>>> Application Service started.");
    }
}
