package com.app.web.list;

import com.app.data.dto.ProductionReportDTO;
import com.app.data.dto.SpecificationDTO;
import com.app.data.dto.TNCRequestDTO;
import com.app.data.entity.OrderItem;
import com.app.data.entity.Specification;
import com.app.data.entity.TNCRequestItem;
import com.app.data.filter.ListFilterBean;
import com.app.data.filter.SpecificationCDI;
import com.app.data.filter.TNCRequestCDI;
import com.app.security.Security;
import com.app.utils.AddMessage;
import com.app.web.Loggable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Named("tncRequestList")
@Scope("session")
public class TNCRequestList {
    @PersistenceContext
    private EntityManager em;
    @Inject
    private AddMessage addMessage;

    @TNCRequestCDI
    @Autowired
    ListFilterBean listFilterBean;

    private Map<String, Boolean> userPA;
    private List<TNCRequestDTO> listRows;

    @Loggable
    @PostConstruct
    public void init(){
        userPA = Security.getUserPrivilegeAction("tncRequestList");
    }

    public void updateList() {
        initList();
    }

    @Loggable
    private void initList() {
        List<TNCRequestItem> tncRequestItems = listFilterBean.getList();
        initListRows(tncRequestItems);
    }

    private void initListRows(List<TNCRequestItem> tncRequestItems){
        listRows = new ArrayList<>();
        if(tncRequestItems.size() > 0){
            int lastTNCRequestId = tncRequestItems.get(0).getTncRequest().getId();
            listRows.add(new TNCRequestDTO(tncRequestItems.get(0), false));
            for(int i = 1; i < tncRequestItems.size(); i++){
                TNCRequestItem oi = tncRequestItems.get(i);
                listRows.add(new TNCRequestDTO(oi, oi.getTncRequest().getId() != lastTNCRequestId));
                lastTNCRequestId = tncRequestItems.get(i).getTncRequest().getId();
            }
        }
    }

    public Map<String, Boolean> getUserPA() {
        return userPA;
    }

    public void setUserPA(Map<String, Boolean> userPA) {
        this.userPA = userPA;
    }

    public List<TNCRequestDTO> getListRows() {
        return listRows;
    }

    public void setListRows(List<TNCRequestDTO> listRows) {
        this.listRows = listRows;
    }
}
