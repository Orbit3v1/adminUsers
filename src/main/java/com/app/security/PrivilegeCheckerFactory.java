package com.app.security;

public class PrivilegeCheckerFactory {

    private static PrivilegeCheckerFactory factory;

    private PrivilegeCheckerFactory() {
    }

    public static PrivilegeCheckerFactory getFactory() {
        if (factory == null) {
            factory = new PrivilegeCheckerFactory();
        }
        return factory;
    }

    public PrivilegeChecker getChecker(String screenName) {
        PrivilegeChecker privilegeChecker = null;
        switch (screenName) {
            case ("personScreen"):
                privilegeChecker = new PersonScreenPC();
                break;
            case ("personList"):
                privilegeChecker = new PersonListPC();
                break;
            case ("roleList"):
                privilegeChecker = new RoleListPC();
                break;
            case ("roleScreen"):
                privilegeChecker = new RoleScreenPC();
                break;
            case ("nomenclatureList"):
                privilegeChecker = new NomenclatureListPC();
                break;
            case ("nomenclatureScreen"):
                privilegeChecker = new NomenclatureScreenPC();
                break;
            case ("orderScreen"):
                privilegeChecker = new OrderScreenPC();
                break;
            case ("orderList"):
                privilegeChecker = new OrderListPC();
                break;
            case ("orderItemScreen"): {
                privilegeChecker = new OrderItemScreenPC();
                break;
            }
            case ("specificationList"): {
                privilegeChecker = new SpecificationListPC();
                break;
            }
            case ("specificationScreen"): {
                privilegeChecker = new SpecificationScreenPC();
                break;
            }
            case ("tncRequestList"): {
                privilegeChecker = new TNCRequestListPC();
                break;
            }
            case ("TNCList"):{
                privilegeChecker = new TNCListPC();
                break;
            }
            case ("TNCRequestScreen"):{
                privilegeChecker = new TNCRequestScreenPC();
                break;
            }
            case ("TNCRequestItemScreen"):{
                privilegeChecker = new TNCRequestItemScreenPC();
                break;
            }
            case ("tncSupplyList"):{
                privilegeChecker = new TNCSupplyListPC();
                break;
            }
            case ("tncSupplyScreen"):{
                privilegeChecker = new TNCSupplyScreenPC();
                break;
            }
            case ("tncSupplyItemScreen"):{
                privilegeChecker = new TNCSupplyItemScreenPC();
                break;
            }
            case ("menu"): {
                privilegeChecker = new MenuPC();
                break;
            }
            default:{
                privilegeChecker = new DefaultPC();
                break;
            }
        }
        return privilegeChecker;
    }
}
