import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScanningFeature {

    public static List<Token> scan(File file) {
        try {
            Scanner scanner = new Scanner(file);
            boolean error = false;
            List<Token> tokens = new ArrayList<>();
            while(scanner.hasNextLine()) {
                String input = scanner.nextLine();
                //System.out.println(input);

                Token token = getToken(input);
                if (token.type != null) {
                    //System.out.println(token.toString());
                    tokens.add(token);
                } else {
                    System.out.println("Error: Unexpected character:" + input);
                    error = true;
                    break;
                }
            }
            scanner.close();

            if (error) return List.of();
            return tokens;

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred trying to open file.");
            e.printStackTrace();
            return List.of();
        }
    }

    private static Token getToken(String input) {
        if (Regex.isOP(input)) {
            TokenType operation = getOperation(input);
            return new Token(operation, input);
        }
        
        if (Regex.isNum(input)) {
            return new Token(TokenType.NUM, input);
        }

        return new Token(null, input);
    }

    private static TokenType getOperation(String input) {
        if (input.equals("+")) return TokenType.PLUS;
        if (input.equals("-")) return TokenType.MINUS;
        if (input.equals("*")) return TokenType.STAR;
        if (input.equals("/")) return TokenType.SLASH;

        return null;
    }
}