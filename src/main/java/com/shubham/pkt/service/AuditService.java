package com.shubham.pkt.service;

import com.shubham.pkt.model.Note;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class AuditService {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void audit(String msg)
    {
        System.out.println("AUDIT: " + msg);
    }
}
