package model;

import com.googlecode.jhocr.element.HocrLine;
import com.googlecode.jhocr.element.HocrWord;
import lombok.extern.java.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by joerg on 14.10.2017.
 */
@Log
public class TotalValueRule extends LineRule {

    Pattern pattern = Pattern.compile("total.*",Pattern.CASE_INSENSITIVE);
    Matcher matcher;

    public String apply(HocrLine line) {
        for (HocrWord hocrWord:line.getWords()){
            matcher = pattern.matcher(hocrWord.getText());
            if (matcher.find()){
                log.fine("Keyword für Rechnungsbetrag gefunden:"+hocrWord);
                for (HocrWord hocrWord2:line.getWords()) {
                    if (hocrWord2.getText().contains("$") || hocrWord2.getText().contains("€"))
                        System.out.println("Wert für Rechnungsbetrag gefunden: " + hocrWord2.getText());
                        return hocrWord2.getText();
                }
            }
        }
        return null;
    }
}
