package com.app.utils;

import com.app.data.dao.AttachmentContentDao;
import com.app.data.entity.Attachment;
import com.app.data.entity.AttachmentContent;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Named("downloader")
@Scope("request")
public class AttachmentDownloader implements Download{

    @Inject
    private AttachmentContentDao attachmentContentDao;
    private Logger logger = Logger.getLogger(getClass());
    
    private FacesContext facesContext;
    private ExternalContext externalContext;
    private Attachment attachment;

    public void download(Attachment attachment) {
        init(attachment);
        populateExternalContent();
        sendResponse();
    }
    
    private void init(Attachment attachment){
        this.attachment = attachment;
        facesContext = FacesContext.getCurrentInstance();
        externalContext = facesContext.getExternalContext();
    }

    private void populateExternalContent(){
        externalContext.responseReset();
        externalContext.setResponseContentType(attachment.getType());
        externalContext.setResponseContentLength(Math.toIntExact(attachment.getSize()));
        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + getFileName(attachment) + "\"");

        addContent();
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

    private void addContent(){
        try {
            byte[] content = getContent();
            OutputStream output = externalContext.getResponseOutputStream();
            output.write(content);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Transactional
    private byte[] getContent(){
        AttachmentContent attachmentContent;
        if(isAttachmentSaved()){
            attachmentContent = attachmentContentDao.getById(attachment.getId());
        } else {
            attachmentContent = attachment.getContent();
        }
        return attachmentContent.getContent();
    }

    private boolean isAttachmentSaved(){
        return attachment.getId() != 0;
    }

    private void sendResponse(){
        facesContext.responseComplete();
    }
}
