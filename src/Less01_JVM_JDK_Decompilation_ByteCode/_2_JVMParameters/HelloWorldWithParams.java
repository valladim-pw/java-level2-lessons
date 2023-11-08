package Less01_JVM_JDK_Decompilation_ByteCode._2_JVMParameters;

public class HelloWorldWithParams {
	public static void main(String[] args) {
		if(args.length == 0)
			System.out.println("Нет параметров");
		for(int i = 0; i < args.length; i++) {
			System.out.println("Параметр " + i + " = " + args[i]);
		}
	}
}
