package com.app.data.dao.hibernate;

import com.app.data.dao.PersonDao;
import com.app.data.entity.Person;
import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

public class HPersonDao extends HGenericDao<Person, Integer> implements PersonDao {

    public HPersonDao() {
        super(Person.class);
    }

    @Override
    public Person getByIdWithResources(Integer id, Collection<Resource> resources) {
        Person entity = getById(id);
        if (resources.contains(Resource.ROLES)) {
            entity.getRoles().size();
        }
        return entity;
    }

    @Override
    public Person getByLogin(String login, String password) {
        Person person = null;
        Query query = em.createQuery(
                "select p " +
                        "from Person p " +
                        "left join fetch p.roles r " +
                        "left join fetch r.privilegeAction pa " +
                        "left join fetch pa.action a " +
                        "left join fetch pa.privilege pr " +
                        "where p.login = :login " +
                        "and p.password = :password")
                .setParameter("login", login)
                .setParameter("password", password);
        List<Person> persons = query.getResultList();
        if (persons.size() != 0) {
            person = persons.get(0);
        }
        return person;
    }

    @Override
    public List<Person> getAll() {
        Query query = em.createQuery("select p from Person p order by p.lastName, p.firstName");
        return query.getResultList();
    }
}
