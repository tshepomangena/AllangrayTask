package za.co.allangray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;

public class ReadFiles {

	private StringBuilder userBuilder;
	private StringBuilder tweetBuilder;
	
	private StringBuilder readUsersFile() {
		try {
			URL url = getClass().getResource("user.txt");
			File file = new File(url.getPath());
			
			FileReader fileReader = new FileReader(file);
		    BufferedReader buffReader = new BufferedReader(fileReader);
		    
		    String data;
		    userBuilder = new StringBuilder();
		    while((data = buffReader.readLine()) != null) {
		    	userBuilder.append(data);
		    	userBuilder.append("\n");
		    }
		    buffReader.close();
		    
		} catch (FileNotFoundException fne) {
			System.out.println("Users File not found...");
		} catch (Exception e) {
			System.out
					.println("Error occured while reading Users File. Reason:"
							+ e);
		}
		return userBuilder;
	}
	
	private StringBuilder readTweetsFile() {
		try {
			URL url = getClass().getResource("tweet.txt");
			File file = new File(url.getPath());
			
			FileReader fileReader = new FileReader(file);
		    BufferedReader buffReader = new BufferedReader(fileReader);
		    
		    String data;
		    tweetBuilder = new StringBuilder();
		    while((data = buffReader.readLine()) != null) {
		    	tweetBuilder.append(data);
		    	tweetBuilder.append("\n");
		    }
		    buffReader.close();
		    
		} catch (FileNotFoundException fne) {
			System.out.println("Tweets File not found...");
		} catch (Exception e) {
			System.out
					.println("Error occured while reading Tweets File. Reason:"
							+ e);
		}
		return tweetBuilder;
	}

	public String getUserFileData() {
		userBuilder = readUsersFile();
		return userBuilder.toString();
	}
	
	public String getTweetFileData() {
		tweetBuilder = readTweetsFile();
		return tweetBuilder.toString();
	}
}
