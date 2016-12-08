package com.app.web;

import com.app.data.entity.Attachment;
import com.app.data.entity.AttachmentContent;
import com.app.utils.AppUtil;
import com.app.utils.SessionUtil;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

@Named
@ApplicationScoped
public class Images {

    @PersistenceContext
    private EntityManager em;

    public StreamedContent getImage(){
        PhaseId phase = FacesContext.getCurrentInstance().getCurrentPhaseId();
        String name = null;
        if(phase != null){
            name = phase.getName();
        }
        String attachmentId = SessionUtil.getParameter("attachmentId");
        String attachmentHash = SessionUtil.getParameter("attachmentHash");
        StreamedContent streamedContent;
        AttachmentContent attachmentContent = null;
        if(attachmentId != null && AppUtil.toInteger(attachmentId) != 0){
            attachmentContent = em.find(AttachmentContent.class, AppUtil.toInteger(attachmentId));
        } else if(attachmentHash != null){
            Attachment attachment = (Attachment) SessionUtil.getSessionVariable(attachmentHash);
            if(attachment != null) {
                attachmentContent = attachment.getContent();
            }
        }

        if(attachmentContent != null){
            streamedContent = new DefaultStreamedContent(new ByteArrayInputStream(attachmentContent.getContent()));
        } else {
            streamedContent = new DefaultStreamedContent();
        }
        return streamedContent;
    }

}
