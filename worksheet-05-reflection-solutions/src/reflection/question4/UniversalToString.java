package reflection.question4;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A universal toString method that gives a string
 * representation of any object showing
 * the object's class name and all declared field names and
 * values are printed.
 */
public class UniversalToString {

    /**
     * Returns all declared fields (public and non-public) in the parameter obj.
     * The class name and field name/value pairs are returned as a String
     * using the following pattern:
     * classname{fieldname1=value1, fieldname2=value2...}
     * <p>
     * Field values that are reference classes that do not have a toString method
     * will be included in the returned string using the same basic pattern:
     * classnameA{fieldname1=refclassB{fieldnameB1=valueB1, ...}, ...}
     *
     * @param obj instance object of any class type
     * @return string describing the class name, field names and values.
     */
    public static String toString(Object obj) {
        if (obj == null) return "null";
        Class<?> c = obj.getClass();

        Map<String, String> fieldValues = new LinkedHashMap<>();
        for (Field fld : c.getDeclaredFields())
            fieldValues.put(fld.getName(), fieldToString(fld, obj));

        return c.getSimpleName() +
                fieldValues.entrySet().stream()
                        .map(e -> e.getKey() + "=" + e.getValue())
                        .collect(Collectors.joining(", ", "{", "}"));
    }

    private static String fieldToString(Field fld, Object obj) {
        try {
            // expose non-public field values
            if (!Modifier.isPublic(fld.getModifiers()) && !fld.trySetAccessible()) {
                return "Inaccessible";
            }
            Class<?> fieldTypeClass = fld.getType();
            Object fieldValue = fld.get(obj);

            if (fieldTypeClass.isPrimitive()) {
                if (fieldTypeClass == char.class)
                    return "'" + fieldValue + "'";

                else
                    return fieldValue.toString();
            }
            else {
                if (fieldTypeClass == String.class && fieldValue != null)
                    return "\"" + fieldValue + "\"";

                else
                    return toString(fieldValue); // recursive call
            }
        }
        catch (IllegalAccessException e) {
            return "IllegalAccess";
        }
    }
}