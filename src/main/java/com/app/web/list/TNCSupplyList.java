package com.app.web.list;

import com.app.data.dto.SpecificationDTO;
import com.app.data.dto.TNCRequestDTO;
import com.app.data.dto.TNCSupplyDTO;
import com.app.data.entity.Specification;
import com.app.data.entity.TNCRequestItem;
import com.app.data.entity.TNCSupply;
import com.app.data.entity.TNCSupplyItem;
import com.app.data.filter.ListFilterBean;
import com.app.data.filter.TNCRequestCDI;
import com.app.data.filter.TNCSupplyCDI;
import com.app.security.Security;
import com.app.web.Loggable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Named("tncSupplyList")
@Scope("session")
public class TNCSupplyList {
    @PersistenceContext
    private EntityManager em;

    @TNCSupplyCDI
    @Autowired
    ListFilterBean listFilterBean;

    private Map<String, Boolean> userPA;
    private List<TNCSupplyDTO> listRows;

    @Loggable
    @PostConstruct
    public void init(){
        userPA = Security.getUserPrivilegeAction("tncSupplyList");
    }

    public void updateList() {
        initList();
    }

    @Loggable
    private void initList() {
        List<TNCSupply> tncSupplies = listFilterBean.getList();
        initListRows(tncSupplies);
    }

    private void initListRows(List<TNCSupply> tncSupplies){
        listRows = new ArrayList<>();
        for(int i = 0; i < tncSupplies.size(); i++){
            TNCSupply sp = tncSupplies.get(i);
            listRows.add(new TNCSupplyDTO(sp));
        }
    }

    public Map<String, Boolean> getUserPA() {
        return userPA;
    }

    public void setUserPA(Map<String, Boolean> userPA) {
        this.userPA = userPA;
    }

    public List<TNCSupplyDTO> getListRows() {
        return listRows;
    }

    public void setListRows(List<TNCSupplyDTO> listRows) {
        this.listRows = listRows;
    }

}
