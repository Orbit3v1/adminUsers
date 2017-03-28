package com.app.msOffice;

import com.app.data.dto.SpecificationDTO;
import org.apache.poi.ss.usermodel.*;

import java.util.*;

/**
 * Created by ayaroslavtsev on 30.08.2016.
 */
public class SpecificationXLS extends GeneratorXLS{

    private List<SpecificationDTO> listRows;

    public SpecificationXLS(List<SpecificationDTO> listRows, Map<String, Boolean> userPA) {
        super(userPA);
        this.listRows = listRows;
    }

    @Override
    protected String getReportName() {
        return "реестрт_ТЗ";
    }

    protected void generateHeader() {
        Row row;
        Cell cell;
        int lastCell = 0;
        row = sheet.createRow(lastRow++);

        if (userPA.get("nameR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("specificationEntity.name"));
        }
        if (userPA.get("startR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("specificationEntity.start"));
        }
        if (userPA.get("typeR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("specificationEntity.type"));
        }
        if (userPA.get("nomenclatureR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("specificationEntity.nomenclature"));
        }
        if (userPA.get("priceR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("specificationEntity.price"));
        }
        if (userPA.get("discountR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("specificationEntity.discount"));
        }
        if (userPA.get("responseDateR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("specificationEntity.responseDate"));
        }
        if (userPA.get("developerR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("specificationEntity.developer"));
        }
        if (userPA.get("responsibleR")) {
            cell = row.createCell(lastCell);
            cell.setCellValue(resourceBundle.getString("specificationEntity.responsible"));
        }
    }

    protected void generateBody() {
        Row row;
        Cell cell;

        for (SpecificationDTO r : listRows) {
            int lastCell = 0;
            row = sheet.createRow(lastRow++);

            if (userPA.get("nameR")) {
                cell = row.createCell(lastCell++);
                cell.setCellValue(r.getName());
            }
            if (userPA.get("startR")) {
                cell = row.createCell(lastCell++);
                if (r.getStart() != null) {
                    cell.setCellValue(r.getStart());
                    cell.setCellStyle(csDate);
                }
            }
            if (userPA.get("typeR")) {
                cell = row.createCell(lastCell++);
                if (r.getStart() != null) {
                    cell.setCellValue(r.getType());
                }
            }
            if (userPA.get("nomenclatureR")) {
                cell = row.createCell(lastCell++);
                if (r.getNomenclatureName() != null) {
                    cell.setCellValue(r.getNomenclatureName());
                }
            }
            if (userPA.get("priceR")) {
                cell = row.createCell(lastCell++);
                if (r.getPrice() != null) {
                    cell.setCellValue(r.getPrice());
                }
            }
            if (userPA.get("discountR")) {
                cell = row.createCell(lastCell++);
                if (r.getDiscount() != null) {
                    cell.setCellValue(r.getDiscount());
                }
            }
            if (userPA.get("responseDateR")) {
                cell = row.createCell(lastCell++);
                if (r.getResponseDate() != null) {
                    cell.setCellValue(r.getResponseDate());
                    cell.setCellStyle(csDate);
                }
            }
            if (userPA.get("developerR")) {
                cell = row.createCell(lastCell++);
                if (r.getDeveloper() != null) {
                    cell.setCellValue(r.getDeveloper());
                }
            }
            if (userPA.get("responsibleR")) {
                cell = row.createCell(lastCell);
                if (r.getResponsible() != null) {
                    cell.setCellValue(r.getResponsible());
                }
            }
        }
    }
}

