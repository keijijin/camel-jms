package com.sample.bean;

import com.sample.model.TweetInfo;

import org.apache.camel.Exchange;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.HashtagEntity;
import twitter4j.Status;
import twitter4j.URLEntity;

public class TweetInfoBean {

    Logger log = LoggerFactory.getLogger(TweetInfoBean.class);
    
    public void MakeTweetInfo(Exchange exchange) {
        Status bodyIn = (Status) exchange.getIn().getBody();
        TweetInfo tweetInfo = new TweetInfo();
        tweetInfo.setTweetId(bodyIn.getId());
        tweetInfo.setText(bodyIn.getText());

        if (bodyIn.getUser() != null) {
            tweetInfo.setUsername(bodyIn.getUser().getName());
            tweetInfo.setLanguage(bodyIn.getUser().getLang());
            tweetInfo.setLocation(bodyIn.getUser().getLocation());
            tweetInfo.setScreenName(bodyIn.getUser().getScreenName());
            tweetInfo.setUserDescription(bodyIn.getUser().getDescription());
        }

        for(int i = 0; i < bodyIn.getURLEntities().length; i++) {
            URLEntity ue = bodyIn.getURLEntities()[i];
            tweetInfo.getUrls().add(ue.getText());
        }
            
        tweetInfo.setFavouriteCount(bodyIn.getFavoriteCount());
        DateTime dateTime = new DateTime(bodyIn.getCreatedAt());
        tweetInfo.setCreationDate(dateTime.toString("yyyy-MM-dd HH:mm:ss"));

        String url = "http://twitter.com/" + tweetInfo.getScreenName() + "/status/" + tweetInfo.getTweetId();
        tweetInfo.setUrl(url);

        for(int i = 0; i < bodyIn.getHashtagEntities().length; i++) {
            HashtagEntity hashtagEntity = bodyIn.getHashtagEntities()[i];
            tweetInfo.getHashTags().add(hashtagEntity.getText());
        }
        //Map map = exchange.getIn().getHeaders();
        //log.info("headers : {}", map);

        exchange.getIn().setBody(tweetInfo, TweetInfo.class);
    }
}
