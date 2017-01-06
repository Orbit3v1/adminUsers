package com.app.web.screen;

import com.app.data.dictionary.TNCRequestState;
import com.app.data.entity.*;
import com.app.utils.AppUtil;
import com.app.utils.SessionUtil;
import com.app.utils.TNCChooser;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

@Named("TNCRequestItemScreen")
@Scope("view")
public class TNCRequestItemScreen extends EntityScreen<TNCRequestItem> {

    private TNCRequestItem originalItem;
    private TNCRequest source;
    private TNCChooser tncChooser;

    private static final String INITIAL_NAME = "0";

    @Override
    protected String getScreenName() {
        return "TNCRequestItemScreen";
    }

    @PostConstruct
    public void init() {
        logger.info("init");
        initSource();
        initEntity();
        tncChooser = new TNCChooser(entity);
    }

    public void initSource(){
        String id = SessionUtil.getParameter("sourceId");
        if(id != null && AppUtil.isNumeric(id)){
            source = (TNCRequest) SessionUtil.getSessionVariable("TNCRequest" + id);
        }
    }

    public void initEntity() {
        String hash = SessionUtil.getParameter("hash");
        if(hash != null && AppUtil.isNumeric(hash)){
            originalItem = AppUtil.findByHashCode(source.getTncRequestItems(), AppUtil.toInteger(hash));
            entity = new TNCRequestItem();
            entity.copyForm(originalItem);
            edit = true;
        } else {
            originalItem = new TNCRequestItem();
            entity = new TNCRequestItem();
            entity.setTncRequest(source);
            entity.setEndPlan(source.getEndPlan());
            entity.setName(INITIAL_NAME);
            entity.setState(TNCRequestState.IN_WORK);
        }
    }

    @Override
    protected void save() {
        originalItem.copyForm(entity);
        if (!edit) {
            source.getTncRequestItems().add(originalItem);
            edit = true;
        }
    }

    @Override
    public void delete(){
        logger.info("delete");
        source.getTncRequestItems().remove(originalItem);
        executeJS("save();");
        exit();
    }

    @Transactional
    public void refresh(){
        logger.info("refresh");
        TNC tnc = em.find(TNC.class, entity.getTnc().getId());
        entity.setTnc(tnc);
    }

    public TNCChooser getTncChooser() {
        return tncChooser;
    }

    public void setTncChooser(TNCChooser tncChooser) {
        this.tncChooser = tncChooser;
    }
}
