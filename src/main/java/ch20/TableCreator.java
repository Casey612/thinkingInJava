package ch20;

import org.apache.commons.lang.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: yuki
 * @date: 2018/10/27
 */
public class TableCreator {

    public static void main(String[] args) {
        Class<?> cl = Member.class;
        DBTabel dbTabel = cl.getAnnotation(DBTabel.class);
        if (dbTabel == null) {
            System.out.println("cannot find annotation");
        }
        String tableName = dbTabel.name();
        if (StringUtils.isEmpty(tableName)) {
            tableName = cl.getName();
        }
        List<String> colnmnDefs = new ArrayList<>();
        for (Field field : cl.getDeclaredFields()) {
            String columnName = null;
            Annotation[] annotations = field.getDeclaredAnnotations();
            if (annotations.length < 1) {
                continue;
            }
            if (annotations[0] instanceof SQLInteger) {
                SQLInteger sInt = (SQLInteger) annotations[0];
                if (StringUtils.isEmpty(sInt.name())) {
                    columnName = field.getName();
                } else {
                    columnName = sInt.name();
                }
                colnmnDefs.add(columnName + "\tINT " + getConstraints(sInt.constraints()));
            }

            if (annotations[0] instanceof SQLString) {
                SQLString sStr = (SQLString) annotations[0];
                if (StringUtils.isEmpty(sStr.name())) {
                    columnName = field.getName();
                } else {
                    columnName = sStr.name();
                }
                colnmnDefs.add(columnName + "\tVARCHAR(" + sStr.value() + ")"
                        + getConstraints(sStr.constraints()));
            }
        }

        StringBuilder sql = new StringBuilder("CREATE TABLE ").append(tableName).append("(");
        for (String s : colnmnDefs) {
            sql.append("\n\t").append(s).append(",");
        }
        sql.deleteCharAt(sql.length() - 1).append("\n);");
        System.out.println("create sql:");
        System.out.println(sql.toString());
    }

    private static String getConstraints(Constraints con) {
        StringBuilder sb = new StringBuilder();
        if (!con.allowNull()) {
            sb.append(" NOT NULL");
        }
        if (con.primaryKey()) {
            sb.append(" PRIMARY KEY");
        }
        if (con.unique()) {
            sb.append(" unique");
        }
        return sb.toString();
    }


}
