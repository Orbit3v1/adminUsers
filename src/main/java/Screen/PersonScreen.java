package Screen;

import Entity.Person;
import org.springframework.context.annotation.Scope;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Named("personScreen")
@Scope("session")
public class PersonScreen {

    private Person person = new Person("Duke");
    @Inject
    private TestInjection testInjection;

    public void init(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MainPU");
        EntityManager em = emf.createEntityManager();
        person = em.find(Person.class, 1);
        System.out.println("Test " + person.getName());
        System.out.println("TestInjection " + testInjection.getMessage());
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
