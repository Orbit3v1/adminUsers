package com.app.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "tnc_link")
public class TNCLink extends AbstractVersionedEntity{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "link")
    private String link;

    @ManyToOne
    @JoinColumn(name="calc_tnc")
    private TNC tnc;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public TNC getTnc() {
        return tnc;
    }

    public void setTnc(TNC tnc) {
        this.tnc = tnc;
    }
}
