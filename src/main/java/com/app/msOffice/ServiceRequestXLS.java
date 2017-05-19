package com.app.msOffice;

import com.app.data.dto.CarRequestDTO;
import com.app.data.dto.ServiceRequestDTO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.util.List;
import java.util.Map;

/**
 * Created by ayaroslavtsev on 19.05.2017.
 */
public class ServiceRequestXLS  extends GeneratorXLS{
    private List<ServiceRequestDTO> listRows;

    public ServiceRequestXLS(List<ServiceRequestDTO> listRows, Map<String, Boolean> userPA) {
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
        sheet.setColumnWidth(9, 3500);
        sheet.setColumnWidth(10, 3500);
        sheet.setColumnWidth(11, 4000);
    }

    @Override
    protected String getReportName() {
        return "заявка_на_сервис";
    }

    protected void generateHeader() {
        Row row;
        Cell cell;
        int lastCell = 0;
        row = sheet.createRow(lastRow++);

        if (userPA.get("nameR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("serviceRequestEntity.name"));
            cell.setCellStyle(csHeader);
        }
        if (userPA.get("startR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("serviceRequestList.time"));
            cell.setCellStyle(csHeader);
        }
        if (userPA.get("responsibleR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("serviceRequestEntity.responsible"));
            cell.setCellStyle(csHeader);
        }
        if (userPA.get("warrantyNumberR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("serviceRequestEntity.warrantyNumber"));
            cell.setCellStyle(csHeader);
        }
        if (userPA.get("counterpartyR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("serviceRequestEntity.counterparty"));
            cell.setCellStyle(csHeader);
        }
        if (userPA.get("contactNameR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("serviceRequestList.contact"));
            cell.setCellStyle(csHeader);
        }
        if (userPA.get("paymentR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("serviceRequestEntity.payment"));
            cell.setCellStyle(csHeader);
        }
        if (userPA.get("descriptionR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("serviceRequestEntity.description"));
            cell.setCellStyle(csHeader);
        }
        if (userPA.get("creatorR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("serviceRequestEntity.creator"));
            cell.setCellStyle(csHeader);
        }
        if (userPA.get("startR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("serviceRequestEntity.start"));
            cell.setCellStyle(csHeader);
        }
        if (userPA.get("endActualR")) {
            cell = row.createCell(lastCell);
            cell.setCellValue(resourceBundle.getString("serviceRequestEntity.endActual"));
            cell.setCellStyle(csHeader);
        }
        if (userPA.get("resultR")) {
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("serviceRequestEntity.result"));
            cell.setCellStyle(csHeader);
        }

    }

    protected void generateBody() {
        Row row;
        Cell cell;

        for (ServiceRequestDTO r : listRows) {
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
            if (userPA.get("warrantyNumberR")) {
                cell = row.createCell(lastCell++);
                cell.setCellStyle(csText);
                if (r.getWarrantyNumber() != null) {
                    cell.setCellValue(r.getWarrantyNumber());
                }
            }
            if (userPA.get("counterpartyR")) {
                cell = row.createCell(lastCell++);
                cell.setCellStyle(csText);
                if (r.getCounterparty() != null) {
                    cell.setCellValue(r.getCounterparty());
                }
            }
            if (userPA.get("contactNameR")) {
                cell = row.createCell(lastCell++);
                cell.setCellStyle(csText);
                if (r.getContact() != null) {
                    cell.setCellValue(r.getContact());
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
            if (userPA.get("resultR")) {
                cell = row.createCell(lastCell++);
                cell.setCellStyle(csText);
                if (r.getResult() != null) {
                    cell.setCellValue(r.getResult());
                }
            }
        }
    }
}
