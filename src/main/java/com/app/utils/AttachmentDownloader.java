package com.app.utils;

import com.app.entity.Attachment;
import com.app.entity.AttachmentContent;
import com.app.entity.NomenclatureAttachment;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Named("downloader")
@Scope("request")
public class AttachmentDownloader implements Download{

    @PersistenceContext
    private EntityManager em;
    private Logger logger = Logger.getLogger(getClass());

    public void download(Attachment attachment) {

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        populateExternalContent(ec, attachment);

        fc.responseComplete();

    }

    private void populateExternalContent(ExternalContext ec, Attachment attachment){

        ec.responseReset();
        ec.setResponseContentType(attachment.getType());
        ec.setResponseContentLength(Math.toIntExact(attachment.getSize()));
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + getFileName(attachment) + "\"");

        addContentTo(ec, attachment);

    }

    private String getFileName(Attachment attachment){
        String fileName = attachment.getName();
        try {
            fileName = URLEncoder.encode(attachment.getName(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    private void addContentTo(ExternalContext ec, Attachment attachment){
        try {
            byte[] content = getContent(attachment);
            OutputStream output = ec.getResponseOutputStream();
            output.write(content);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Transactional
    private byte[] getContent(Attachment attachment){
        AttachmentContent attachmentContent;
        if(isSaved(attachment)){
            attachmentContent = em.find(AttachmentContent.class, attachment.getId());
        } else {
            attachmentContent = attachment.getContent();
        }
        return attachmentContent.getContent();
    }

    private boolean isSaved(Attachment attachment){
        return attachment.getId() != 0;
    }

}
