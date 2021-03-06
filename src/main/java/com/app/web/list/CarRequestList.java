package com.app.web.list;

import com.app.data.dao.CarRequestDao;
import com.app.data.dao.NomenclatureDao;
import com.app.data.dictionary.CarRequestSort;
import com.app.data.dictionary.CarRequestState;
import com.app.data.dictionary.OrderItemState;
import com.app.data.dto.CarRequestDTO;
import com.app.data.dto.SpecificationDTO;
import com.app.data.entity.CarRequest;
import com.app.data.entity.Nomenclature;
import com.app.data.entity.OrderItem;
import com.app.data.entity.Specification;
import com.app.data.filter.FilterBean;
import com.app.data.filter.ListFilterBean;
import com.app.data.filter.SpecificationCDI;
import com.app.msOffice.CarRequestXLS;
import com.app.msOffice.ProductionXLS;
import com.app.security.Security;
import com.app.utils.AddMessage;
import com.app.web.Loggable;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named("carRequestList")
@Scope("session")
public class CarRequestList {

    @Autowired
    @Qualifier("carRequestListFilterBean")
    ListFilterBean listFilterBean;

    @Autowired
    CarRequestDao carRequestDao;

    @Autowired
    private AddMessage addMessage;

    private Map<String, Boolean> userPA;
    private List<CarRequestDTO> listRows;

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
        List<CarRequest> carRequests = listFilterBean.getList();
        listRows = carRequests.stream().map(CarRequestDTO::new).collect(Collectors.toList());
    }

    @Loggable
    public void setEndActual(CarRequest carRequest) {
        Date date = new Date();
        carRequest.setEndActual(date);
        save(carRequest);
    }

    @Transactional
    private void save(CarRequest carRequest){
        try {
            carRequestDao.save(carRequest);
            addMessage.setMessage("mainForm:entities", "carRequestScreen.success.edit", FacesMessage.SEVERITY_INFO);
        } catch (OptimisticLockException e) {
            addMessage.setMessage("mainForm:entities", "error.entityWasChanged", FacesMessage.SEVERITY_ERROR);
        } catch (Exception e) {
            addMessage.setMessage("mainForm:entities", "error.exception", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Loggable
    public void onCellEdit(CarRequest carRequest){
        save(carRequest);
        executeJS("clickIn('mainForm:refreshNoReload');");
    }

    protected void executeJS(String script){
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute(script);
    }

    public void exportExcel(){
        CarRequestXLS pXLS = new CarRequestXLS(listRows, userPA);
        pXLS.renderExcel();
    }

    public Map<String, Boolean> getUserPA() {
        return userPA;
    }

    public void setUserPA(Map<String, Boolean> userPA) {
        this.userPA = userPA;
    }

    public List<CarRequestDTO> getListRows() {
        return listRows;
    }

    public void setListRows(List<CarRequestDTO> listRows) {
        this.listRows = listRows;
    }

    public List<CarRequestState> getFilterStates() {
        return Arrays.asList(CarRequestState.values());
    }
}
