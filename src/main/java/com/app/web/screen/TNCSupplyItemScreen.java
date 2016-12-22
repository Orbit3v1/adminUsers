package com.app.web.screen;

import com.app.data.dictionary.TNCRequestState;
import com.app.data.entity.*;
import com.app.utils.AppUtil;
import com.app.utils.SessionUtil;
import com.app.utils.TNCChooser;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Named;

@Named("tncSupplyItemScreen")
@Scope("view")
public class TNCSupplyItemScreen extends EntityScreen<TNCSupplyItem> {
    private TNCSupplyItem originalItem;
    private TNCSupply source;
    private TNCChooser tncChooser;

    @Override
    protected String getScreenName() {
        return "tncSupplyItemScreen";
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
            source = (TNCSupply) SessionUtil.getSessionVariable("TNCSupply" + id);
        }
    }

    public void initEntity() {
        String hash = SessionUtil.getParameter("hash");
        if(hash != null && AppUtil.isNumeric(hash)){
            originalItem = AppUtil.findByHashCode(source.getTncSupplyItems(), AppUtil.toInteger(hash));
            entity = new TNCSupplyItem();
            entity.copyForm(originalItem);
            edit = true;
        } else {
            originalItem = new TNCSupplyItem();
            entity = new TNCSupplyItem();
            entity.setTncSupply(source);
        }
    }

    @Override
    protected void save() {
        originalItem.copyForm(entity);
        if (!edit) {
            source.getTncSupplyItems().add(originalItem);
            edit = true;
        }
    }

    @Override
    public void delete(){
        logger.info("delete");
        source.getTncSupplyItems().remove(originalItem);
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
