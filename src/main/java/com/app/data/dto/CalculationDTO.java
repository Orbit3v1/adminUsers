package com.app.data.dto;

import com.app.data.entity.Product;
import com.app.utils.AppUtil;

import javax.persistence.Column;
import java.math.BigDecimal;

/**
 * Created by ayaroslavtsev on 19.09.2016.
 */
public class CalculationDTO {

    private Product product;
    private String name;
    private String units;
    private BigDecimal price;
    private BigDecimal count;
    private BigDecimal height;
    private BigDecimal width;
    private BigDecimal length;
    private BigDecimal formula;
    private BigDecimal formulaConverted;

    public CalculationDTO(CalculationDTO dto) {
        this.name = dto.name;
        this.product = dto.product;
        this.units = dto.units;
        this.price = dto.price;
        this.formula = dto.formula;
        this.formulaConverted = dto.formulaConverted;
    }

    public CalculationDTO(Product product) {
        this.product = product;
    }

    public CalculationDTO(String name) {
        this.name = name;
    }

    public void merge(CalculationDTO dto){
        this.price = AppUtil.add(this.price, dto.price);
        this.formula = AppUtil.add(this.formula, dto.formula);
        this.formulaConverted = AppUtil.add(this.formulaConverted, dto.formulaConverted);
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

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public BigDecimal getFormulaConverted() {
        return formulaConverted;
    }

    public void setFormulaConverted(BigDecimal formulaConverted) {
        this.formulaConverted = formulaConverted;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
