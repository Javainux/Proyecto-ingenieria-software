package com.MiDoc.Midoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MidocApplication {

    public static void main(String[] args) {
        String port = System.getenv("PORT");
        if (port != null) {
            System.setProperty("server.port", port);
        }
        SpringApplication.run(MidocApplication.class, args);
    }
}
