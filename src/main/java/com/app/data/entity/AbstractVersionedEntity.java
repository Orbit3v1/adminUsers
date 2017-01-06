package com.app.data.entity;

import com.app.data.entity.interfaces.Unique;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class AbstractVersionedEntity<T> implements Unique<T> {
    @Version
    @Column(name = "version", nullable = false)
    private int version;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
