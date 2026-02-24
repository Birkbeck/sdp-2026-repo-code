package reflection.question1;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DescribeClass {

    public static void main(String[] args) {
        if (args.length > 0) {
            String className = args[0];
            try {
                System.out.println(new InterfaceConstructor(className));
            }
            catch (ClassNotFoundException e) {
                System.err.println("Unknown classname '" + className + "'");
            }
        }
    }

    /**
     * A class that takes the fully qualified Java class name as a String or a Class object
     * and provides public methods for accessing metadata about the class.
     * The toString method of an instance of this class will return an interface
     * description of the class declaration, and the fields, constructors and methods
     * of the class.
     */
    private static class InterfaceConstructor {

        private final static String INDENT = "\t";
        private final Class<?> c;

        public InterfaceConstructor(String className) throws ClassNotFoundException {
            this.c = Class.forName(className);
        }

        /**
         * Create a string of Strings that have had any null values removed and that
         * are separated by a user defined delimiter.
         *
         * @param delimiter string of the delimiter
         * @param strings   vararg of Strings to be joined possibly including null values
         * @return string of delimiter separated String
         */
        private static String joinRemovingNullEntries(String delimiter, String... strings) {
            return Arrays.stream(strings)
                .filter(Objects::nonNull)
                .collect(Collectors.joining(delimiter));
        }

        /**
         * Removes all package names for a fully qualified class name, including package names
         * from any parameterized types present in the name.
         *
         * @param className string of fully qualified class name
         * @return simplified class name.
         */
        private static String simplifyClass(String className) {
            Pattern p = Pattern.compile("([$_A-Za-z0-9]+?\\.)+([$_A-Za-z0-9]+.*)");
            for (Matcher m = p.matcher(className); m.find(); m = p.matcher(className)) {
                className = m.replaceAll("$2");
            }
            return className;
        }

        /**
         * Create a string of simplified parameter types that are separated by a
         * user defined delimiter.
         *
         * @param params    array list of reflection parameters
         * @param delimiter string of the delimiter
         * @return string of delimiter separated parameter types
         */
        private static String getDelimiterSeparatedParameterTypes(Parameter[] params, String delimiter) {
            return Stream.of(params)
                .map(Parameter -> simplifyTypeClass(Parameter.getParameterizedType()))
                .collect(Collectors.joining(delimiter));
        }

        private static String simplifyTypeClass(Type t) {
            return simplifyClass(t.getTypeName());
        }

        private String getClassType() {
            if (c.isInterface()) {
                return null;
            }
            return "class";
        }

        private String getExtension() {
            if (c.getSuperclass() == null) return null;

            return "extends " + c.getSuperclass().getSimpleName();
        }

        private String getSimpleModifiers(int modifiers) {
            if (modifiers == 0) return null;

            return Modifier.toString(modifiers);
        }

        private String getInterfaces() {
            if (c.getInterfaces().length == 0) return null;

            return "implements " + Stream.of(c.getInterfaces())
                    .map(Class::getSimpleName)
                    .collect(Collectors.joining(", "));
        }

        private String getClassDeclaration() {
            return joinRemovingNullEntries(" ",
                    getSimpleModifiers(c.getModifiers()),
                    getClassType(),
                    c.getSimpleName(),
                    getExtension(),
                    getInterfaces());
        }

        private String getFieldInformation() {
            if (c.getDeclaredFields().length == 0) return null;

            List<String> output = new ArrayList<>(Arrays.asList(INDENT + "// Field information", ""));
            for (Field fld : c.getDeclaredFields()) {
                output.add(1, joinRemovingNullEntries(" ",
                        INDENT,
                        getSimpleModifiers(fld.getModifiers()),
                        simplifyTypeClass(fld.getGenericType()),
                        fld.getName() + ";"));
            }
            return String.join("\n", output);
        }

        private String getConstructorsInformation() {
            if (c.getConstructors().length == 0) return null;

            List<String> output = new ArrayList<>(Arrays.asList(INDENT + "// Constructor information", ""));
            for (Constructor<?> constr : c.getConstructors()) {
                output.add(1, joinRemovingNullEntries(" ",
                        INDENT,
                        getSimpleModifiers(constr.getModifiers()),
                        simplifyClass(constr.getName())
                                + "(" + getDelimiterSeparatedParameterTypes(constr.getParameters(), ", ") + ");"));
            }
            return String.join("\n", output);
        }

        private String getMethodsInformation() {
            if (c.getDeclaredMethods().length == 0) return null;

            List<String> output = new ArrayList<>(Arrays.asList(INDENT + "// Declared method information", ""));
            for (Method method : c.getDeclaredMethods()) {
                output.add(1, joinRemovingNullEntries(" ",
                        INDENT,
                        getSimpleModifiers(method.getModifiers()),
                        simplifyTypeClass(method.getGenericReturnType()),
                        method.getName()
                                + "(" + getDelimiterSeparatedParameterTypes(method.getParameters(), ", ") + ");"));
            }
            return String.join("\n", output);
        }

        @Override
        public String toString() {
            if (c == null) return "";
            return joinRemovingNullEntries("\n",
                    "/**",
                    " * Interface created for " + c.getCanonicalName(),
                    " */",
                    getClassDeclaration() + " {",
                    getFieldInformation(),
                    getConstructorsInformation(),
                    getMethodsInformation(),
                    "}");
        }
    } // InterfaceConstructor
}