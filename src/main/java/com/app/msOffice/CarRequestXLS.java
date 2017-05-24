package com.app.msOffice;

import com.app.data.dto.CarRequestDTO;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;

import java.util.*;


public class CarRequestXLS extends GeneratorXLS {
    private List<CarRequestDTO> listRows;

    public CarRequestXLS(List<CarRequestDTO> listRows, Map<String, Boolean> userPA) {
        super(userPA);
        this.listRows = listRows;
        setColumnsWidth();
    }

    private void setColumnsWidth(){
        sheet.setColumnWidth(0, 3000);
        sheet.setColumnWidth(1, 3500);
        sheet.setColumnWidth(2, 4000);
        sheet.setColumnWidth(3, 4000);
        sheet.setColumnWidth(4, 4000);
        sheet.setColumnWidth(5, 4000);
        sheet.setColumnWidth(6, 4000);
        sheet.setColumnWidth(7, 4000);
        sheet.setColumnWidth(8, 4000);
        sheet.setColumnWidth(9, 3000);
        sheet.setColumnWidth(10, 3500);
        sheet.setColumnWidth(11, 3500);
    }

    @Override
    protected String getReportName() {
        return "заявка_на_авто";
    }

    protected void generateHeader() {
        Row row;
        Cell cell;
        int lastCell = 0;
        row = sheet.createRow(lastRow++);

        if (userPA.get("nameR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("carRequestEntity.name"));
            cell.setCellStyle(csHeader);
        }
        if (userPA.get("startR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("carRequestList.time"));
            cell.setCellStyle(csHeader);
        }
        if (userPA.get("responsibleR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("carRequestEntity.responsible"));
            cell.setCellStyle(csHeader);
        }
        if (userPA.get("addressFromR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("carRequestEntity.addressFrom"));
            cell.setCellStyle(csHeader);
        }
        if (userPA.get("addressToR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("carRequestEntity.addressTo"));
            cell.setCellStyle(csHeader);
        }
        if (userPA.get("receiverNameR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("carRequestList.receiver"));
            cell.setCellStyle(csHeader);
        }
        if (userPA.get("paymentR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("carRequestEntity.payment"));
            cell.setCellStyle(csHeader);
        }
        if (userPA.get("descriptionR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("carRequestEntity.description"));
            cell.setCellStyle(csHeader);
        }
        if (userPA.get("creatorR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("carRequestEntity.creator"));
            cell.setCellStyle(csHeader);
        }
        if (userPA.get("priorityR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("carRequestEntity.priority"));
            cell.setCellStyle(csHeader);
        }
        if (userPA.get("startR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("carRequestEntity.start"));
            cell.setCellStyle(csHeader);
        }
        if (userPA.get("endActualR")) {
            cell = row.createCell(lastCell);
            cell.setCellValue(resourceBundle.getString("carRequestEntity.endActual"));
            cell.setCellStyle(csHeader);
        }
        if (userPA.get("declarationR")) {
            cell = row.createCell(lastCell);
            cell.setCellValue(resourceBundle.getString("carRequestEntity.declaration"));
            cell.setCellStyle(csHeader);
        }

    }

    protected void generateBody() {
        Row row;
        Cell cell;

        for (CarRequestDTO r : listRows) {
            int lastCell = 0;

            CellStyle style = wb.createCellStyle();
            style.setWrapText(true);

            row = sheet.createRow(lastRow++);
            row.setRowStyle(style);

            if (userPA.get("nameR")) {
                cell = row.createCell(lastCell++);
                cell.setCellValue(r.getName());
                cell.setCellStyle(csText);
            }
            if (userPA.get("startR")) {
                cell = row.createCell(lastCell++);
                cell.setCellStyle(csText);
                if (r.getTime() != null) {
                    cell.setCellValue(r.getTime());
                }
            }
            if (userPA.get("responsibleR")) {
                cell = row.createCell(lastCell++);
                cell.setCellStyle(csText);
                if (r.getResponsible() != null) {
                    cell.setCellValue(r.getResponsible());
                }
            }
            if (userPA.get("addressFromR")) {
                cell = row.createCell(lastCell++);
                cell.setCellStyle(csText);
                if (r.getAddressFrom() != null) {
                    cell.setCellValue(r.getAddressFrom());
                }
            }
            if (userPA.get("addressToR")) {
                cell = row.createCell(lastCell++);
                cell.setCellStyle(csText);
                if (r.getAddressTo() != null) {
                    cell.setCellValue(r.getAddressTo());
                }
            }
            if (userPA.get("receiverNameR")) {
                cell = row.createCell(lastCell++);
                cell.setCellStyle(csText);
                if (r.getReceiver() != null) {
                    cell.setCellValue(r.getReceiver());
                }
            }
            if (userPA.get("paymentR")) {
                cell = row.createCell(lastCell++);
                cell.setCellStyle(csText);
                if (r.getPayment() != null) {
                    cell.setCellValue(r.getPayment());
                }
            }
            if (userPA.get("descriptionR")) {
                cell = row.createCell(lastCell++);
                cell.setCellStyle(csText);
                if (r.getDescription() != null) {
                    cell.setCellValue(r.getDescription());
                }
                cell.setCellStyle(csText);

            }
            if (userPA.get("creatorR")) {
                cell = row.createCell(lastCell++);
                cell.setCellStyle(csText);
                if (r.getCreator() != null) {
                    cell.setCellValue(r.getCreator());
                }
            }
            if (userPA.get("priorityR")) {
                cell = row.createCell(lastCell++);
                cell.setCellStyle(csText);
                if (r.getPriority() != null) {
                    cell.setCellValue(r.getPriority());
                }
            }
            if (userPA.get("startR")) {
                cell = row.createCell(lastCell++);
                cell.setCellStyle(csDate);
                if (r.getStart() != null) {
                    cell.setCellValue(r.getStart());
                }
            }
            if (userPA.get("endActualR")) {
                cell = row.createCell(lastCell);
                cell.setCellStyle(csDate);
                if (r.getEndActual() != null) {
                    cell.setCellValue(r.getEndActual());
                }
            }
            if (userPA.get("declarationR")) {
                cell = row.createCell(lastCell);
                cell.setCellStyle(csDate);
                if (r.getDeclaration() != null) {
                    cell.setCellValue(r.getDeclaration());
                }
            }
        }
    }
}
