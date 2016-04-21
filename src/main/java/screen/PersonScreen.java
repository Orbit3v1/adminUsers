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

    private Person person = new Person("Duke", "Nukem", "duke@ukr.net", "Active");
    @Inject
    private TestInjection testInjection;
    @Inject
    private EntityManager entityManager;
    @Inject
    private EntityManagerFactory entityManagerFactory;
    @Inject
    private ResourceBundle messages;

    @PostConstruct
    public void init(){

        System.out.println("Test " + person.getLastName());
        System.out.println("TestInjection " + testInjection.getMessage());

        //createInitialTestData();
        //testEntityManagerInjection();
        //testHierarchy();

        //testAddPrivilegeAction();
        //testResourceBundle();


    }

    public void testResourceBundle(){
        Locale locale = new Locale("ru", "RU");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("strings");
        System.out.println(resourceBundle.getString("test"));
        System.out.println(messages.getString("test"));
    }

    public void setNewPerson(){
        EntityManager em = entityManagerFactory.createEntityManager();
        person = em.find(Person.class, 1);
    }

    public void updatePerson(){
        // person = entityManager.find(Person.class, 1);
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
//        person = entityManager.find(Person.class, 1);
        //, LockModeType.OPTIMISTIC
        person = em.find(Person.class, 1);
        person.setLastName(person.getLastName() + "T");
        em.getTransaction().commit();
//        EntityManager em = entityManagerFactory.createEntityManager();
//
//        em.getTransaction().begin();
//        Privilege privilege = em.find(Privilege.class, "TEST_P");
//        System.out.println(privilege.getName());
//        privilege.setDescription("Test");
//        em.flush();
//        em.getTransaction().commit();
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
