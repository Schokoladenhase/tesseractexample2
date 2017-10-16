package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.java.Log;

/**
 * Created by joerg on 14.10.2017.
 */
@Data @AllArgsConstructor
public class BusinessValue {
    String businessKey;
    BusinessValueType type;
    String value;
}
