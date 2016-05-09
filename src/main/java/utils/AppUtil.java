package utils;


import entity.Attachment;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

public class AppUtil {

    public static Attachment getAttachment(Part file){
        Attachment attachment = new Attachment();
        try {
            InputStream input = file.getInputStream();
            byte[] content = new byte[(int) file.getSize()];
            input.read(content);

            attachment.setName(getFilename(file));
            attachment.setSize(file.getSize());
            attachment.setContent(content);
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

}
