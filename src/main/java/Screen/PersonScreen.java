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

    @PostConstruct
    public void init(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MainPU");
        EntityManager em = emf.createEntityManager();
        person = em.find(Person.class, 1);
        System.out.println(person.getRoles());

        //Role roleP = em.find(Role.class, "CONST");
        Role role = new Role("WORKER", "Worker", "Has all privileges2");
        System.out.println("Role" + role);

        Privilege privilege = new Privilege("DELETE", "delete", "delete all");
        ArrayList<Privilege> privileges = new ArrayList<>();
        privileges.add(privilege);

        System.out.println("Privilege" + privilege);


        em.getTransaction().begin();
        em.persist(privilege);
        System.out.println("1");
        role.setPrivilege(privileges);
        em.persist(role);
        System.out.println("2");
        person.getRoles().add(role);
        em.persist(person); //em.merge(u); for updates
        System.out.println("3");
        em.getTransaction().commit();


//        Role role = new Role("CEO", "Director", "Has all privileges2");
//        Role role2 = new Role("CONST", "Constructor", "Has all privileges");
//        em.getTransaction().begin();
//        //em.merge(role); //em.merge(u); for updates
//        em.persist(role2); //em.merge(u); for updates
//        em.getTransaction().commit();
//        em.close();

        System.out.println("Test " + person.getLastName());
        System.out.println("TestInjection " + testInjection.getMessage());
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
