package com.app.validator;

import com.app.data.entity.Specification;
import com.app.utils.AppUtil;
import org.springframework.context.annotation.Scope;

import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.persistence.Query;
import java.util.List;

@Named("specificationValidator")
@Scope("request")
public class SpecificationValidator extends AbstractValidator<Specification> {

    protected boolean edit;
    protected String workDays;

    @Override
    public boolean validate(Specification specification, Object... args) {
        this.entity = specification;
        edit = args.length > 0 ? (Boolean) args[0] : false;
        workDays = args.length > 1 ? (String) args[1] : "";
        return isValidName() & isValidDiscount() & isValidWorkDays() & isValidNomenclature();
    }

    protected boolean isValidName() {
        boolean valid = true;
        if (edit && getEntityWithSameName().size() != 0) {
            valid = false;
            addMessage.setMessage("mainForm:name", "specificationScreen.error.nameDuplicate", FacesMessage.SEVERITY_ERROR);
        }
        return valid;
    }

    protected List<Specification> getEntityWithSameName() {
        Query query = em.createQuery("select r from Specification r where r.name = :name and r.subName = :subName and r.id != :id")
                .setParameter("name", entity.getName())
                .setParameter("subName", entity.getSubName())
                .setParameter("id", edit ? entity.getId() : "");
        return query.getResultList();
    }

    protected boolean isValidDiscount() {
        boolean valid = true;
        if (entity.getDiscount() != null && !entity.getDiscount().equals("") && !AppUtil.isNumeric(entity.getDiscount())) {
            valid = false;
            addMessage.setMessage("mainForm:discount", "error.notNumber", FacesMessage.SEVERITY_ERROR);
        }
        return valid;
    }

    protected boolean isValidWorkDays() {
        boolean valid = true;
        if (workDays != null && !workDays.equals("") && !AppUtil.isNumeric(workDays)) {
            valid = false;
            addMessage.setMessage("mainForm:workDays", "error.notNumber", FacesMessage.SEVERITY_ERROR);
        }
        return valid;
    }

    protected boolean isValidNomenclature(){
        boolean valid = true;
        if (entity.getNomenclature() != null && getEntityWithSameNomenclature().size() != 0) {
            valid = false;
            addMessage.setMessage("mainForm:nomenclature", "specificationScreen.error.nomenclatureDuplicate", FacesMessage.SEVERITY_ERROR);
        }
        return valid;
    }

    protected List<Specification> getEntityWithSameNomenclature() {
        Query query = em.createQuery("select r from Specification r where r.nomenclature.id = :nomenclatureId and r.id != :id")
                .setParameter("nomenclatureId", entity.getNomenclature().getId())
                .setParameter("id", edit ? entity.getId() : -1);
        return query.getResultList();
    }
}
