package com.app.data.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tnc_supply_item")
public class TNCSupplyItem extends AbstractVersionedEntity{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "cnt")
    private int count;

    @ManyToOne
    @JoinColumn(name = "calc_tnc")
    private TNC tnc;

    @ManyToOne
    @JoinColumn(name = "tnc_supply")
    private TNCSupply tncSupply;

    @ManyToMany
    @JoinTable(name = "tnc_supply_request_item",
            joinColumns = @JoinColumn(name = "tnc_supply_item"),
            inverseJoinColumns = @JoinColumn(name = "tnc_request_item"))
    private List<TNCRequestItem> tncRequestItems = new ArrayList<>();

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public TNC getTnc() {
        return tnc;
    }

    public void setTnc(TNC tnc) {
        this.tnc = tnc;
    }

    public TNCSupply getTncSupply() {
        return tncSupply;
    }

    public void setTncSupply(TNCSupply tncSupply) {
        this.tncSupply = tncSupply;
    }

    public List<TNCRequestItem> getTncRequestItems() {
        return tncRequestItems;
    }

    public void setTncRequestItems(List<TNCRequestItem> tncRequestItems) {
        this.tncRequestItems = tncRequestItems;
    }
}
