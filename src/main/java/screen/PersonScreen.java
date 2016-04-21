package screen;

import entity.*;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

@Named("personScreen")
@Scope("session")
public class PersonScreen {

    private Person person;
    @Inject
    private EntityManagerFactory entityManagerFactory;

    @PostConstruct
    public void init(){
        System.out.println("personScreen");
    }

    public String editPerson(Person person){
        this.person = person;
        return "editPerson";
    }

    public String newPerson(){
        person = new Person();
        return "editPerson";
    }

    public String exit(){
        return "personList";
    }

    public void save(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        person = em.merge(person);
        em.getTransaction().commit();
        em.close();

    }

    public String saveAndExit(){
        save();
        return exit();
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
