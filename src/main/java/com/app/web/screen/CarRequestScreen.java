package com.app.web.screen;

import com.app.data.dao.AttachmentContentDao;
import com.app.data.dao.CarRequestDao;
import com.app.data.entity.*;
import com.app.security.Security;
import com.app.utils.AppUtil;
import com.app.utils.SessionUtil;
import com.app.web.Loggable;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Named("carRequestScreen")
@Scope("view")
public class CarRequestScreen extends EntityScreen<CarRequest>{

    @Autowired
    private CarRequestDao carRequestDao;
    @Autowired
    private AttachmentContentDao attachmentContentDao;

    private List<Person> developers;

    @Override
    protected String getScreenName() {
        return "carRequestScreen";
    }

    @Loggable
    @PostConstruct
    public void init() {
        initEntity();
        developers = entityUtil.getDevelopers();
    }

    @Transactional
    public void initEntity() {
        String id = SessionUtil.getParameter("id");
        if(id != null && AppUtil.isNumeric(id)){
            entity = carRequestDao.getById(AppUtil.toInteger(id));
            edit = true;
        } else {
            entity = new CarRequest();
            entity.setCreator(Security.getCurrentUser());
            entity.setStart(new Date());
            entity.setName(getGeneratedName());
        }
    }

    private String getGeneratedName(){
        final String PREFIX = "Д";
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String name = df.format(new Date());
        return PREFIX + name;
    }

    @Override
    @Transactional
    protected void save() {
        saveAttachment();
        entity = carRequestDao.save(entity);
    }

    private void saveAttachment(){
        for(Attachment attachment : entity.getAttachments()){
            if(attachment.getId() == 0) {
                AttachmentContent attachmentContent = attachment.getContent();
                attachmentContent = attachmentContentDao.save(attachmentContent);

                attachment.setId(attachmentContent.getId());
                attachment.setContent(attachmentContent);
            }
        }
    }

    public void refresh(){
    }

    public void setEndActual(){
        entity.setEndActual(new Date());
    }

    public void uploadFile(FileUploadEvent event) {
        Attachment attachment = AppUtil.getAttachment(event.getFile());
        entity.getAttachments().add(attachment);
    }

    public void deleteAttachment(Attachment attachment) {
        logger.info("deleteAttachment attachment. fileName = " + attachment.getName());
        entity.getAttachments().remove(attachment);
    }

    @Loggable
    public void cancelEndActual(){
        entity.setEndActual(null);
    }

    public List<Person> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Person> developers) {
        this.developers = developers;
    }

}
