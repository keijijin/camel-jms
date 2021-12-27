package com.sample.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TweetInfo {
    private Long tweetId;
	private String text;
    private String url;
	private String username;
	private String language;
	private String location;
    private String screenName;
    private String userDescription;
	private Integer favouriteCount;
	private String creationDate;
    private List<String> urls = new ArrayList<>();
    private List<String> hashTags = new ArrayList<>();
}
