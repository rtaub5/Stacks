import java.io.FileReader;
import java.util.Stack;

/**
 * @Rocki Taub
 * Class created 11/06/23
 */
public class ParenthesesChecker
{

    public static <File> void main(String[] args)
    {
        if (args.length < 1)
        {
            System.out.println("no filename provided");
        } else
        {
            try (FileReader fr = new FileReader(args[0]))
            {
                boolean popped = false;
                int ch = 0;
                Stack<Character> parenStack = new Stack<>();
                int quote = -1;
                while ((ch = fr.read()) != -1)
                {
                    char symbol = (char) ch;
                    quote = checkQuotes(symbol, quote);
                    if (quote == -1)
                    {
                        if (((symbol == '(') || (symbol == '{') || (symbol == '[')))
                        {
                            parenStack.push(symbol);

                        } else if (((symbol == ')') || (symbol == '}') || (symbol == ']')))
                        {
                            popped = tryPopStack(symbol, parenStack);
                        }
                    }
                }
                if (popped)
                {
                    checkForm(parenStack);
                }
                else
                {
                    System.out.println("Not well-formed");
                }

            } catch (Exception io)
            {
                System.out.println(io.getMessage());
            }
        }
    }

    /**
     *
     * @param ch character trying to check if partner on stack
     * @param parenStack stack holding all parentheses symbols
     * @return popped, boolean which checks if stack was empty when trying to pop top value.
     */
    public static boolean tryPopStack(char ch, Stack<Character> parenStack)
    {
        boolean popped = true;
        if (!parenStack.empty())
        {
            char topChar = parenStack.pop();

            if (ch == ')')
            {
                if (!(topChar == '('))
                {
                    parenStack.push(topChar);
                }
            } else if (ch == '}')
            {
                if (!(topChar == '{'))
                {
                    parenStack.push(topChar);
                }
            } else if (ch == ']')
            {
                if (!(topChar == '['))
                {
                    parenStack.push(topChar);
                }
            }
        } else
        {
            popped = false;
        }
        return popped;
    }

    /**
     *
     * @param quote character trying to check if its quotes
     * @return quote state of stack if inside quotes or not
     */
    public static int checkQuotes(char quote, int quoteState)
    {
        if (quote == '"')
        {
            if (quoteState == 1)
                quoteState = -1;
            else
                quoteState = 1;
        }
        else
        {
            return quoteState;
        }
        return quoteState;
    }

    /**
     *
     * @param parenStack stack holding all parentheses symbols
     */
    public static void checkForm(Stack<Character> parenStack)
    {
        if (parenStack.isEmpty())
        {
            System.out.println("Well-formed");
        } else
        {
            System.out.println("Not well-formed");
        }

    }
}





