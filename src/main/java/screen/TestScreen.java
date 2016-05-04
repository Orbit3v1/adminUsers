package screen;

import entity.Attachment;
import entity.Role;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import utils.Security;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Named("testScreen")
@Scope("request")
public class TestScreen {

    @Inject
    private MailSender mailSender;
    @Inject
    private EntityManagerFactory entityManagerFactory;

    private List<Attachment> attachments;

    public TestScreen() {
    }

    @PostConstruct
    public void init() {

        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select r from Attachment r");
        attachments = query.getResultList();
    }

    public void sendEmail(){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("orbit3v1@gmail.com");
        message.setTo("duke007@ukr.net");
        message.setSubject("test");
        message.setText("hello it's test mail from java");
        mailSender.send(message);

    }

    public void listener(FileUploadEvent event) throws Exception {
        UploadedFile item = event.getUploadedFile();
        Attachment attachment = new Attachment();
        attachment.setName(item.getName());
        attachment.setSize(item.getSize());
        attachment.setContent(item.getData());
        attachment.setType(item.getContentType());
        attachments.add(attachment);

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(attachment);
        em.getTransaction().commit();
        em.close();


    }

    public void download(Attachment attachment){

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.responseReset();
        ec.setResponseContentType(attachment.getType());
        ec.setResponseContentLength((int) attachment.getSize());
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + attachment.getName() + "\"");

        try {
            OutputStream output = ec.getResponseOutputStream();
            output.write(attachment.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }

        fc.responseComplete();

    }

    public void delete(Attachment attachment){
        attachments.remove(attachment);
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(attachment));
        em.getTransaction().commit();
        em.close();
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
}
