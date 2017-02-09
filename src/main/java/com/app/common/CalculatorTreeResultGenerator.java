package com.app.common;

import com.app.data.dao.FunctionDao;
import com.app.data.dto.CalculationDTO;
import com.app.data.entity.Function;
import com.app.data.entity.Product;
import com.app.data.entity.ProductInParameter;
import com.app.utils.JSEngine;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.script.ScriptException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@Named("calculatorTreeResultGenerator")
@Scope("request")
public class CalculatorTreeResultGenerator {

    @Inject
    private FunctionDao functionDao;
    @Inject
    private JSEngine jsEngine;

    private Map<Product, String> errorProducts;
    private String includedFunctions;
    private String inParameters;
    private Product rootEntity;
    private TreeNode resultRoot;

    public void calculate(Product entity) throws ScriptException {
        this.rootEntity = entity;
        init();

        String formula = includedFunctions + " " + inParameters;
        generateCalcTree(resultRoot, rootEntity, formula);
        if (hasErrors()) {
            throw new ScriptException("Functions have errors");
        }
    }

    private void init(){
        initFunctions();
        initInParameters();
        errorProducts = new HashMap<>();
        resultRoot = new DefaultTreeNode();
    }


    @Transactional
    private void initFunctions() {
        includedFunctions = "";
        List<Function> functions = functionDao.getAll();
        for (Function f : functions) {
            includedFunctions += f.getCode();
        }
    }

    private void initInParameters() {
        inParameters = "";
        for(ProductInParameter p : rootEntity.getInParameters()){
            inParameters += p.getName() + " = " + p.getValue() + ";";
        }
    }

    private void generateCalcTree(TreeNode parentNode, Product product, String parentFormula) {
        String formula = parentFormula + ";" + generateFormula(product);
        CalculationDTO calc = new CalculationDTO(product.getName());
        try {
            calc.setCount(calculateParameter(formula, product.getCount()));
            calc.setHeight(calculateParameter(formula, product.getHeight()));
            calc.setLength(calculateParameter(formula, product.getLength()));
            calc.setWidth(calculateParameter(formula, product.getWidth()));
            if(hasValue(product.getFormula())) {
                calc.setFormula(executeCode(formula));
            }
            updateCalcName(calc, product);
        } catch (ScriptException e) {
            e.printStackTrace();
            errorProducts.put(product, e.getMessage());
        }

        //calc.setPrice(getPrice(product, formulaResult));

        TreeNode tn = new DefaultTreeNode(calc, parentNode);
        tn.setExpanded(true);

        for (Product p : product.getSubordinates()) {
            generateCalcTree(tn, p, formula);
        }
    }

    private void updateCalcName(CalculationDTO calc, Product product){
        String name = calc.getName();
        name = replaceAll(name, product.getCountAlias(), calc.getCount());
        name = replaceAll(name, product.getHeightAlias(), calc.getHeight());
        name = replaceAll(name, product.getLengthAlias(), calc.getLength());
        name = replaceAll(name, product.getWidthAlias(), calc.getWidth());
        calc.setName(name);
    }

    private String generateFormula(Product product){
        StringJoiner result = new StringJoiner(";");
        if(hasValue(product.getDetail())) {
            result.add(product.getDetail());
        }
        if(hasValue(product.getFormula())) {
            result.add(product.getFormula());
        }
        return result.toString();
    }

    private BigDecimal calculateParameter(String formula, String parameter) throws ScriptException {
        BigDecimal result = null;

        if(hasValue(parameter)) {
            formula += "; " + parameter;
            result = executeCode(formula);
        }
        return result;
    }

    private BigDecimal executeCode(String formula) throws ScriptException {
        String result = jsEngine.calculate(formula);
        return new BigDecimal(result);
    }

    private String replaceAll(String str, String regex, BigDecimal val){
        if(!hasValue(regex)){
            return str;
        }
        return str.replaceAll(regex, bigDecimalToString(val));
    }

    private String bigDecimalToString(BigDecimal val){
        String result = "0";
        if(val != null){
            result = val.setScale(0, BigDecimal.ROUND_CEILING).toString();
        }
        return result;
    }

    private boolean hasValue(String code){
        return code != null && !code.replaceAll(" ", "").equals("");
    }

    public boolean hasErrors() {
        return errorProducts.size() != 0;
    }

    public TreeNode getResultRoot() {
        return resultRoot;
    }

    public void setResultRoot(TreeNode resultRoot) {
        this.resultRoot = resultRoot;
    }

    public Map<Product, String> getErrorProducts() {
        return errorProducts;
    }

    public void setErrorProducts(Map<Product, String> errorProducts) {
        this.errorProducts = errorProducts;
    }
}
