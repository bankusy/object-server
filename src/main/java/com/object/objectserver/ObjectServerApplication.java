package com.object.objectserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ObjectServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ObjectServerApplication.class, args);
    }

}
