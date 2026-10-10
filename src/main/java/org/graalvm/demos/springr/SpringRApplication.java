package org.graalvm.demos.springr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringRApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringRApplication.class, args);
    }
}
