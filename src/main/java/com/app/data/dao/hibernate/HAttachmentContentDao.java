package com.app.data.dao.hibernate;

import com.app.data.dao.AttachmentContentDao;
import com.app.data.dao.NomenclatureDao;
import com.app.data.entity.AttachmentContent;
import com.app.data.entity.Nomenclature;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by ayaroslavtsev on 12.01.2017.
 */
public class HAttachmentContentDao extends HGenericDao<AttachmentContent, Integer> implements AttachmentContentDao {

    public HAttachmentContentDao() {
        super(AttachmentContent.class);
    }

    @Override
    public List<AttachmentContent> getAll() {
        Query query = em.createQuery("select p from Nomenclature p " +
                "order by p.name");
        return query.getResultList();
    }
}
