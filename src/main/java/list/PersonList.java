package list;

import entity.Person;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Named("personList")
@Scope("request")
public class PersonList {

    @Inject
    private EntityManagerFactory entityManagerFactory;

    private List<Person> persons;


    @PostConstruct
    public void init(){
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select p from Person p");
        persons = query.getResultList();
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
