package com.shubham.pkt.context;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class RequestContext {

    //logging constructor
    public RequestContext() {
        System.out.println("RequestContext created: " + this);
    }

}
