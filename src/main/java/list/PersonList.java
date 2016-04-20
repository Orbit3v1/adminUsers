package list;

import entity.Person;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Named("personList")
@Scope("request")
public class PersonList {

    @Inject
    private EntityManager entityManager;

    private List<Person> persons;

    private Person person = new Person("Duke", "Nukem", "duke@ukr.net", "Active");

    @PostConstruct
    public void init(){
        System.out.println("begin");
        Query query = entityManager.createQuery("select p from Person p");
        persons = query.getResultList();
        System.out.println("persons" + persons);

    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
