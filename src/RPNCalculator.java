import java.util.*;
/**
 * @author Rocki Taub
 * Created 09/27/23
 */
public class RPNCalculator {
    public static void main(String [] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter an RPN expression or <CR> to exit");
        String expression = input.nextLine();

        while (!expression.equals("")){
            String answer = evaluate(expression);
            System.out.println(answer);
            System.out.println("Enter an RPN expression or <CR> to exit");
            expression = input.nextLine();
        }
        System.out.println("good bye");
    }

    /**
     * @param expression String of numbers and operation symbols
     * @return answer to RPN in a string or error message
     */
    public static String evaluate(String expression)
    {
        String [] expressionArray = expression.split("\\s+");
        Stack<Double>rpnStack = new Stack<>();
        String result;
        try {
            for (String expr : expressionArray) {
                boolean isNum = isNumericPush(expr, rpnStack);
                if (!isNum)
                {
                    Double numTwo = rpnStack.pop();
                    Double numOne = rpnStack.pop();
                    if (expr.equals("+")) {
                        rpnStack.push(numOne + numTwo);
                    } else if (expr.equals("*")) {
                        rpnStack.push(numOne * numTwo);
                    } else if (expr.equals("-")) {
                        rpnStack.push(numOne - numTwo);
                    } else if (expr.equals("/")) {
                        rpnStack.push(numOne / numTwo);
                    } else if (expr.equals("^")) {
                        rpnStack.push(Math.exp(numTwo * Math.log(numOne)));
                    }

                }
            }

            result = Double.toString(rpnStack.pop());
            if (!rpnStack.isEmpty()) {
                result = result + "\nextra junk ignored";
            }
        }
        catch (Exception exc)
        {
            result = "Syntax error";
        }
        return result;
    }

    /**
     * @param element number or operation symbol
     * @param rpnStack stack with doubles
     * @return boolean true if double, otherwise false
     */
    private static boolean isNumericPush(String element, Stack<Double>rpnStack)
    {
        try
        {
            rpnStack.push(Double.parseDouble(element));
            return true;
        }
        catch (NumberFormatException exc1)
        {
            return false;
        }
    }
}

