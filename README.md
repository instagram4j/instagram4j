instagram4j
========

[![Apache License](http://img.shields.io/badge/license-ASL-blue.svg)](https://github.com/brunocvcunha/instagram4j/blob/master/LICENSE)
[![Build Status](https://travis-ci.org/brunocvcunha/instagram4j.svg)](https://travis-ci.org/brunocvcunha/instagram4j)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.brunocvcunha.instagram4j/instagram4j/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.brunocvcunha.instagram4j/instagram4j) [![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](http://makeapullrequest.com)

:camera: Java client to Instagram's private API. Allows access to all the features that Instagram app provides.

Based on the [Instagram PHP Api](https://github.com/mgp25/Instagram-API) and [Instagram Python Api](https://github.com/LevPasha/Instagram-API-python).


Usage
--------

Download [the latest release JAR][1] or grab via Maven:
```xml
<dependency>
  <groupId>org.brunocvcunha.instagram4j</groupId>
  <artifactId>instagram4j</artifactId>
  <version>1.0</version>
</dependency>
```
or Gradle:
```groovy
compile 'org.brunocvcunha.instagram4j:instagram4j:1.0'
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
List<InstagramUserSummary> users = ppfFollowers.getUsers();
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

#### Upload a photo your feed
```java
instagram.sendRequest(new InstagramUploadPhotoRequest(
        new File("/tmp/file-to-upload.jpg"),
        "Posted with Instagram4j, how cool is that?"));
```

#### Upload a video your feed
```java
instagram.sendRequest(new InstagramUploadVideoRequest(
        new File("/tmp/file-to-upload.mp4"),
        "Video posted with Instagram4j, how cool is that?"));
```

#### Get feed for a hashtag
```java
InstagramTagFeedResult tagFeed = instagram.sendRequest(new InstagramTagFeedRequest("github"));
for (InstagramTagFeedResultTag feedResult : tagFeed.getItems()) {
    System.out.println("Post ID: " + feedResult.getPk());
}
```

#### Perform a like operation for a media
```java
instagram.sendRequest(new InstagramLikeRequest(feedResult.getPk()));
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
