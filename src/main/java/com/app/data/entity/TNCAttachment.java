package com.app.data.entity;
import javax.persistence.*;

@Entity
@Table(name = "tnc_attachment")
public class TNCAttachment {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name="calc_tnc")
    private TNC tnc;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="attachment")
    private Attachment attachment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TNC getTnc() {
        return tnc;
    }

    public void setTnc(TNC TNC) {
        this.tnc = TNC;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }
}
