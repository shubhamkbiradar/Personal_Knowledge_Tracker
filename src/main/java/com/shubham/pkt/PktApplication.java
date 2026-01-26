package com.shubham.pkt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PktApplication {

    public static void main(String[] args) {
        SpringApplication.run(PktApplication.class, args);

        // If you previously used an AnnotationConfigApplicationContext for manual wiring,
        // that approach does not start an HTTP server. The Spring Boot entrypoint above is required
        // to auto-configure and start Tomcat + DispatcherServlet.

        // Optional: you can still obtain beans from the ApplicationContext if needed:
        // var ctx = new AnnotationConfigApplicationContext(CoreConfig.class);
        // var service = ctx.getBean(NoteService.class);
        // service.create("first note");
        // ctx.close();
    }
}
