package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order extends AbstractVersionedEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "customer")
    private String customer;

    @Column(name = "cnt")
    private int count;

    @Column(name = "material")
    private String material;

    @Column(name = "gib")
    private String gib;

    @Column(name = "start")
    private Date start;

    @Column(name = "docDate")
    private Date docDate;

    @Column(name = "endPlan")
    private Date endPlan;

    @Column(name = "endActual")
    private Date endActual;

    @ManyToOne
    @JoinColumn(name = "nomenclature")
    private Nomenclature nomenclature;

    @ManyToOne
    @JoinColumn(name = "responsible")
    private Person responsible;

    @ManyToOne
    @JoinColumn(name = "developer")
    private Person developer;

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getGib() {
        return gib;
    }

    public void setGib(String gib) {
        this.gib = gib;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public Date getEndPlan() {
        return endPlan;
    }

    public void setEndPlan(Date endPlan) {
        this.endPlan = endPlan;
    }

    public Date getEndActual() {
        return endActual;
    }

    public void setEndActual(Date endActual) {
        this.endActual = endActual;
    }

    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
    }

    public Person getResponsible() {
        return responsible;
    }

    public void setResponsible(Person responsible) {
        this.responsible = responsible;
    }

    public Person getDeveloper() {
        return developer;
    }

    public void setDeveloper(Person developer) {
        this.developer = developer;
    }
}
