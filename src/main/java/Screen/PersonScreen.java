package Screen;

import Entity.*;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

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
        //testEntityManagerInjection();
        //testHierarchy();

        testAddPrivilegeAction();
    }

    private void testAddPrivilegeAction(){
        Person person = entityManager.find(Person.class, 1);
        Role role = person.getRoles().get(0);
        Action action = entityManager.find(Action.class, "READ");
        Privilege privilege = entityManager.find(Privilege.class, "TEST_P");
        PrivilegeAction privilegeAction = new PrivilegeAction(new PrivilegeActionId(privilege.getId(), action.getId()));
        role.getPrivilegeAction().add(privilegeAction);

        entityManager.getTransaction().begin();
        entityManager.persist(role);
        entityManager.getTransaction().commit();

    }

    private void testHierarchy(){
        Person person = entityManager.find(Person.class, 1);
        System.out.println("Person: " + person.getEmail());
        Role role = person.getRoles().get(0);
        System.out.println("Role: " + role.getName());
        PrivilegeAction privilegeAction = role.getPrivilegeAction().get(0);
        System.out.println("PrivilegeAction: " + privilegeAction.getAction().getName() + " / " + privilegeAction.getPrivilege().getName());
    }

    private void createInitialTestData(){
        Privilege privilege = new Privilege("INIT", "Init Priv", "Test priv");
        ArrayList<Privilege> privileges = new ArrayList<>();
        privileges.add(privilege);

        Role role = new Role("INIT", "Init Role", "Test role", new ArrayList<>());
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
