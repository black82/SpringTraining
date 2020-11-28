package com.example.demo.service;

import com.example.demo.model.Invoice;
import com.example.demo.service.ServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class Scheduling {
    @Autowired
    private ServiceTest serviceTest;
    @Scheduled(fixedDelay = 1000)
    public void getDiscount(){
        Invoice client = serviceTest.getInvoice("client");
        System.out.println(client.getDiscount());
        Invoice instance = client.getInstance(new Date(), new Date());
        System.out.println(instance.toString());

    }
}
