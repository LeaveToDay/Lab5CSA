import java.util.*;

public class AssemblyInterpreter {
    private static Map<String, String> variables = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter assembly statement: ");
            String statement = scanner.nextLine();

            if (statement.isEmpty()) {
                break;
            }

            String[] tokens = statement.split("\\s+");

            if (tokens[1].equals("DB")) {
                String variable = tokens[0];
                String value = tokens[2];

                variables.put(variable, parseValue(value));

                System.out.println(variables.get(variable));
            } else if (tokens[0].equals("INC")) {
                String variable = tokens[1];
                String value = variables.get(variable);

                int intValue = Integer.parseInt(value, 16) + 1;

                variables.put(variable, Integer.toHexString(intValue));

                System.out.println(intValue);
            } else if (tokens[0].equals("ADD")) {
                String variable1 = tokens[1];
                String variable2 = tokens[2];
                String value1 = variables.get(variable1);
                String value2 = variables.get(variable2);

                int intValue1 = Integer.parseInt(value1, 16);
                int intValue2 = Integer.parseInt(value2, 16);
                int result = intValue1 + intValue2;

                variables.put(variable1, Integer.toHexString(result));

                System.out.println(result);
            } else if (tokens[0].equals("SUB")) {
                String variable1 = tokens[1];
                String variable2 = tokens[2];
                String value1 = variables.get(variable1);
                String value2 = variables.get(variable2);

                int intValue1 = Integer.parseInt(value1, 16);
                int intValue2 = Integer.parseInt(value2, 16);
                int result = intValue1 - intValue2;

                variables.put(variable1, Integer.toHexString(result));

                System.out.println(Integer.toHexString(result));
            } else if (tokens[0].equals("MOV")) {
                String variable1 = tokens[1];
                String variable2 = tokens[2];
                String value2 = variables.get(variable2);

                variables.put(variable1, value2);

                System.out.println(value2);
            } else {
                System.out.println("Unknown statement");
            }
        }
    }

    private static String parseValue(String value) {
        if (value.startsWith("0b")) {
            return Integer.toHexString(Integer.parseInt(value.substring(2), 2));
        } else if (value.startsWith("0x")) {
            return value.substring(2);
        } else if (value.startsWith("'") && value.endsWith("'")) {
            String str = value.substring(1, value.length() - 1);

            if (str.length() == 1) {
                return Integer.toHexString((int) str.charAt(0));
            } else {
                StringBuilder hex = new StringBuilder();

                for (char c : str.toCharArray()) {
                    hex.append(Integer.toHexString((int) c));
                }

                return hex.toString();
            }
        } else {
            return Integer.toHexString(Integer.parseInt(value));
        }
    }
}
