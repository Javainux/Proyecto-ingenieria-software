package com.MiDoc.Midoc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MidocApplication {

    @Bean
    public CommandLineRunner keepAlive() {
        return args -> {
            System.out.println("MiDoc está en línea. Presiona CTRL+C para terminar.");
            Thread.currentThread().join(); 
        };
    }



    public static void main(String[] args) {
        SpringApplication.run(MidocApplication.class, args);
		System.out.println("✅ MidocApplication arrancó correctamente."); 
    }
}
