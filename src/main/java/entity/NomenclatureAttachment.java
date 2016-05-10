package entity;

import dictionary.NAType;

import javax.persistence.*;

@Entity
@Table(name = "nomenclature_attachment")
public class NomenclatureAttachment{

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name="nomenclature")
    private Nomenclature nomenclature;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="attachment")
    private Attachment attachment;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private NAType type;

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

    public NAType getType() {
        return type;
    }

    public void setType(NAType type) {
        this.type = type;
    }
}
