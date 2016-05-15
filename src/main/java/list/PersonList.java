package list;

import entity.Person;
import org.springframework.context.annotation.Scope;
import utils.Security;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Named("personList")
@Scope("request")
public class PersonList {

    @PersistenceContext
    private EntityManager em;

    private List<Person> persons;
    private Map<String, Boolean> userPA;


    @PostConstruct
    public void init(){
        Query query = em.createQuery("select p from Person p order by p.lastName, p.firstName");
        persons = query.getResultList();
        userPA = Security.getUserPrivilegeAction("personList");
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public Map<String, Boolean> getUserPA() {
        return userPA;
    }

    public void setUserPA(Map<String, Boolean> userPA) {
        this.userPA = userPA;
    }

}
