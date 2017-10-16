package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.jhocr.element.HocrDocument;
import com.googlecode.jhocr.element.HocrLine;
import com.googlecode.jhocr.element.HocrPage;
import com.googlecode.jhocr.element.HocrWord;
import com.googlecode.jhocr.parser.HocrParser;
import model.BusinessValue;
import model.BusinessValueType;
import model.LineRule;
import model.TotalValueRule;

public class TesseractExample {

    public static void main(String[] args) {
        File imageFile = new File("C:\\Temp\\ocrtest\\dns-services-scam-letter.png");
        TesseractWrapper tw = new TesseractWrapper();
        HocrParser parser = null;
        List<BusinessValue> businessValues=new ArrayList<BusinessValue>();
        try {
            parser=new HocrParser(new FileInputStream(tw.doOcr(imageFile).getName().concat(".hocr")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        HocrDocument hocdoc=parser.parse();
        LineRule totalValue=new TotalValueRule();
        for (HocrPage page:hocdoc.getPages()){
            for (HocrLine line:page.getLines()){
                if (totalValue.apply(line)!=null) businessValues.add(new BusinessValue("Gesamtbetrag", BusinessValueType.EURO_CURRENCY,totalValue.apply(line)));
            }
        }
        businessValues.stream().forEach(System.out::println);
    }
}