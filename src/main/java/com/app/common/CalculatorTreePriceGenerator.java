package com.app.common;

import com.app.data.dto.CalculationDTO;
import com.app.data.entity.*;
import com.app.utils.AppUtil;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

@Named("calculatorTreePriceGenerator")
@Scope("request")
public class CalculatorTreePriceGenerator {

    private TreeNode priceRoot;
    private Map<String, CalculationDTO> groupedProducts;

    public TreeNode generate(TreeNode calcRoot) {
        init();

        groupProducts(calcRoot);
        createPriceHierarchy();
        populatePrice(priceRoot);

        return priceRoot;
    }

    private void init() {
        groupedProducts = new HashMap<>();
        priceRoot = new DefaultTreeNode(null, null);
    }

    private void groupProducts(TreeNode node) {
        CalculationDTO calculationDTO = (CalculationDTO) node.getData();
        if (calculationDTO != null) {
            String name = calculationDTO.getName();
            if (groupedProducts.containsKey(name)) {
                groupedProducts.get(name).merge(calculationDTO);
            } else {
                groupedProducts.put(name, new CalculationDTO(calculationDTO));
            }
        }
        for (TreeNode n : node.getChildren()) {
            groupProducts(n);
        }
    }

    private void createPriceHierarchy() {
        CalculationDTO fullPriceDTO = new CalculationDTO("Полная себестоимость");
        TreeNode fullPriceNode = new DefaultTreeNode(fullPriceDTO, priceRoot);
        fullPriceNode.setExpanded(true);

        CalculationDTO productionPriceDTO = new CalculationDTO("Производственная себестоимость");
        TreeNode productionPriceNode = new DefaultTreeNode(productionPriceDTO, fullPriceNode);
        productionPriceNode.setExpanded(true);

        CalculationDTO directPriceDTO = new CalculationDTO("Прямые затраты");
        TreeNode directPriceNode = new DefaultTreeNode(directPriceDTO, productionPriceNode);
        directPriceNode.setExpanded(true);

        CalculationDTO materialsDTO = new CalculationDTO("Материалы");
        TreeNode materialsNode = new DefaultTreeNode(materialsDTO, directPriceNode);
        materialsNode.setExpanded(true);

        for (CalculationDTO tnc : groupedProducts.values()) {
            if (tnc.getProduct() instanceof ProductTNC) {
                CalculationDTO tncDTO = new CalculationDTO(tnc);
                new DefaultTreeNode(tncDTO, materialsNode);
            }
        }

        CalculationDTO salaryDTO = new CalculationDTO("Заработная плата рабочих");
        TreeNode salaryNode = new DefaultTreeNode(salaryDTO, directPriceNode);
        salaryNode.setExpanded(true);

        for (CalculationDTO tnc : groupedProducts.values()) {
            if (tnc.getProduct() instanceof ProductWork) {
                CalculationDTO tncDTO = new CalculationDTO(tnc);
                new DefaultTreeNode(tncDTO, salaryNode);
            }
        }
    }

    private void populatePrice(TreeNode node) {
        for (TreeNode childNode : node.getChildren()) {
            populatePrice(childNode);
            CalculationDTO dto = (CalculationDTO) node.getData();
            if (dto != null) {
                dto.setPrice(AppUtil.add(dto.getPrice(), ((CalculationDTO) childNode.getData()).getPrice()));
            }
        }
    }


}
