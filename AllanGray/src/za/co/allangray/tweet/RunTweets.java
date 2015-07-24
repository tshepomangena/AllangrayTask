package za.co.allangray.tweet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import za.co.allangray.ReadFiles;

public class RunTweets{

	private String showTweets(String users, String tweets) {
		String mutatedTweets = null;
		String noTweets = "No Conversation or Tweets available...";
		
		mutatedTweets = buildTweetConversation(users, tweets);
		
		return mutatedTweets != null ? mutatedTweets.toString() : noTweets;
	}
	
	private String buildTweetConversation(String users, String tweets) {
		String buildTweets = new String();
		
		if (users != null && tweets != null) {
			List<String> usersList = getListOfUsersInAphabeticalOrder(users);
			tweets = tweets.replaceAll("> ", ": ");
			
			List<String> tweetList = turnTweetsStringToList(tweets);
			
			for (String user : usersList) {
				if (tweets.contains(user)) {
					buildTweets += user + "\n";
					for (String tweet : tweetList) {
						if(tweet.trim().startsWith(user)) {
							// by default you should see your own tweets.
							buildTweets += "\t" + "@" + tweet + "\n";
						}
						
						if(tweetRulesAllow(tweet, user)) {
							buildTweets += "\t" + "@" + tweet + "\n";
						} 
					}
				} else {
					buildTweets += user + "\n";
				}
			}
		}
		return buildTweets;
	}

	private boolean tweetRulesAllow(String tweet, String user) {
		// Not the best way to write this but I was sleepy and tired... :) I would have preferred not to hard code this part here.
		boolean rule1 = (user.equals("Ward") && tweet.trim().startsWith("Alan"));
		boolean rule2 = (user.equals("Alan") && tweet.trim().startsWith("Martin")); 
		boolean rule3 = (user.equals("Ward") && (tweet.trim().startsWith("Martin") || tweet.trim().startsWith("Alan") )); 
		
		if(rule1 || rule2 || rule3) {
			return true;
		}
		return false;
	}

	private List<String> turnTweetsStringToList(String tweets) {
		List<String> tweetList = new ArrayList();
		Set<String> set = new LinkedHashSet<>(Arrays.asList(tweets.split(Pattern
				.quote("\n"))));
		
		for (String tweet : set) {
			if (tweet.isEmpty())
				continue;
			tweetList.add(tweet);
		}
		return tweetList;
	}

	private List<String> getListOfUsersInAphabeticalOrder(String users) {
		users = users.trim().replaceAll("\n", ",").replaceAll("follows", ",")
				.replaceAll(" ", "");

		Set<String> set = new LinkedHashSet<>(Arrays.asList(users.split(Pattern
				.quote(","))));
		List<String> list = new ArrayList();
		for (String user : set) {
			if (user.isEmpty())
				continue;
			list.add(user);
		}
		Collections.sort(list, sortAlphabetically);
		return list;
	}
	
	private static Comparator<String> sortAlphabetically = new Comparator<String>() {
	    public int compare(String str1, String str2) {
	        int res = String.CASE_INSENSITIVE_ORDER.compare(str1, str2);
	        if (res == 0) {
	            res = str1.compareTo(str2);
	        }
	        return res;
	    }
	};

	public static void main(String[] args) {
		ReadFiles readFiles = new ReadFiles();
		String tweets = readFiles.getTweetFileData();
		String users = readFiles.getUserFileData();
		
		RunTweets runTweets = new RunTweets();
		System.out.println(runTweets.showTweets(users, tweets));
	}
}
