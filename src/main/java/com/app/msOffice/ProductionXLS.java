package com.app.msOffice;

import com.app.dto.ProductionReportDTO;
import com.app.entity.OrderListFilter;
import com.app.entity.Person;
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
 * Created by Andrey on 02.07.2016.
 */
public class ProductionXLS {

    private ResourceBundle resourceBundle;

    private List<ProductionReportDTO> listRows;
    private Map<String, Boolean> userPA;
    private OrderListFilter filter;
    private Workbook wb;
    private Sheet sheet;
    private CellStyle cellStyle;
    private int lastRow = 0;

    private static final String FILE_NAME = "производство";

    public ProductionXLS(List<ProductionReportDTO> listRows, Map<String, Boolean> userPA, OrderListFilter filter) {
        this.listRows = listRows;
        this.userPA = userPA;
        this.filter = filter;
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

    private void generateBody() {
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
                    cell.setCellStyle(cellStyle);
                }
            }
            if (userPA.get("docDateR")) {
                cell = row.createCell(lastCell++);
                if (r.getDocDate() != null) {
                    cell.setCellValue(r.getDocDate());
                    cell.setCellStyle(cellStyle);
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
                    cell.setCellStyle(cellStyle);
                }
            }
            if (userPA.get("endActualR")) {
                cell = row.createCell(lastCell);
                if (r.getEndActual() != null) {
                    cell.setCellValue(r.getEndActual());
                    cell.setCellStyle(cellStyle);
                }
            }

        }
    }


}


