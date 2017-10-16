package model;

import com.googlecode.jhocr.element.HocrLine;
import com.googlecode.jhocr.element.HocrPage;

/**
 * Created by joerg on 14.10.2017.
 */
public abstract class LineRule {
    public abstract String apply(HocrLine line);
}
