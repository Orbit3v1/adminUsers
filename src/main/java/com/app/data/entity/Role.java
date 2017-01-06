package com.app.data.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
public class Role extends AbstractVersionedEntity<String> {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_privilege_action",
            joinColumns = @JoinColumn(name = "role"),
            inverseJoinColumns = {
                    @JoinColumn(name = "privilege", referencedColumnName = "privilege"),
                    @JoinColumn(name = "action", referencedColumnName = "action")
            })
    private List<PrivilegeAction> privilegeAction = new ArrayList<>();

    public Role() {
    }

    public Role(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Role(String id, String name, String description, List<PrivilegeAction> privilegeAction) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.privilegeAction = privilegeAction;
    }

    @Override
    public int hashCode() {
        return (id != null)
                ? (getClass().hashCode() + id.hashCode())
                : super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj != null && getClass() == obj.getClass() && id != null)
                ? id.equals(((Role) obj).id)
                : (obj == this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PrivilegeAction> getPrivilegeAction() {
        return privilegeAction;
    }

    public void setPrivilegeAction(List<PrivilegeAction> privilege) {
        this.privilegeAction = privilege;
    }
}
