package validator;

import entity.Nomenclature;
import entity.Order;
import org.springframework.context.annotation.Scope;
import utils.AppUtil;
import utils.SessionUtil;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ResourceBundle;

@Named("orderValidator")
@Scope("request")
public class OrderValidator implements Validator<Order> {
    @PersistenceContext
    protected EntityManager em;
    @Inject
    ResourceBundle resourceBundle;

    private Order order;
    private boolean edit;
    private String count;

    @Override
    public boolean validate(Order order, Object... args) {
        this.order = order;
        edit = args.length > 0 ? (Boolean) args[0] : false;
        count = args.length > 1 ? (String) args[1] : "";
        return isValidName() & isValidNomenclature() & isValidCount();
    }

    private boolean isValidName() {
        boolean valid = true;
        if (order.getName().equals("")) {
            valid = false;
            SessionUtil.setMessage("mainForm:name", "error.notNull", FacesMessage.SEVERITY_ERROR);
        } else {
            Query query = em.createQuery("select r from Order r where r.name = :name and (r.id != :id or :id is null)")
                    .setParameter("name", order.getName())
                    .setParameter("id", edit ? order.getId() : null);
            if (query.getResultList().size() != 0) {
                valid = false;
                SessionUtil.setMessage("mainForm:name", "orderScreen.error.nameDuplicate", FacesMessage.SEVERITY_ERROR);
            }
        }
        return valid;
    }

    private boolean isValidNomenclature() {
        boolean valid = true;
        if (order.getNomenclature() == null) {
            valid = false;
            SessionUtil.setMessage("mainForm:nomenclature", "error.notNull", FacesMessage.SEVERITY_ERROR);
        }
        return valid;
    }

    private boolean isValidCount() {
        boolean valid = true;
        if (count.equals("")) {
            valid = false;
            SessionUtil.setMessage("mainForm:count", "error.notNull", FacesMessage.SEVERITY_ERROR);
        } else if (!AppUtil.isNumeric(count)) {
            valid = false;
            SessionUtil.setMessage("mainForm:count", "error.notNumber", FacesMessage.SEVERITY_ERROR);
        }
        return valid;
    }
}
