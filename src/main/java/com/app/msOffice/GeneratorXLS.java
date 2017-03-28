package com.app.msOffice;

import com.app.data.entity.Person;
import com.app.security.Security;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class GeneratorXLS {
    protected ResourceBundle resourceBundle;

    protected Map<String, Boolean> userPA;
    protected Workbook wb;
    protected Sheet sheet;
    protected CellStyle csDate;
    protected CellStyle csText;
    protected CellStyle csHeader;
    protected int lastRow = 0;

    protected abstract void generateHeader();
    protected abstract void generateBody();

    public GeneratorXLS(Map<String, Boolean> userPA) {
        this.userPA = userPA;
        init();
    }

    private void init() {
        resourceBundle = ResourceBundle.getBundle("com.app.StringBundle.StringsBundle", new Locale("ru", "RU"));

        wb = new HSSFWorkbook();
        sheet = wb.createSheet("export");
        initStyles();
    }

    private void initStyles(){
        CreationHelper createHelper = wb.getCreationHelper();
        csDate = wb.createCellStyle();
        csDate.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
        addBorders(csDate);

        csText = wb.createCellStyle();
        csText.setWrapText(true);
        addBorders(csText);

        csHeader = wb.createCellStyle();
        csHeader.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
        addBorders(csHeader);
    }

    private void addBorders(CellStyle cellStyle){
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    }

    public void renderExcel() {
        generateTitle();
        generateHeader();
        generateBody();

        generateResponse();

    }

    protected void generateResponse() {
        String fileName = getFileName();
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.responseReset();
        ec.setResponseContentType("text/xsl");

        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

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

        return getReportName() + "_" + reportDate + ".xls";
    }

    protected String getReportName(){
        return "excel";
    }

    protected void generateTitle() {
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

        CreationHelper createHelper = wb.getCreationHelper();
        CellStyle style = wb.createCellStyle();
        style.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));

        cell = row.createCell(1);
        cell.setCellValue(new Date());
        cell.setCellStyle(style);

        sheet.createRow(lastRow++);
    }

}
