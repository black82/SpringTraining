package com.example.demo.service;

import com.example.demo.model.Invoice;
import org.springframework.stereotype.Service;

@Service

public interface ServiceTest {
    Invoice getInvoice(String typeInvoice);
}
