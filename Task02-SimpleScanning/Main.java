import java.io.File;
import java.util.List;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        System.out.println("---------------------------------------");
        String path = System.getProperty("user.dir");
        File file = new File(path + "/Calc1.stk");
        
        List<Token> tokens = ScanningFeature.scan(file);

        if (tokens.size() > 0) {
            Stack<Integer> stack = new Stack<Integer>();
            Integer output = compute(stack, tokens);
            System.out.println("\nSaída: " + output);
        }
    }

    private static Integer compute(Stack<Integer> stack, List<Token> tokens) {
        for (Token t : tokens) {
            System.out.println(t.toString());
            if (t.type != TokenType.NUM) {
                resolveOperation(stack, t.type);
                continue;
            } 
            saveNumber(stack, t.lexeme);
        }
        return stack.pop();
    }

    private static void resolveOperation(Stack<Integer> stack, TokenType tokenType) {
        if (stack.size() >= 2) {
            Integer a = stack.pop();
            Integer b = stack.pop();
    
            Integer result = 0;
            if (tokenType.equals(TokenType.PLUS)) result = b+a;
            if (tokenType.equals(TokenType.MINUS)) result = b-a;
            if (tokenType.equals(TokenType.STAR)) result = b*a;
            if (tokenType.equals(TokenType.SLASH)) result = a == 0 ? b : b/a;
    
            stack.push(result);
        }
    }

    private static void saveNumber(Stack<Integer> stack, String lexeme) {
        stack.push(Integer.parseInt(lexeme));
    }
}