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
public class InvoiceClient implements Invoice {
    private String type;
    @lombok.Getter
    @DateAfterPeriod(periodMonth = 1)
    private Date activePeriod;
    private Date received;
    private Date payingDate;

    @Override
    public BigDecimal getDiscount() {
        return BigDecimal.valueOf(0.22);
    }

    @Override
    public Invoice getInstance(final Date received, final Date payingDate) {
        return new InvoiceClient(this.getTypeClient(),activePeriod, received, payingDate);

    }

    public String getTypeClient() {
        return "client";
    }
}
