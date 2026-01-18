package com.shubham.pkt;

import com.shubham.pkt.config.CoreConfig;
import com.shubham.pkt.service.NoteService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PktApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(CoreConfig.class);

        // optional: exercise the graph
        var service = ctx.getBean(NoteService.class);
        service.create("first note");
        service.create("second note");

        ctx.close();
    }
}
