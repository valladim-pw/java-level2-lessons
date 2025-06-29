package HomeWork.MavenFilesZipTest;

import java.io.IOException;

public class ZipTest2 {
	public static void main(String[] args) throws IOException {
		Runtime.getRuntime().exec("cd /d d:/JAVAProgwards/2.00_JavaAdvance0421A_lessons/src/HomeWork/MavenFilesZipTest");
		Runtime.getRuntime().exec("rar -a d:/JAVAProgwards/2.00_JavaAdvance0421A_lessons/src/HomeWork/MavenFilesZipTest/file.txt");
		
	}
}
