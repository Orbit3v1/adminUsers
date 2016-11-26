package com.app.data.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "person")
public class Person extends AbstractVersionedEntity{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_Name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "active")
    @Type(type = "yes_no")
    private boolean active = true;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @ManyToMany
    @JoinTable(name = "person_role",
        joinColumns = @JoinColumn(name = "person"),
        inverseJoinColumns = @JoinColumn(name = "role"))
    private List<Role> roles;

    @OneToMany(mappedBy = "developer")
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "responsible")
    private List<Order> orders;

    @OneToMany(mappedBy = "developer")
    private List<Specification> spDeveloped;

    @OneToMany(mappedBy = "responsible")
    private List<Specification> spResponsible;

    @OneToMany(mappedBy = "approvedBy")
    private List<Specification> spApproved;


    public Person() {
    }

    public Person(String firstName, String lastName, String email, boolean active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.active = active;
    }

    public Person(String firstName, String lastName, String email, boolean active, List<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.active = active;
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        return (getClass().hashCode() + Integer.valueOf(id).hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        return (obj != null && getClass() == obj.getClass())
                ? (id == ((Person) obj).id)
                : (obj == this);
    }

    @Override
    public String toString() {
        String res = "";
        if(lastName != null && !lastName.equals("")){
            res += lastName;
        }
        if(firstName != null && !firstName.equals("")){
            if(!res.equals("")){
                res += " ";
            }
            res += firstName;
        }
        if(res.equals("")){
            res = login;
        }
        return res;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean state) {
        this.active = state;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Specification> getSpDeveloped() {
        return spDeveloped;
    }

    public void setSpDeveloped(List<Specification> spDeveloped) {
        this.spDeveloped = spDeveloped;
    }

    public List<Specification> getSpResponsible() {
        return spResponsible;
    }

    public void setSpResponsible(List<Specification> spResponsible) {
        this.spResponsible = spResponsible;
    }

    public List<Specification> getSpApproved() {
        return spApproved;
    }

    public void setSpApproved(List<Specification> spApproved) {
        this.spApproved = spApproved;
    }
}
