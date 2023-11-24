package Less01_JVM_JDK_Decompilation_ByteCode._1_Intro_Decompilation_ByteCode;

import java.io.*;

public class TestCMD {
		public static void main(String[] args)	{
			System.out.println("Opening cmd window");
			try{// cmd is a command that opens the command window//CMD /C is used to run commands and then terminate the existing window while CMD /K will run the command and then it returns you to the given prompt.
			
				Runtime.getRuntime().exec(new String[] {"cmd", "/K", "Start"});// the following line can also be used.....//Runtime.getRuntime().exec(new String[] {"cmd", "/C", "Start"});
			}
			catch (Exception e){
				System.out.println("Error: " + e);
			}
		}
}
