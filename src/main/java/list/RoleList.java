package list;

import entity.Person;
import entity.Role;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Named("roleList")
@Scope("request")
public class RoleList {
    @Inject
    private EntityManagerFactory entityManagerFactory;

    private List<Role> roles;


    @PostConstruct
    public void init(){
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select r from Role r order by r.id");
        roles = query.getResultList();
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
