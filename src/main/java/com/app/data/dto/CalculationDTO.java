package com.app.data.dto;

import java.math.BigDecimal;

/**
 * Created by ayaroslavtsev on 19.09.2016.
 */
public class CalculationDTO {

    private String name;
    private BigDecimal price;

    public CalculationDTO(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
