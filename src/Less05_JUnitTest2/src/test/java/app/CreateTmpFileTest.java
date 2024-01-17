package app;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class CreateTmpFileTest {
	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();
	@Test(expected = IOException.class)
	public void createFileTest() throws IOException {
		boolean result;
		File randomDir = tempFolder.newFolder("random");
		File data = new File(randomDir, "r?and.data");
		result = data.createNewFile();
		assertTrue("Не удалось создать файл random.data", result);
	}
}
