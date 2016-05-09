package entity;

import javax.persistence.*;

@Entity
@Table(name = "nomenclature_attachment")
public class NomenclatureAttachment extends AbstractVersionedEntity  {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name="nomenclature")
    private Nomenclature nomenclature;

    @ManyToOne
    @JoinColumn(name="attachment")
    private Attachment attachment;

    @Column(name = "type")
    private String type;

    public NomenclatureAttachment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
