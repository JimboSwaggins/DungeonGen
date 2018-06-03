package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringAnalyzer {
	
	public static boolean isNull(String toCheck) {
		if(toCheck.equals("") || toCheck == null) {
			return true;
		}return false;
	}
	
	public static boolean containsCharacters(String toCheck) {
		Pattern p = Pattern.compile("[a-zA-Z].");
		Matcher m = p.matcher(toCheck);
		if(m.find()) {
			return true;
		}return false;
	}
	
	public static boolean containsNumbers(String toCheck) {
		Pattern p = Pattern.compile("[1-9].");
		Matcher m = p.matcher(toCheck);
		if(m.find()) {
			return true;
		}return false;
	}
}
