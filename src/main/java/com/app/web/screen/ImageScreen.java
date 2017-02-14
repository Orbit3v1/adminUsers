package com.app.web.screen;

import com.app.data.entity.Attachment;
import com.app.data.entity.AttachmentContent;
import com.app.utils.AppUtil;
import com.app.utils.AttachmentDownloader;
import com.app.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Named;

@Named("imageScreen")
@Scope("view")
public class ImageScreen {
    private String id;
    private String hashCode;

    @PostConstruct
    public void init() {
        id = SessionUtil.getParameter("id");
        hashCode = SessionUtil.getParameter("hashCode");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }
}

