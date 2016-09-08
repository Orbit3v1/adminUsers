package com.app.msOffice;

import com.app.data.dto.SpecificationDTO;
import com.app.data.entity.Person;
import com.app.utils.Security;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ayaroslavtsev on 30.08.2016.
 */
public class SpecificationXLS {
    private ResourceBundle resourceBundle;

    private List<SpecificationDTO> listRows;
    private Map<String, Boolean> userPA;
    private Workbook wb;
    private Sheet sheet;
    private CellStyle cellStyle;
    private int lastRow = 0;

    private static final String FILE_NAME = "реестрт_ТЗ";

    public SpecificationXLS(List<SpecificationDTO> listRows, Map<String, Boolean> userPA) {
        this.listRows = listRows;
        this.userPA = userPA;
        init();
    }

    private void init() {
        resourceBundle = ResourceBundle.getBundle("com.app.utils.StringsBundle", new Locale("ru", "RU"));

        wb = new HSSFWorkbook();
        sheet = wb.createSheet("export");
        CreationHelper createHelper = wb.getCreationHelper();
        cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
    }

    public void renderExcel() {
        generateTitle();
        generateFilter();
        generateHeader();
        generateBody();

        generateResponse();

    }

    private void generateResponse() {
        String file = getFileName();
        try {
            file = URLEncoder.encode(file, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.responseReset();
        ec.setResponseContentType("text/xsl");

        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + file + "\"");

        try {
            OutputStream output = ec.getResponseOutputStream();
            wb.write(output);
        } catch (Exception e) {
            e.printStackTrace();
        }

        fc.responseComplete();
    }

    private String getFileName() {
        Date today = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String reportDate = df.format(today);

        return FILE_NAME + "_" + reportDate + ".xls";
    }

    private void generateTitle() {
        Row row;
        Cell cell;

        row = sheet.createRow(lastRow++);
        cell = row.createCell(0);
        cell.setCellValue("Создал");

        Person creator = Security.getCurrentUser();
        cell = row.createCell(1);
        cell.setCellValue(creator.getLastName() + " " + creator.getFirstName());

        row = sheet.createRow(lastRow++);
        cell = row.createCell(0);
        cell.setCellValue("Дата");

        cell = row.createCell(1);
        cell.setCellValue(new Date());
        cell.setCellStyle(cellStyle);

        row = sheet.createRow(lastRow++);
    }

    private void generateFilter() {

    }

    private void generateHeader() {
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
            cell = row.createCell(lastCell++);
            cell.setCellValue(resourceBundle.getString("specificationEntity.responsible"));
        }
    }

    private void generateBody() {
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
                    cell.setCellStyle(cellStyle);
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
                    cell.setCellStyle(cellStyle);
                }
            }
            if (userPA.get("developerR")) {
                cell = row.createCell(lastCell++);
                if (r.getDeveloper() != null) {
                    cell.setCellValue(r.getDeveloper());
                }
            }
            if (userPA.get("responsibleR")) {
                cell = row.createCell(lastCell++);
                if (r.getResponsible() != null) {
                    cell.setCellValue(r.getResponsible());
                }
            }
        }
    }
}

