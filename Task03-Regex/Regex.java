public class Regex {
    private static String patternForInt = "^[-]?[1-9][0-9]*|0$";
    private static String patternForOperation = "[*/+\\-]";
	
	
	public static boolean isNum(String token) {
        return token.matches(patternForInt);
    }
	
	public static boolean isOP(String token) {
        return token.matches(patternForOperation);
    }

}