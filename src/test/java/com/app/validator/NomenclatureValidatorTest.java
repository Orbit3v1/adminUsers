package com.app.validator;


import com.app.data.entity.Nomenclature;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NomenclatureValidatorTest {

    NomenclatureValidator validator;

    @Before
    public void init(){
        validator = new NomenclatureValidator();
        validator.addMessage = (componentId, bundleKey, type) -> {};
    }

    @Test
    public void isValidNameOk() {

        Nomenclature nomenclature = new Nomenclature();
        nomenclature.setName("Test");
        validator.entity = nomenclature;

        List<Nomenclature> nomenclaturesWithSameName = new ArrayList<>();

        NomenclatureValidator spy = Mockito.spy(validator);
        Mockito.doReturn(nomenclaturesWithSameName).when(spy).getNomenclaturesWithSameName();

        boolean result = spy.isValidName();
        assertTrue(result);
    }

    @Test
    public void isValidNameEmpty() {

        Nomenclature nomenclature = new Nomenclature();
        nomenclature.setName("");
        validator.entity = nomenclature;

        List<Nomenclature> nomenclaturesWithSameName = new ArrayList<>();

        NomenclatureValidator spy = Mockito.spy(validator);
        Mockito.doReturn(nomenclaturesWithSameName).when(spy).getNomenclaturesWithSameName();

        boolean result = spy.isValidName();
        assertFalse(result);
    }

    @Test
    public void isValidNameNull() {

        Nomenclature nomenclature = new Nomenclature();
        validator.entity = nomenclature;

        List<Nomenclature> nomenclaturesWithSameName = new ArrayList<>();

        NomenclatureValidator spy = Mockito.spy(validator);
        Mockito.doReturn(nomenclaturesWithSameName).when(spy).getNomenclaturesWithSameName();

        boolean result = spy.isValidName();
        assertFalse(result);
    }

    @Test
    public void isValidNameSameExists() {

        Nomenclature nomenclature = new Nomenclature();
        nomenclature.setName("Test");
        validator.entity = nomenclature;

        List<Nomenclature> nomenclaturesWithSameName = new ArrayList<>();
        nomenclaturesWithSameName.add(new Nomenclature());

        NomenclatureValidator spy = Mockito.spy(validator);
        Mockito.doReturn(nomenclaturesWithSameName).when(spy).getNomenclaturesWithSameName();

        boolean result = spy.isValidName();
        assertFalse(result);
    }

    @Test
    public void isValidGibOk() {

        validator.gib = "12345";

        boolean result = validator.isValidGib();
        assertTrue(result);
    }

    @Test
    public void isValidGibNotNumber() {

        validator.gib = "a12";

        boolean result = validator.isValidGib();
        assertFalse(result);
    }
}
