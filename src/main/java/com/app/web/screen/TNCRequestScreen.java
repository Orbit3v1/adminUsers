package com.app.web.screen;

import com.app.data.entity.*;
import com.app.security.Security;
import com.app.utils.AppUtil;
import com.app.utils.EntityUtil;
import com.app.utils.SessionUtil;
import com.app.web.Loggable;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Named("TNCRequestScreen")
@Scope("view")
public class TNCRequestScreen extends EntityScreen<TNCRequest>{

    private static final String NAME_PREFIX = "ТНЦ";
    private List<Person> developers;

    @Loggable
    @PostConstruct
    public void init() {
        initEntity();
        developers = EntityUtil.getDevelopers();
    }

    @Transactional
    public void initEntity() {
        String id = SessionUtil.getParameter("id");
        if(id != null && AppUtil.isNumeric(id)){
            entity = em.find(TNCRequest.class, AppUtil.toInteger(id));
            entity.getTncRequestItems().size();
            edit = true;
        } else {
            entity = new TNCRequest();
            entity.setCreator(Security.getCurrentUser());
            entity.setResponsible(Security.getCurrentUser());
            entity.setStart(new Date());
            generateName();
        }
    }

    private void generateName(){
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String name = NAME_PREFIX + df.format(new Date());
        entity.setName(name);
    }

    public void delete(TNCRequestItem item) {
        entity.getTncRequestItems().remove(item);
    }

    public void shareEntity(){
        SessionUtil.addSessionVariable("TNCRequest" + entity.getId(), entity);
    }

    @Override
    protected void clearCash(){
        SessionUtil.removeSessionVariable("TNCRequest" + entity.getId());
    }

    @Override
    protected String getScreenName() {
        return "TNCRequestScreen";
    }

    @Override
    @Transactional
    protected void save() {
        generateItemsNames();
        entity = em.merge(entity);
    }

    private void generateItemsNames(){
        AtomicInteger maxName = new AtomicInteger(getMaxItemName() + 1);
        entity.getTncRequestItems().stream().filter(x -> x.getId().equals(0)).forEach(x -> x.setName(String.valueOf(maxName.getAndIncrement())));
    }

    private int getMaxItemName(){
        Query query = em.createQuery("select max(cast(ri.name as int)) " +
                "from TNCRequestItem ri " +
                "where ri.tncRequest.name = :name "
        );
        Integer result = (Integer) query.setParameter("name", entity.getName()).getSingleResult();
        return result == null ? 0 : result;
    }

    @Loggable
    @Transactional
    public void refresh() {
        for (TNCRequestItem item : entity.getTncRequestItems()) {
            item.setTnc(em.find(TNC.class, item.getTnc().getId()));
        }
    }

    public List<Person> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Person> developers) {
        this.developers = developers;
    }
}
