package screen;

import entity.Nomenclature;
import entity.Order;
import entity.Person;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Locale;

@Named("orderScreen")
@Scope("session")
public class OrderScreen extends EntityScreen<Order>  {

    @Inject
    Locale locale;

    @PostConstruct
    public void init() {
        entity = new Order();
    }

    @Override
    protected String getScreenName() {
        return "orderScreen";
    }

    @Override
    public boolean save() {
        return false;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
