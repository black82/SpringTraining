package com.example.demo.service;

import com.example.demo.anotation.PostProxy;
import com.example.demo.anotation.Profiling;
import com.example.demo.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Component
@Profiling
public class Service implements ServiceTest {
    @Autowired
    List<Invoice> invoice;

    @Override
    public Invoice getInvoice(String typeInvoice) {
        Map<String, Invoice> map = invoice
                .stream()
                .collect(toMap(Invoice::getTypeClient, invoice1 -> invoice1));
        Invoice invoice = map.get(typeInvoice);
        if (invoice == null) {
            throw new UnsupportedOperationException("This type unsupported " + typeInvoice);
        } else {
            return invoice;
        }
    }
    @PostProxy
    @Override
    public void contextListener(){
        System.out.println("******************** Listener Refresh ***********************");
    }
}
