import javax.persistence.*;

/**
 * Created by Andrey on 16.04.2016.
 */
@Entity
@Table(name = "CITY", schema = "sakila")
public class Person {
    @Id
    @GeneratedValue
    @Column(name = "city_id")
    private int id;

    @Column(name = "city")
    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
