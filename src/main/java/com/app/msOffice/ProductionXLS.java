package com.app.msOffice;

import com.app.data.dto.ProductionReportDTO;
import org.apache.poi.ss.usermodel.*;

import java.util.*;

/**
 * Created by Andrey on 02.07.2016.
 */
public class ProductionXLS extends GeneratorXLS{
    private List<ProductionReportDTO> listRows;

    public ProductionXLS(List<ProductionReportDTO> listRows, Map<String, Boolean> userPA) {
        super(userPA);
        this.listRows = listRows;
    }

    @Override
    protected String getReportName(){
        return "производство";
    }

    protected void generateHeader() {
        Row row;
        Cell cell;
        int lastCell = 0;
        row = sheet.createRow(lastRow++);

        if (userPA.get("nameR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("orderEntity.name"));
        }
        if (userPA.get("customerR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("orderEntity.customer"));
        }
        if (userPA.get("nomenclatureR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("orderItemEntity.nomenclature"));
        }
        if (userPA.get("countR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("orderItemEntity.count"));
        }
        if (userPA.get("materialR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("nomenclatureEntity.material"));
        }
        if (userPA.get("gibR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("nomenclatureEntity.gib"));
        }
        if (userPA.get("responsibleR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("orderEntity.responsible"));
        }
        if (userPA.get("startR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("orderEntity.start"));
        }
        if (userPA.get("docDateR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("orderItemEntity.docDate"));
        }
        if (userPA.get("developerR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("orderItemEntity.developer"));
        }
        if (userPA.get("endPlanR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("orderItemEntity.endPlan"));
        }
        if (userPA.get("endActualR")) {
            cell = row.createCell(lastCell);
            cell.setCellValue(resourceBundle.getString("orderItemEntity.endActual"));
        }

    }

    protected void generateBody() {
        Row row;
        Cell cell;

        for (ProductionReportDTO r : listRows) {
            int lastCell = 0;
            row = sheet.createRow(lastRow++);

            if (userPA.get("nameR")) {
                cell = row.createCell(lastCell++);
                cell.setCellValue(r.getName());
            }
            if (userPA.get("customerR")) {
                cell = row.createCell(lastCell++);
                cell.setCellValue(r.getCustomer());
            }
            if (userPA.get("nomenclatureR")) {
                cell = row.createCell(lastCell++);
                cell.setCellValue(r.getNomenclatureName());
            }
            if (userPA.get("countR")) {
                cell = row.createCell(lastCell++);
                cell.setCellValue(r.getCount());
            }
            if (userPA.get("materialR")) {
                cell = row.createCell(lastCell++);
                cell.setCellValue(r.getMaterial());
            }
            if (userPA.get("gibR")) {
                cell = row.createCell(lastCell++);
                if (r.getGib() != null) {
                    cell.setCellValue(r.getGib());
                }
            }
            if (userPA.get("responsibleR")) {
                cell = row.createCell(lastCell++);
                cell.setCellValue(r.getResponsible());
            }
            if (userPA.get("startR")) {
                cell = row.createCell(lastCell++);
                if (r.getStart() != null) {
                    cell.setCellValue(r.getStart());
                    cell.setCellStyle(csDate);
                }
            }
            if (userPA.get("docDateR")) {
                cell = row.createCell(lastCell++);
                if (r.getDocDate() != null) {
                    cell.setCellValue(r.getDocDate());
                    cell.setCellStyle(csDate);
                }
            }
            if (userPA.get("developerR")) {
                cell = row.createCell(lastCell++);
                cell.setCellValue(r.getDeveloper());
            }
            if (userPA.get("endPlanR")) {
                cell = row.createCell(lastCell++);
                if (r.getEndPlan() != null) {
                    cell.setCellValue(r.getEndPlan());
                    cell.setCellStyle(csDate);
                }
            }
            if (userPA.get("endActualR")) {
                cell = row.createCell(lastCell);
                if (r.getEndActual() != null) {
                    cell.setCellValue(r.getEndActual());
                    cell.setCellStyle(csDate);
                }
            }
        }
    }

}


