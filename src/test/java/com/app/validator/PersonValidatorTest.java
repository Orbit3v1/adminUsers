package com.app.validator;

import com.app.data.entity.Person;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class PersonValidatorTest {

    PersonValidator validator;

    @Before
    public void init(){
        validator = new PersonValidator();
        validator.addMessage = (componentId, bundleKey, type) -> {};
    }

    @Test
    public void isValidEmailOk() {

        Person person = new Person();
        person.setEmail("test@test.com");
        validator.entity = person;

        List<Person> personsWithSameEmail = new ArrayList<>();

        PersonValidator spy = Mockito.spy(validator);
        Mockito.doReturn(personsWithSameEmail).when(spy).getPersonsWithSameEmail();

        boolean result = spy.isValidEmail();
        assertTrue(result);
    }

    @Test
    public void isValidEmailSameExists() {
        Person person = new Person();
        person.setEmail("test@test.com");
        validator.entity = person;

        List<Person> personsWithSameEmail = new ArrayList<>();
        personsWithSameEmail.add(new Person());

        PersonValidator spy = Mockito.spy(validator);
        Mockito.doReturn(personsWithSameEmail).when(spy).getPersonsWithSameEmail();

        boolean result = spy.isValidEmail();
        assertFalse(result);
    }

    @Test
    public void isValidLoginOk() {
        Person person = new Person();
        person.setLogin("test");
        validator.entity = person;

        List<Person> personsWithSameLogin = new ArrayList<>();

        PersonValidator spy = Mockito.spy(validator);
        Mockito.doReturn(personsWithSameLogin).when(spy).getPersonsWithSameLogin();

        boolean result = spy.isValidLogin();
        assertTrue(result);
    }

    @Test
    public void isValidLoginSameExists() {
        Person person = new Person();
        person.setLogin("test");
        validator.entity = person;

        List<Person> personsWithSameLogin = new ArrayList<>();
        personsWithSameLogin.add(new Person());

        PersonValidator spy = Mockito.spy(validator);
        Mockito.doReturn(personsWithSameLogin).when(spy).getPersonsWithSameLogin();

        boolean result = spy.isValidLogin();
        assertFalse(result);
    }

    @Test
    public void isValidLoginEmpty() {
        Person person = new Person();
        person.setLogin("");
        validator.entity = person;

        List<Person> personsWithSameLogin = new ArrayList<>();

        PersonValidator spy = Mockito.spy(validator);
        Mockito.doReturn(personsWithSameLogin).when(spy).getPersonsWithSameLogin();

        boolean result = spy.isValidLogin();
        assertFalse(result);
    }

    @Test
    public void isValidLoginNull() {
        Person person = new Person();
        validator.entity = person;

        List<Person> personsWithSameLogin = new ArrayList<>();

        PersonValidator spy = Mockito.spy(validator);
        Mockito.doReturn(personsWithSameLogin).when(spy).getPersonsWithSameLogin();

        boolean result = spy.isValidLogin();
        assertFalse(result);
    }

    @Test
    public void isValidPasswordEditEmpty() {
        Person person = new Person();
        person.setPassword("");
        validator.entity = person;
        validator.edit = true;

        boolean result = validator.isValidPassword();
        assertTrue(result);
    }

    @Test
    public void isValidPasswordNewOk() {
        Person person = new Person();
        person.setPassword("test");
        validator.entity = person;
        validator.edit = false;

        boolean result = validator.isValidPassword();
        assertTrue(result);
    }

    @Test
    public void isValidPasswordNewEmpty() {
        Person person = new Person();
        person.setPassword("");
        validator.entity = person;
        validator.edit = false;

        boolean result = validator.isValidPassword();
        assertFalse(result);
    }

    @Test
    public void isValidPasswordNewNull() {
        Person person = new Person();
        validator.entity = person;
        validator.edit = false;

        boolean result = validator.isValidPassword();
        assertFalse(result);
    }
}
