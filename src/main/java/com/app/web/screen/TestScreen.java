package com.app.web.screen;

import com.app.data.dao.PersonDao;
import com.app.data.entity.Attachment;
import com.app.data.entity.AttachmentContent;
import com.app.data.entity.Person;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.Part;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Named("testScreen")
@Scope("request")
public class TestScreen {

    @Inject
    private MailSender mailSender;
    @Inject
    private EntityManagerFactory entityManagerFactory;
    @PersistenceContext
    private EntityManager em;
    @Inject
    private PersonDao personDao;

    @Resource(mappedName = "jdbc/appOwnerDB")
    DataSource ds2;

    private List<Attachment> attachments;
    private Part file;
    private Date date;

    public TestScreen() {
    }

    @PostConstruct
    public void init() {

        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select r from Attachment r");
        attachments = query.getResultList();
    }

    public void onChangeDate(int id, Date date){
        System.out.println("onChangeDate");
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
        //attachment.setContent(item.getData());
        attachment.setType(item.getContentType());
        attachments.add(attachment);

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(attachment);
        em.getTransaction().commit();
        em.close();

    }

    @Transactional
    public void upload() {
        Attachment attachment = new Attachment();
        try {
            InputStream input = file.getInputStream();
            byte[] content = new byte[(int) file.getSize()];
            input.read(content);

            AttachmentContent c = new AttachmentContent();
            c.setContent(content);
            AttachmentContent cm = em.merge(c);

            attachment.setName(getFilename(file));
            attachment.setSize(file.getSize());
            attachment.setContent(cm);
            attachment.setType(file.getContentType());
            attachment.setId(cm.getId());
            attachments.add(attachment);

            em.merge(attachment);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }




    public void download(Attachment attachment){

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.responseReset();
        ec.setResponseContentType(attachment.getType());
        ec.setResponseContentLength(Math.toIntExact(attachment.getSize()));
        String fileName = attachment.getName();
        try {
            fileName = URLEncoder.encode(attachment.getName(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        try {
            AttachmentContent content = em.find(AttachmentContent.class, attachment.getId());
            OutputStream output = ec.getResponseOutputStream();
            output.write(content.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }

        fc.responseComplete();

    }

    public void delete(Attachment attachment ){
        attachments.remove(attachment);
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(attachment));
        em.getTransaction().commit();
        em.close();
    }

    public void renderExcel(){
        Workbook wb = new HSSFWorkbook();

        Sheet sheet = wb.createSheet("Timesheet");
        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);
        sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);

        Row titleRow = sheet.createRow(0);
        titleRow.setHeightInPoints(45);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("Weekly Timesheet");

        String file = "timesheet.xls";

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.responseReset();
        ec.setResponseContentType("text/xsl");
       // ec.setResponseContentLength((int) out.getSize());
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + file + "\"");

        try {
            OutputStream output = ec.getResponseOutputStream();
            wb.write(output);
        } catch (Exception e) {
            e.printStackTrace();
        }

        fc.responseComplete();
    }

    public void testJNDI() throws NamingException, SQLException {
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        DataSource ds = (DataSource) envCtx.lookup("jdbc/appOwnerDB");

        Connection conn = ds.getConnection();
        conn.close();
    }

    public void testSecondLevelCash(){
        List<Person> persons1 = personDao.getAll();
        List<Person> persons2 = personDao.getAll();
        Person person1 = personDao.getById(15);
        Person person2 = personDao.getById(15);
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
