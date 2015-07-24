package za.co.allangray;

import org.junit.Assert;
import org.junit.Test;

public class ReadFilesTest {

	ReadFiles readFiles = new ReadFiles();
	
	@Test
	public void getUserFileDataTest() {
		System.out.println("User File contains: " + readFiles.getUserFileData());
		Assert.assertNotNull(readFiles.getUserFileData());
	}

	@Test
	public void getTweetFileDataTest() {
		System.out.println("Tweet File contains: " + readFiles.getTweetFileData());
		Assert.assertNotNull(readFiles.getTweetFileData());
	}
}
