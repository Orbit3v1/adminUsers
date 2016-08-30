package com.app.msOffice;

import com.app.entity.Specification;
import com.app.utils.AppUtil;
import org.apache.log4j.Logger;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.*;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hwpf.HWPFDocument;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * Created by ayaroslavtsev on 30.08.2016.
 */
public class SpecificationDoc {

    protected Logger logger = Logger.getLogger(getClass());
    HWPFDocument doc;
    Specification specification;

    public SpecificationDoc(Specification specification) {
        this.specification = specification;
    }

    public void render() throws IOException{
        init();
        generateData();
        generateResponse();
    }

    private void init() throws IOException{
        String filePath = "MSTemplate/Specification.doc";
        POIFSFileSystem fs = null;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream(filePath);
        fs = new POIFSFileSystem(input);
        doc = new HWPFDocument(fs);
    }

    private void generateData(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        replaceText("$:Name", specification.getName() + "-" + specification.getSubName());
        replaceText("$:nomenclature", specification.getNomenclature() == null ? "" : specification.getNomenclature().getName());
        replaceText("$:responsible", specification.getResponsible() == null ? "" : specification.getResponsible().toString());
        replaceText("$:start", specification.getStart() == null ? "" : df.format(specification.getStart()));
        replaceText("$:material", specification.getNomenclature() == null ? "" : specification.getNomenclature().getMaterial());
        replaceText("$:mainSize", specification.getMainSize() == null ? "" : specification.getMainSize());
        replaceText("$:detailSize", specification.getDetailSize() == null ? "" : specification.getDetailSize());
        replaceText("$:pressure", specification.getPressure() == null ? "" : specification.getPressure());
        replaceText("$:additional", specification.getAdditional() == null ? "" : specification.getAdditional());
        replaceText("$:price", specification.getPrice() == null ? "" : specification.getPrice());
        replaceText("$:discount", specification.getDiscount() == null ? "" : specification.getDiscount());
        replaceText("$:workDays", specification.getWorkDays() == null ? "" : String.valueOf(specification.getWorkDays()));
    }



    private void replaceText(String findText, String replaceText){
        Range r1 = doc.getRange();

        for (int i = 0; i < r1.numSections(); ++i ) {
            Section s = r1.getSection(i);
            for (int x = 0; x < s.numParagraphs(); x++) {
                Paragraph p = s.getParagraph(x);
                for (int z = 0; z < p.numCharacterRuns(); z++) {
                    CharacterRun run = p.getCharacterRun(z);
                    String text = run.text();
                    if(text.contains(findText)) {
                        run.replaceText(findText, replaceText);
                    }
                }
            }
        }
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
            doc.write(output);
        } catch (Exception e) {
            e.printStackTrace();
        }

        fc.responseComplete();
    }

    private String getFileName() {
        return "ТЗ" + "_" + specification.getName() + "-" + specification.getSubName() + ".doc";
    }

}
