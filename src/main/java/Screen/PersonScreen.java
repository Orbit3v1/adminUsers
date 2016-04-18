package Screen;

import Entity.Person;
import Entity.Privilege;
import Entity.Role;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;

@Named("personScreen")
@Scope("session")
public class PersonScreen {

    private Person person = new Person("Duke", "Nukem", "duke@ukr.net", "Active");
    @Inject
    private TestInjection testInjection;
    @Inject
    private EntityManager entityManager;

    @PostConstruct
    public void init(){

        System.out.println("Test " + person.getLastName());
        System.out.println("TestInjection " + testInjection.getMessage());

        //createInitialTestData();
        //testPersist();
        testEntityManagerInjection();
    }

    private void createInitialTestData(){
        Privilege privilege = new Privilege("INIT", "Init Priv", "Test priv");
        ArrayList<Privilege> privileges = new ArrayList<>();
        privileges.add(privilege);

        Role role = new Role("INIT", "Init Role", "Test role", privileges);
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(role);

        Person person = new Person("Init", "User", "init@ukr.net", "Active", roles);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MainPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(privilege);
        em.persist(role);
        em.persist(person);
        em.getTransaction().commit();
    }

    private void testPersist(){
        Privilege privilege = new Privilege("Persist", "Persist Priv", "Test Persist");
        ArrayList<Privilege> privileges = new ArrayList<>();
        privileges.add(privilege);

        Role role = new Role("Persist", "Persist Role", "Persist role", privileges);
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(role);

        Person person = new Person("Persist", "User", "persist@ukr.net", "Active", roles);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MainPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        //don't persist child entities
//        em.persist(privilege);
//        em.persist(role);
        em.persist(person);
        em.getTransaction().commit();
    }

    private void testEntityManagerInjection(){
        System.out.print(entityManager);
        person = entityManager.find(Person.class, 1);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
