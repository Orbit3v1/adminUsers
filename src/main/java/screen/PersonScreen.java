package screen;

import entity.*;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Named("personScreen")
@Scope("session")
public class PersonScreen {

    private Person person;
    @Inject
    private EntityManagerFactory entityManagerFactory;

    private boolean isEdit;

    @PostConstruct
    public void init() {
        System.out.println("personScreen");
    }

    public String editPerson(Person person) {
        this.person = person;
        isEdit = true;
        return "editPerson";
    }

    public String newPerson() {
        person = new Person();
        return "editPerson";
    }

    public String exit() {
        return "personList";
    }

    public void save() {
        if (validate()) {
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            person = em.merge(person);
            em.getTransaction().commit();
            em.close();
        } else {
            System.out.println("Error email");
        }

    }

    public String saveAndExit() {
        save();
        return exit();
    }

    private boolean validate() {
        boolean valid = true;
        if(person.getEmail().equals("")){
            person.setEmail(null);
        }

        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select p from Person p where p.email = :email and p.id != :id")
                .setParameter("email", person.getEmail())
                .setParameter("id", person.getId());
        List<Person> persons = query.getResultList();
        if (persons.size() != 0) {
            valid = false;
        }


        return valid;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
