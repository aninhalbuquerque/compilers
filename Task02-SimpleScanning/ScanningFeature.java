import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 Roteiro:
- evoluir o projeto da Task 01 para implementar uma feature de scanning:
   -- No geral, nosso Programa le um arquivo com a expressao em RPN e devolve a expressao avalliada
   -- a feature de scanning deve retornar uma lista de tokens
   -- a partir dessa lista de tokens que e realizada a interpretacao das expressoes com uma pilha
   -- a feature de scanning deve retornar um erro caso nao reconheca um "num" [numero] ou "op" [operator]

Exemplo de entrada [com sucesso]:
10
10
+
// a lista de tokens reconhecida [caso a imprima]
Token [type=NUM, lexeme=10]
Token [type=NUM, lexeme=10]
Token [type=PLUS, lexeme=+]

Saida: 20

Exemplo de entrada [com falha]:
10
s
+
Error: Unexpected character: s

*Obs: para implementacao da lista de tokens, a classe Token e TokenType em anexo devera servir de 
suporte para o [manual] scanning; sendo assim, ou inves do codigo retornado na linha 94 [do projeto
base que passei]: List<String> tokens = scan(source); nos teriamos o seguinte: 
List<Token> tokens = scan(source);
**Obs: obviamente minimas alteracoes podem ser feitas 
[ex: adaptar o pacote das classes em anexo para seu projeto]
***Obs: ainda nao usar expressões regulares para versão 2; foco eh a identificacao dos token types
 */


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
        TokenType operation = getOperation(input);
        if (operation != null) {
            return new Token(operation, input);
        }

        TokenType number = getNumber(input);
        if (number != null) {
            return new Token(number, input);
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

    private static TokenType getNumber(String input) {
        try{
            int number = Integer.parseInt(input);
            return TokenType.NUM;
        }
        catch (NumberFormatException ex){
            return null;
        }
    }
}