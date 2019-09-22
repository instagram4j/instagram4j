instagram4j
========

[![Apache License](http://img.shields.io/badge/license-ASL-blue.svg)](https://github.com/brunocvcunha/instagram4j/blob/master/LICENSE)
[![Build Status](https://travis-ci.org/brunocvcunha/instagram4j.svg)](https://travis-ci.org/brunocvcunha/instagram4j)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.brunocvcunha.instagram4j/instagram4j/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.brunocvcunha.instagram4j/instagram4j) [![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](http://makeapullrequest.com)
[![Code Climate](https://codeclimate.com/github/brunocvcunha/instagram4j/badges/gpa.svg)](https://codeclimate.com/github/brunocvcunha/instagram4j)

:camera: Java client to Instagram's private API. Allows access to all features that the Instagram app provides.

Based on the [Instagram PHP Api](https://github.com/mgp25/Instagram-API) and [Instagram Python Api](https://github.com/LevPasha/Instagram-API-python).


Usage
--------

Download [the latest release JAR][1] or grab via Maven:
```xml
<dependency>
  <groupId>org.brunocvcunha.instagram4j</groupId>
  <artifactId>instagram4j</artifactId>
  <version>1.12</version>
</dependency>
```
or Gradle:
```groovy
compile 'org.brunocvcunha.instagram4j:instagram4j:1.12'
```



Supported Operations & Examples
--------

#### Login

```java
// Login to instagram
Instagram4j instagram = Instagram4j.builder().username("username").password("password").build();
instagram.setup();
instagram.login();

```

#### Login with proxy without authentication

```java
// Login to instagram

HttpHost proxy = new HttpHost("host", "port", "http");
Instagram4j instagram = Instagram4j.builder().username("username").password("password").proxy(proxy).build();
instagram.setup();
instagram.login();

```


#### Login with proxy with authentication

```java
// Login to instagram

HttpHost proxy = new HttpHost("host", "port", "http");
CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(new AuthScope(AuthScope.ANY),
                new UsernamePasswordCredentials("login", "password"));
        
        
Instagram4j instagram = Instagram4j.builder().username("username").password("password").proxy(proxy).credentialsProvider(credentialsProvider).build();
instagram.setup();
instagram.login();

```

#### Search user by handle
```java
InstagramSearchUsernameResult userResult = instagram.sendRequest(new InstagramSearchUsernameRequest("github"));
System.out.println("ID for @github is " + userResult.getUser().getPk());
System.out.println("Number of followers: " + userResult.getUser().getFollower_count());
```

#### Follow user
```java
instagram.sendRequest(new InstagramFollowRequest(userResult.getUser().getPk()));
```

#### Unfollow user
```java
instagram.sendRequest(new InstagramUnfollowRequest(userResult.getUser().getPk()));
```

#### Get user followers
```java
InstagramGetUserFollowersResult githubFollowers = instagram.sendRequest(new InstagramGetUserFollowersRequest(userResult.getUser().getPk()));
List<InstagramUserSummary> users = githubFollowers.getUsers();
for (InstagramUserSummary user : users) {
    System.out.println("User " + user.getUsername() + " follows Github!");
}

```

#### Search user by handle
```java
InstagramSearchUsernameResult userResult = instagram.sendRequest(new InstagramSearchUsernameRequest("github"));
System.out.println("ID for @github is " + userResult.getUser().getPk());
System.out.println("Number of followers: " + userResult.getUser().follower_count);
```

#### Upload a photo to your feed
```java
instagram.sendRequest(new InstagramUploadPhotoRequest(
        new File("/tmp/file-to-upload.jpg"),
        "Posted with Instagram4j, how cool is that?"));
```

#### Upload a video to your feed
```java
instagram.sendRequest(new InstagramUploadVideoRequest(
        new File("/tmp/file-to-upload.mp4"),
        "Video posted with Instagram4j, how cool is that?"));
```

#### Get feed for a hashtag
```java
InstagramFeedResult tagFeed = instagram.sendRequest(new InstagramTagFeedRequest("github"));
for (InstagramFeedItem feedResult : tagFeed.getItems()) {
    System.out.println("Post ID: " + feedResult.getPk());
}
```

#### Perform a like operation for a media
```java
instagram.sendRequest(new InstagramLikeRequest(feedResult.getPk()));
```

#### Add a comment for a media
```java
instagram.sendRequest(new InstagramPostCommentRequest(feedResult.getPk(), "Hello! How are you?"));
```


#### Get comments from media
```java
InstagramGetMediaCommentsResult commentsResult = instagram.sendRequest(new InstagramGetMediaCommentsRequest(mediaId, maxCommentId));
```

#### Share message
```java
recipients - List of recipients IDs (i.e. "1234567890")
instagram.sendRequest(InstagramDirectShareRequest.builder(ShareType.MESSAGE, recipients).message(message).build());
```

#### Share media
```java
recipients - List of recipients IDs (i.e. "1234567890")
instagram.sendRequest(InstagramDirectShareRequest.builder(ShareType.MEDIA, recipients).mediaId(mid).message(message).build());
```

#### Edit media
```java
InstagramEditMediaRequest r = new InstagramEditMediaRequest(mediaId, caption);
UserTags tags = r.new UserTags();
tags.getTagsToAdd().add(r.new UserTag(userId, posX, posY));
tags.getTagsToAdd().add(r.new UserTag(userId2, posX2, posY2));
tags.getUserIdsToRemoveTag().add("1231231231");
tags.getUserIdsToRemoveTag().add("3213213213");
r.setUserTags(tags);
instagram.sendRequest(r);
```



#### Mute Post and Story
```java
instagram.sendRequest(new InstagramMuteStoryAnPostRequest(userResult.getUser().getPk()));

```


#### Mute Post 
```java
instagram.sendRequest(new InstagramMutePostRequest(userResult.getUser().getPk()));

```

#### Mute Story 
```java
instagram.sendRequest(new InstagramMuteStoryRequest(userResult.getUser().getPk()));

```

#### (More operations to be added)

Building from the source
--------
```
git clone https://github.com/brunocvcunha/instagram4j
cd instagram4j
mvn clean install
```

#

Snapshots of the development version are available in [Sonatype's `snapshots` repository][snap].

instagram4j requires at minimum Java 8.


 [1]: https://search.maven.org/remote_content?g=org.brunocvcunha.instagram4j&a=instagram4j&v=LATEST
 [snap]: https://oss.sonatype.org/content/repositories/snapshots/



 # Terms and conditions

- You will NOT use this API for marketing purposes (spam, botting, harassment, massive bulk messaging...).
- We do NOT give support to anyone who wants to use this API to send spam or commit other crimes.
- We reserve the right to block any user of this repository that does not meet these conditions.

## Legal

This code is in no way affiliated with, authorized, maintained, sponsored or endorsed by Instagram or any of its affiliates or subsidiaries. This is an independent and unofficial API. Use at your own risk.
