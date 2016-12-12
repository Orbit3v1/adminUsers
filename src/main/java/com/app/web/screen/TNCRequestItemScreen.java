package com.app.web.screen;

import com.app.data.dictionary.TNCRequestState;
import com.app.data.entity.*;
import com.app.utils.AppUtil;
import com.app.utils.EntityUtil;
import com.app.utils.SessionUtil;
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

    private static final String INITIAL_NAME = "?";

    @Override
    protected String getScreenName() {
        return "TNCRequestItemScreen";
    }

    @PostConstruct
    public void init() {
        logger.info("init");
        initSource();
        initEntity();
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
            originalItem = getItemByHash(source, AppUtil.toInteger(hash));
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

    private TNCRequestItem getItemByHash(TNCRequest source, Integer itemHash){
        TNCRequestItem item = null;
        for(TNCRequestItem i : source.getTncRequestItems()){
            if(i.hashCode() == itemHash){
                item = i;
                break;
            }
        }
        return item;
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

    public void choose(String screenName){
        RequestContext rq = RequestContext.getCurrentInstance();
        rq.openDialog("/select/" + screenName, getStandardOptions(), null);
    }

    private Map<String, Object> getStandardOptions() {
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", true);
        options.put("draggable", true);
        options.put("modal", true);
        options.put("width", 650);
        options.put("height", 400);
        return options;
    }

    public void onTNCChosen(SelectEvent event) {
        TNC tnc = (TNC) event.getObject();
        entity.setTnc(tnc);
    }

    @Transactional
    public void refresh(){
        logger.info("refresh");
        TNC tnc = em.find(TNC.class, entity.getTnc().getId());
        entity.setTnc(tnc);
    }
}
