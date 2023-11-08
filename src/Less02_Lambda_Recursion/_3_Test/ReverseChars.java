package Less02_Lambda_Recursion._3_Test;

public class ReverseChars {
	static String reverseChars(String str) {
		if(!str.isEmpty()) {
			int len = str.length();
			String res = "";
			res = str.substring(0, len - (len - 1));
			if(len == 1)
				return res;
			String result = reverseChars(str.substring(len - (len - 1))).concat(res);
			return result;
		}
		return "";
	}
	
	public static void main(String[] args) {
		String str = "Cтрока";
		String str2 = "12345";
		//System.out.println(reverseChars("12345"));
		reverseChars(str);
		
	}
}
