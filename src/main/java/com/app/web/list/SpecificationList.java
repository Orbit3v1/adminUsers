package com.app.web.list;

import com.app.data.dictionary.SpecificationSort;
import com.app.data.entity.Specification;
import com.app.data.filter.ListFilterBean;
import com.app.data.filter.SpecificationCDI;
import com.app.data.filter.SpecificationListFilterBean;
import com.app.data.dto.SpecificationDTO;
import com.app.data.entity.filter.SpecificationListFilter;
import com.app.msOffice.SpecificationXLS;
import com.app.utils.AddMessage;
import com.app.utils.Security;
import com.app.web.Loggable;
import org.apache.log4j.Logger;
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

@Named("specificationList")
@Scope("session")
public class SpecificationList {
    @PersistenceContext
    private EntityManager em;
    private Logger logger = Logger.getLogger(getClass());

    @Inject
    private AddMessage addMessage;
    @SpecificationCDI
    @Autowired
    ListFilterBean listFilterBean;

    private Map<String, Boolean> userPA;
    private SpecificationListFilter filter;
    private List<SpecificationDTO> listRows;
    private boolean saveError;
    private String textError;

    @Loggable
    @PostConstruct
    public void init(){
        userPA = Security.getUserPrivilegeAction("specificationList");
        filter = ((SpecificationListFilterBean) listFilterBean).getFilterOriginal();
    }

    public void updateList() {
        initList();
    }

    @Loggable
    private void initList() {
        List<Specification> specifications = listFilterBean.getList();
        initListRows(specifications);
    }

    private void initListRows(List<Specification> specifications){
        listRows = new ArrayList<>();
        for(int i = 0; i < specifications.size(); i++){
            Specification sp = specifications.get(i);
            listRows.add(new SpecificationDTO(sp));
        }

    }

    public String getImage(String name){
        String image = "sort_neutral";
        if(filter.getSort() != null){
            if(filter.getSort().equals(SpecificationSort.valueOf(name + "_ASC"))){
                image = "sort_asc";
            } else if(filter.getSort().equals(SpecificationSort.valueOf(name + "_DESC"))){
                image = "sort_desc";
            }
        }
        return image;
    }

    public void exportExcel(){
        SpecificationXLS pXLS = new SpecificationXLS(listRows, userPA);
        pXLS.renderExcel();
    }



    public Map<String, Boolean> getUserPA() {
        return userPA;
    }

    public void setUserPA(Map<String, Boolean> userPA) {
        this.userPA = userPA;
    }

    public List<SpecificationDTO> getListRows() {
        return listRows;
    }

    public void setListRows(List<SpecificationDTO> listRows) {
        this.listRows = listRows;
    }

    public boolean isSaveError() {
        return saveError;
    }

    public void setSaveError(boolean saveError) {
        this.saveError = saveError;
    }

    public String getTextError() {
        return textError;
    }

    public void setTextError(String textError) {
        this.textError = textError;
    }
}
