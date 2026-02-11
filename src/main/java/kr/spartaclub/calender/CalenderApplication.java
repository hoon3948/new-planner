package kr.spartaclub.calender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CalenderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalenderApplication.class, args);
    }

}
