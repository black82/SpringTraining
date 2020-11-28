package com.example.demo.model;


import com.example.demo.anotation.DateAfterPeriod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class InvoicePartner implements Invoice {
    private String type;
    @DateAfterPeriod(periodMonth = 2)
    private Date activePeriod;
    private Date received;
    private Date payingDate;

    @Override
    public BigDecimal getDiscount() {
        return new BigDecimal("0.33");
    }

    @Override
    public Invoice getInstance(final Date received, final Date payingDate) {
        return new InvoicePartner(this.getTypeClient(), activePeriod, received, payingDate);

    }

    @Override
    public String getTypeClient() {
        return "partner";
    }
}
