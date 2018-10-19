package ch19;

import java.text.DateFormat;
import java.util.Date;

/**
 * @author yuzhe
 * @since 10/19/18
 */
public enum ConstantSpecificMethod {

    DATE_TIME {
        @Override
        String getInfo() {
            return DateFormat.getDateInstance().format(new Date());
        }
    },
    CLASSPATH{
        @Override
        String getInfo() {
            return System.getenv("");
        }
    },
    VERSION{
        @Override
        String getInfo() {
            return System.getenv("java.version");
        }
    };

    abstract String getInfo();

    public static void main(String[] args) {
        for (ConstantSpecificMethod csm : values()) {
            System.out.println(csm.getInfo());
        }
    }

}
