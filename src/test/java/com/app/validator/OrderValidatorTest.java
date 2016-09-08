package com.app.validator;

import com.app.data.entity.Order;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by ayaroslavtsev on 01.06.2016.
 */
public class OrderValidatorTest {

    OrderValidator validator;

    @Before
    public void init(){
        validator = new OrderValidator();
        validator.addMessage = (componentId, bundleKey, type) -> {};
    }

    @Test
    public void isValidNameOk() {

        Order order = new Order();
        order.setName("Test");
        validator.entity = order;

        List<Order> ordersWithSameName = new ArrayList<>();

        OrderValidator spy = Mockito.spy(validator);
        Mockito.doReturn(ordersWithSameName).when(spy).getOrderWithSameName();

        boolean result = spy.isValidName();
        assertTrue(result);
    }

    @Test
    public void isValidNameEmpty() {

        Order order = new Order();
        order.setName("");
        validator.entity = order;

        List<Order> ordersWithSameName = new ArrayList<>();

        OrderValidator spy = Mockito.spy(validator);
        Mockito.doReturn(ordersWithSameName).when(spy).getOrderWithSameName();

        boolean result = spy.isValidName();
        assertFalse(result);
    }

    @Test
    public void isValidNameNull() {

        Order order = new Order();
        validator.entity = order;

        List<Order> ordersWithSameName = new ArrayList<>();

        OrderValidator spy = Mockito.spy(validator);
        Mockito.doReturn(ordersWithSameName).when(spy).getOrderWithSameName();

        boolean result = spy.isValidName();
        assertFalse(result);
    }

    @Test
    public void isValidNameSameExists() {

        Order order = new Order();
        order.setName("Test");
        validator.entity = order;

        List<Order> ordersWithSameName = new ArrayList<>();
        ordersWithSameName.add(new Order());

        OrderValidator spy = Mockito.spy(validator);
        Mockito.doReturn(ordersWithSameName).when(spy).getOrderWithSameName();

        boolean result = spy.isValidName();
        assertFalse(result);
    }

//    @Test
//    public void isValidNomenclatureOk() {
//
//        Order order = new Order();
//    //    order.setNomenclature(new Nomenclature());
//        validator.entity = order;
//
//        boolean result = validator.isValidNomenclature();
//        assertTrue(result);
//    }
//
//    @Test
//    public void isValidNomenclatureNull() {
//
//        Order order = new Order();
//        validator.entity = order;
//
//        boolean result = validator.isValidNomenclature();
//        assertFalse(result);
//    }

//    @Test
//    public void isValidCountOk() {
//
//        validator.count = "12345";
//
//        boolean result = validator.isValidCount();
//        assertTrue(result);
//    }
//
//    @Test
//    public void isValidCountEmpty() {
//
//        validator.count = "";
//
//        boolean result = validator.isValidCount();
//        assertFalse(result);
//    }
//
//    @Test
//    public void isValidCountNull() {
//
//        validator.count = null;
//
//        boolean result = validator.isValidCount();
//        assertFalse(result);
//    }
//
//    @Test
//    public void isValidCountNotNumber() {
//
//        validator.count = "a12";
//
//        boolean result = validator.isValidCount();
//        assertFalse(result);
//    }
}
