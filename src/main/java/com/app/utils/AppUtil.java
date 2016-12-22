package com.app.utils;


import com.app.data.entity.Attachment;
import com.app.data.entity.AttachmentContent;
import com.app.data.entity.TNCRequestItem;
import org.primefaces.model.UploadedFile;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

public class AppUtil {

    public static Attachment getAttachment(Part file){
        Attachment attachment = new Attachment();
        try {
            InputStream input = file.getInputStream();
            byte[] content = new byte[(int) file.getSize()];
            input.read(content);

            AttachmentContent attachmentContent = new AttachmentContent();
            attachmentContent.setContent(content);

            attachment.setName(getFilename(file));
            attachment.setSize(file.getSize());
            attachment.setContent(attachmentContent);
            attachment.setType(file.getContentType());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return attachment;
    }

    public static Attachment getAttachment(UploadedFile file){
        Attachment attachment = new Attachment();
        try {
            InputStream input = file.getInputstream();
            byte[] content = new byte[(int) file.getSize()];
            input.read(content);

            AttachmentContent attachmentContent = new AttachmentContent();
            attachmentContent.setContent(content);

            attachment.setName(file.getFileName());
            attachment.setSize(file.getSize());
            attachment.setContent(attachmentContent);
            attachment.setType(file.getContentType());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return attachment;
    }


    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }

    public static boolean isNumeric(String string){
        try {
            Long.parseLong(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static Integer toInteger(String string){
        Integer res = null;
        if(string != null && !string.equals("")) {
            res = Integer.valueOf(string);
        }
        return res;
    }

    public static String toString(Integer val){
        return val == null ? null : String.valueOf(val);
    }

    public static Date trimTime(Date date) {
        if(date == null){
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);

        return calendar.getTime();
    }

    public static Date endDay(Date date){
        if(date == null){
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.HOUR_OF_DAY, 23);

        return calendar.getTime();
    }


    public static boolean isInRange(Date date, Date begin, Date end){
        boolean match = true;
        Date val = trimTime(date);

        if(begin == null && end == null){
            match = true;
        } else if(val == null){
            match = false;
        } else {
            if (begin != null){
                match =  begin.compareTo(val) <= 0;
            }
            if (end != null){
                match &=  end.compareTo(val) >= 0;
            }

        }
        return match;
    }

    public static boolean notEmpty(String str){
        return str != null && !str.equals("");
    }

    public static <T> T findByHashCode(Collection<T> collection, Integer hashCode){
        T item = null;
        for(T i : collection){
            if(i.hashCode() == hashCode){
                item = i;
                break;
            }
        }
        return item;
    }

}
