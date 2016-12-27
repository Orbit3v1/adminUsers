package com.app.web.list;

import com.app.data.dto.ProductionReportDTO;
import com.app.data.dto.SpecificationDTO;
import com.app.data.dto.TNCRequestDTO;
import com.app.data.entity.*;
import com.app.data.filter.ListFilterBean;
import com.app.data.filter.SpecificationCDI;
import com.app.data.filter.TNCRequestCDI;
import com.app.security.Security;
import com.app.utils.AddMessage;
import com.app.utils.AppUtil;
import com.app.utils.SessionUtil;
import com.app.web.Loggable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named("tncRequestList")
@Scope("session")
public class TNCRequestList {
    @PersistenceContext
    private EntityManager em;

    @TNCRequestCDI
    @Autowired
    ListFilterBean listFilterBean;

    private Map<String, Boolean> userPA;
    private List<TNCRequestDTO> listRows;
    private List<TNCRequestDTO> selectedRows;
    private TNCSupply tncSupply;

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
        selectedRows = new ArrayList<>();
        tncSupply = getTNCSupply();
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

    public void createSupply(){
        Map<TNC, TNCSupplyItem> map = new HashMap<>();
        for(TNCSupplyItem supplyItem : tncSupply.getTncSupplyItems()){
            map.put(supplyItem.getTnc(), supplyItem);
        }
        for(TNCRequestDTO row : selectedRows){
            TNC tnc = row.getTnc();
            TNCSupplyItem tncSupplyItem = new TNCSupplyItem();
            if(map.containsKey(tnc)){
                tncSupplyItem = map.get(tnc);
                tncSupplyItem.setCount(tncSupplyItem.getCount() + row.getCount());
                tncSupplyItem.getTncRequestItems().add(row.getTncRequestItem());
            } else {
                tncSupplyItem.setTncSupply(tncSupply);
                tncSupplyItem.setTnc(tnc);
                tncSupplyItem.setCount(row.getCount());
                tncSupplyItem.getTncRequestItems().add(row.getTncRequestItem());
                tncSupply.getTncSupplyItems().add(tncSupplyItem);
                map.put(tnc, tncSupplyItem);
            }
        }
        SessionUtil.addSessionVariable("TNCSupplyCreated", tncSupply);
    }

    private TNCSupply getTNCSupply(){
        TNCSupply tncSupply = new TNCSupply();
        String id = SessionUtil.getParameter("sourceId");
        if(id != null && AppUtil.isNumeric(id)){
            tncSupply = (TNCSupply) SessionUtil.getSessionVariable("TNCSupply" + id);
        }
        return tncSupply;
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

    public List<TNCRequestDTO> getSelectedRows() {
        return selectedRows;
    }

    public void setSelectedRows(List<TNCRequestDTO> selectedRows) {
        this.selectedRows = selectedRows;
    }

}
