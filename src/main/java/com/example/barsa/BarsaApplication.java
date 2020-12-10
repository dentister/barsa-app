package com.example.barsa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class BarsaApplication {
    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
	    context = SpringApplication.run(BarsaApplication.class, args);
	}

    @EventListener(ApplicationReadyEvent.class)
    private void processFiles() {
        System.out.println("***********************");
        System.out.println("Application was loaded.");
    }

    public static ConfigurableApplicationContext appCtx() {
        return context;
    }
}
