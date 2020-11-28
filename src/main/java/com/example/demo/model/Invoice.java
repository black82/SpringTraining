package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Service
public interface Invoice {
    String getTypeClient();

    BigDecimal getDiscount();

    Invoice getInstance( Date received, Date payingDate);

}
