package com.app.web.list;

import com.app.data.dao.ServiceRequestDao;
import com.app.data.dictionary.CarRequestState;
import com.app.data.dictionary.ServiceRequestState;
import com.app.data.dto.CarRequestDTO;
import com.app.data.dto.ServiceRequestDTO;
import com.app.data.entity.ServiceRequest;
import com.app.data.filter.ListFilterBean;
import com.app.msOffice.CarRequestXLS;
import com.app.msOffice.ServiceRequestXLS;
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
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named("serviceRequestList")
@Scope("session")
public class ServiceRequestList {
    @Autowired
    @Qualifier("serviceRequestListFilterBean")
    ListFilterBean listFilterBean;

    @Autowired
    ServiceRequestDao serviceRequestDao;

    @Autowired
    private AddMessage addMessage;

    private Map<String, Boolean> userPA;
    private List<ServiceRequestDTO> listRows;

    @Loggable
    @PostConstruct
    public void init(){
        userPA = Security.getUserPrivilegeAction("serviceRequestList");
    }

    public void updateList() {
        initList();
    }

    @Loggable
    private void initList() {
        List<ServiceRequest> serviceRequests = listFilterBean.getList();
        listRows = serviceRequests.stream().map(ServiceRequestDTO::new).collect(Collectors.toList());
    }

    @Loggable
    public void setEndActual(ServiceRequest serviceRequest) {
        Date date = new Date();
        serviceRequest.setEndActual(date);
        save(serviceRequest);
    }

    @Transactional
    private void save(ServiceRequest serviceRequest){
        try {
            serviceRequestDao.save(serviceRequest);
            addMessage.setMessage("mainForm:entities", "carRequestScreen.success.edit", FacesMessage.SEVERITY_INFO);
        } catch (OptimisticLockException e) {
            addMessage.setMessage("mainForm:entities", "error.entityWasChanged", FacesMessage.SEVERITY_ERROR);
        } catch (Exception e) {
            addMessage.setMessage("mainForm:entities", "error.exception", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Loggable
    public void onCellEdit(ServiceRequest serviceRequest){
        save(serviceRequest);
        executeJS("clickIn('mainForm:refreshNoReload');");
    }

    protected void executeJS(String script){
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute(script);
    }

    public void exportExcel(){
        ServiceRequestXLS pXLS = new ServiceRequestXLS(listRows, userPA);
        pXLS.renderExcel();
    }

    public Map<String, Boolean> getUserPA() {
        return userPA;
    }

    public void setUserPA(Map<String, Boolean> userPA) {
        this.userPA = userPA;
    }

    public List<ServiceRequestDTO> getListRows() {
        return listRows;
    }

    public void setListRows(List<ServiceRequestDTO> listRows) {
        this.listRows = listRows;
    }

    public List<ServiceRequestState> getFilterStates() {
        return Arrays.asList(ServiceRequestState.values());
    }
}
