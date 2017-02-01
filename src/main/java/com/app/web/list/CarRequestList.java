package com.app.web.list;

import com.app.data.dao.NomenclatureDao;
import com.app.data.dto.SpecificationDTO;
import com.app.data.entity.CarRequest;
import com.app.data.entity.Nomenclature;
import com.app.data.entity.Specification;
import com.app.data.filter.ListFilterBean;
import com.app.data.filter.SpecificationCDI;
import com.app.security.Security;
import com.app.web.Loggable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named("carRequestList")
@Scope("request")
public class CarRequestList {

    @Autowired
    @Qualifier("carRequestListFilterBean")
    ListFilterBean listFilterBean;

    private Map<String, Boolean> userPA;
    private List<CarRequest> listRows;

    @Loggable
    @PostConstruct
    public void init(){
        userPA = Security.getUserPrivilegeAction("carRequestList");
    }

    public void updateList() {
        initList();
    }

    @Loggable
    private void initList() {
        listRows = listFilterBean.getList();
    }

    public Map<String, Boolean> getUserPA() {
        return userPA;
    }

    public void setUserPA(Map<String, Boolean> userPA) {
        this.userPA = userPA;
    }

    public List<CarRequest> getListRows() {
        return listRows;
    }

    public void setListRows(List<CarRequest> listRows) {
        this.listRows = listRows;
    }
}
