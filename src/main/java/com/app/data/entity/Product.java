package com.app.data.entity;

import com.app.data.entity.interfaces.Copy;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "calc_product")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",discriminatorType= DiscriminatorType.STRING)
@DiscriminatorValue("PRODUCT")
public class Product extends AbstractVersionedEntity implements Copy<Product>, Cloneable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "detail")
    private String detail;

    @Column(name = "description")
    private String description;

    @Column(name = "height")
    private String height;

    @Column(name = "width")
    private String width;

    @Column(name = "length")
    private String length;

    @Column(name = "count")
    private String count;

    @Column(name = "heightAlias")
    private String heightAlias;

    @Column(name = "widthAlias")
    private String widthAlias;

    @Column(name = "lengthAlias")
    private String lengthAlias;

    @Column(name = "countAlias")
    private String countAlias;

    @Column(name = "formula")
    private String formula;

    @Column(name = "type", insertable = false, updatable = false)
    private String type = "PRODUCT";

    @ManyToOne
    @JoinColumn(name="parentId")
    private Product parent;

    @OneToMany(mappedBy="parent", fetch = FetchType.EAGER, cascade={CascadeType.ALL}, orphanRemoval = true)
    private List<Product> subordinates = new ArrayList<>();

    @OneToMany(mappedBy="product", fetch = FetchType.LAZY, cascade={CascadeType.ALL}, orphanRemoval = true)
    private List<ProductInParameter> inParameters = new ArrayList<>();

    public Product(){
        super();
        this.heightAlias = "H";
        this.widthAlias = "B";
        this.lengthAlias = "L";
        this.countAlias = "N";
    }


    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getFormula() {
        return formula == null ? "" : formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Product getParent() {
        return parent;
    }

    public void setParent(Product parent) {
        this.parent = parent;
    }

    public List<Product> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(List<Product> subordinates) {
        this.subordinates = subordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<ProductInParameter> getInParameters() {
        return inParameters;
    }

    public void setInParameters(List<ProductInParameter> inParameters) {
        this.inParameters = inParameters;
    }

    public String getHeightAlias() {
        return heightAlias;
    }

    public void setHeightAlias(String heightAlias) {
        this.heightAlias = heightAlias;
    }

    public String getWidthAlias() {
        return widthAlias;
    }

    public void setWidthAlias(String widthAlias) {
        this.widthAlias = widthAlias;
    }

    public String getLengthAlias() {
        return lengthAlias;
    }

    public void setLengthAlias(String lengthAlias) {
        this.lengthAlias = lengthAlias;
    }

    public String getCountAlias() {
        return countAlias;
    }

    public void setCountAlias(String countAlias) {
        this.countAlias = countAlias;
    }

    public Product copy(){
        Product copy = new Product();
        copy.name = this.name;
        copy.id = this.id;
        copy.setVersion(this.getVersion());
        return copy;
    }

    public void copyData(Product copy){
        this.id = copy.id;
        this.setVersion(copy.getVersion());
        this.name = copy.name;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Product product = (Product) super.clone();
        if(product.parent == null){
            product.name = this.name + "_" + getNameSuffix();
        }
        product.setId(0);
        product.setVersion(this.getVersion());
        product.detail = this.detail;
        product.description = this.description;
        product.height = this.height;
        product.width = this.width;
        product.length = this.length;
        product.count = this.count;
        product.heightAlias = this.heightAlias;
        product.widthAlias = this.widthAlias;
        product.lengthAlias = this.lengthAlias;
        product.countAlias = this.countAlias;
        product.formula = this.formula;
        product.type = this.type;
        List<Product> subordinates = new ArrayList<>();
        for(Product p : this.subordinates){
            Product subProduct = (Product) p.clone();
            subProduct.parent = product;
            subordinates.add(subProduct);
        }
        product.subordinates = subordinates;
        List<ProductInParameter> inParameters = new ArrayList<>();
        for(ProductInParameter p : this.inParameters){
            ProductInParameter inParameter = (ProductInParameter) p.clone();
            inParameter.setProduct(product);
            inParameters.add(inParameter);
        }
        product.inParameters = inParameters;
        return product;
    }

    private String getNameSuffix(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        return df.format(today);
    }
}



