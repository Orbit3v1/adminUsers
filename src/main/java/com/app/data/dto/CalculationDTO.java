package com.app.data.dto;

import javax.persistence.Column;
import java.math.BigDecimal;

/**
 * Created by ayaroslavtsev on 19.09.2016.
 */
public class CalculationDTO {

    private String name;
    private BigDecimal price;
    private BigDecimal count;
    private BigDecimal height;
    private BigDecimal width;
    private BigDecimal length;
    private BigDecimal formula;

    public CalculationDTO(String name, BigDecimal formula, BigDecimal price) {
        this.name = name;
        this.formula = formula;
        this.price = price;
    }

    public CalculationDTO(String name) {
        this.name = name;
    }

    public CalculationDTO() {
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

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getFormula() {
        return formula;
    }

    public void setFormula(BigDecimal formula) {
        this.formula = formula;
    }
}
