package HomeWork.MavenFilesZipTest;

import java.io.*;
import java.nio.file.Files;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;



public class ZipTest {
	public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
		File destFile = new File(destinationDir, zipEntry.getName());
		
		String destDirPath = destinationDir.getCanonicalPath();
		String destFilePath = destFile.getCanonicalPath();
		
		if (!destFilePath.startsWith(destDirPath + File.separator)) {
			throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
		}
		
		return destFile;
	}
	
	static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
		if (fileToZip.isHidden()) {
			return;
		}
		if (fileToZip.isDirectory()) {
			if (fileName.endsWith("/")) {
				zipOut.putNextEntry(new ZipEntry(fileName));
				zipOut.closeEntry();
			} else {
				zipOut.putNextEntry(new ZipEntry(fileName + "/"));
				zipOut.closeEntry();
			}
			File[] children = fileToZip.listFiles();
			for (File childFile : children) {
				zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
			}
			return;
		}
		FileInputStream fis = new FileInputStream(fileToZip);
		ZipEntry zipEntry = new ZipEntry(fileName);
		zipOut.putNextEntry(zipEntry);
		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zipOut.write(bytes, 0, length);
		}
		fis.close();
	}
	
	public static void main(String[] args) throws IOException {
		String dir = "d:\\JAVAProgwards\\2.00_JavaAdvance0421A_lessons\\src\\HomeWork\\MavenFilesZipTest\\target";
		String zipFilePath = "d:\\JAVAProgwards\\2.00_JavaAdvance0421A_lessons\\src\\HomeWork\\MavenFilesZipTest\\target\\MavenCalculator-1.0.jar";
		File zip = new File(zipFilePath);
		String zipName = zip.getName();
		zipName = zipName.substring(0, zipName.length() - 3);
		System.out.println(zipName);
		//File file = new File(dir);
		
		File rarFile = new File("d:\\JAVAProgwards\\2.00_JavaAdvance0421A_lessons\\src\\HomeWork\\MavenFilesZipTest\\target\\MavenCalculator.rar");
		//rarFile.createNewFile();
		//String fileZip = "src/main/resources/unzipTest/compressed.zip";
		File destDir = new File(dir, zipName);
		if (!destDir.exists())
			destDir.mkdirs();
		
		byte[] buffer = new byte[1024];
		ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath));
		ZipEntry zipEntry = zis.getNextEntry();
		while (zipEntry != null) {
			File newFile = newFile(destDir, zipEntry);
			if (zipEntry.isDirectory()) {
				if (!newFile.isDirectory() && !newFile.mkdirs()) {
					throw new IOException("Failed to create directory " + newFile);
				}
			} else {
				// fix for Windows-created archives
				File parent = newFile.getParentFile();
				if (!parent.isDirectory() && !parent.mkdirs()) {
					throw new IOException("Failed to create directory " + parent);
				}
				// write file content
				FileOutputStream fos = new FileOutputStream(newFile);
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
			}
			zipEntry = zis.getNextEntry();
		}
		String sourceFile = destDir.getPath();
		FileOutputStream fos = new FileOutputStream(rarFile);
		ZipOutputStream zipOut = new ZipOutputStream(fos);
		
		File fileToZip = new File(sourceFile);
		zipFile(fileToZip, fileToZip.getName(), zipOut);
		zipOut.close();
		fos.close();
		//FileUtils.deleteDirectory(new File(destDir.getPath()));
	}
}
