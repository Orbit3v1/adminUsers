package com.app.validator;

import com.app.data.entity.Role;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


public class RoleValidatorTest {

    RoleValidator validator;

    @Before
    public void init(){
        validator = new RoleValidator();
        validator.addMessage = (componentId, bundleKey, type) -> {};
    }

    @Test
    public void isValidIdOk() {

        Role role = new Role();
        role.setId("Test_123");
        validator.entity = role;

        List<Role> rolesWithSameId = new ArrayList<>();

        RoleValidator spy = Mockito.spy(validator);
        Mockito.doReturn(rolesWithSameId).when(spy).getRolesWithSameId();

        boolean result = spy.isValidId();
        assertTrue(result);
    }

    @Test
    public void isValidIdWrongCharacters() {

        Role role = new Role();
        role.setId("Test_123!б");
        validator.entity = role;

        List<Role> rolesWithSameId = new ArrayList<>();

        RoleValidator spy = Mockito.spy(validator);
        Mockito.doReturn(rolesWithSameId).when(spy).getRolesWithSameId();

        boolean result = spy.isValidId();
        assertFalse(result);
    }

    @Test
    public void isValidIdEmpty() {

        Role role = new Role();
        role.setId("");
        validator.entity = role;

        List<Role> rolesWithSameId = new ArrayList<>();

        RoleValidator spy = Mockito.spy(validator);
        Mockito.doReturn(rolesWithSameId).when(spy).getRolesWithSameId();

        boolean result = spy.isValidId();
        assertFalse(result);
    }

    @Test
    public void isValidIdNull() {

        Role role = new Role();
        validator.entity = role;

        List<Role> rolesWithSameId = new ArrayList<>();

        RoleValidator spy = Mockito.spy(validator);
        Mockito.doReturn(rolesWithSameId).when(spy).getRolesWithSameId();

        boolean result = spy.isValidId();
        assertFalse(result);
    }

    @Test
    public void isValidIdSameExists() {

        Role role = new Role();
        role.setId("Test");
        validator.entity = role;

        List<Role> rolesWithSameId = new ArrayList<>();
        rolesWithSameId.add(new Role());

        RoleValidator spy = Mockito.spy(validator);
        Mockito.doReturn(rolesWithSameId).when(spy).getRolesWithSameId();

        boolean result = spy.isValidId();
        assertFalse(result);
    }

    @Test
    public void isValidNameOk() {

        Role role = new Role();
        role.setName("Test");
        validator.entity = role;

        List<Role> rolesWithSameName = new ArrayList<>();

        RoleValidator spy = Mockito.spy(validator);
        Mockito.doReturn(rolesWithSameName).when(spy).getRolesWithSameName();

        boolean result = spy.isValidName();
        assertTrue(result);
    }

    @Test
    public void isValidNameEmpty() {

        Role role = new Role();
        role.setName("");
        validator.entity = role;

        List<Role> rolesWithSameName = new ArrayList<>();

        RoleValidator spy = Mockito.spy(validator);
        Mockito.doReturn(rolesWithSameName).when(spy).getRolesWithSameName();

        boolean result = spy.isValidName();
        assertFalse(result);
    }

    @Test
    public void isValidNameNull() {

        Role role = new Role();
        validator.entity = role;

        List<Role> rolesWithSameName = new ArrayList<>();

        RoleValidator spy = Mockito.spy(validator);
        Mockito.doReturn(rolesWithSameName).when(spy).getRolesWithSameName();

        boolean result = spy.isValidName();
        assertFalse(result);
    }

    @Test
    public void isValidNameSameExists() {

        Role role = new Role();
        role.setName("Test");
        validator.entity = role;

        List<Role> rolesWithSameName = new ArrayList<>();
        rolesWithSameName.add(new Role());

        RoleValidator spy = Mockito.spy(validator);
        Mockito.doReturn(rolesWithSameName).when(spy).getRolesWithSameName();

        boolean result = spy.isValidName();
        assertFalse(result);
    }
}
