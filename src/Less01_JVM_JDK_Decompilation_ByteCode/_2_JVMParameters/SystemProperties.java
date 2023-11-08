package Less01_JVM_JDK_Decompilation_ByteCode._2_JVMParameters;

public class SystemProperties {
	public static void main(String[] args) {
		String p1 = System.getProperty("file.separator");
		String p2 = System.getProperty("line.separator");
		String p3 = System.getProperty("user.language");
		String p4 = System.getProperty("user.region");
		String p5 = System.getProperty("file.encoding");
		System.out.println(p1 + "\n" + p2 + "\n" + p3 + "\n" + p4 + "\n" + p5);
	}
}
