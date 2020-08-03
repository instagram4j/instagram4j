

instagram4j
========
[![Apache License](http://img.shields.io/badge/license-ASL-blue.svg)](https://github.com/brunocvcunha/instagram4j/blob/master/LICENSE)

:camera: Java wrapper using OkHttpClient for Instagram's private api (Android emulation)
## Table of contents
 - [Install](#install)
 - [Overview](#overview)
     - [Features](#features)
 - [Usage](#usage)
     - [Terms and Conditions](#terms-and-conditions)
     - [Setup & Login](#setup-and-login)
         - [Simple Login](#simple-login)
         - [Two factor login](#two-factor-login)
         - [Challenge login](#challenge-login)
         - [Login with proxy](#login-with-proxy)
     - [Serialization](#serialization)
 - [Key Concepts](#key-concepts)

# Install
Download release jar.
More information soon to follow.
# Overview
This Java library provides requests that emulate the Android Instagram app. Most of the official app functionality is supported here. This library has undergone a massive rewrite (instagram4j 1.x.x is not compatible) The rewrite intends to help with maintainability and flexibility throughout time.
## Features
Most of the Android Instagram app features are supported. Here are some notable ones.
- OkHttpClient and jackson-databind
- two factor login
- support for challenges
- iterable feeds
- serialization
- timeline, story, live, direct messaging, shopping, and more!

# Usage
## Terms and Conditions
This library is intended for personal use and for educational experiences due to limitations of Instagram's public API.

 - Please use Instagram's public API if possible
 - Do not use this library to spam (botting, spam messaging, etc...)
 - There will be no support for those with malicious intent
 - Use reasonable (human) delay in between sending requests
 - Don't be evil.

This library is in no way affiliated with, authorized, maintained, sponsored or endorsed by Instagram or any of its affiliates or subsidiaries. This is an independent and unofficial API. Use at your own risk.

Contributors are not responsible for usage and maintainability. Due to the nature of this project, some features of the library are not guaranteed as they make change and break in the future. This library is licensed under ASL.

---

## Setup and Login
An IGClient instance must be constructed and logged in to send most requests. 
### Simple Login
Basic login using IGClient builder method.
#### *Example:*
```java
IGClient client = IGClient.builder()
        .withUsername("username")
        .withPassword("password")
        .login();
```
### Two factor login
Provide a consumer that may resolve two factor login process. The example uses the included utility to resolve two factor logins.
#### *Example:*
```java
Scanner scanner = new Scanner(System.in);

// Callable that returns inputted code from System.in
Callable<String> inputCode = () -> {
    System.out.print("Please input code: ");
    return scanner.nextLine();
};

// handler for two factor login
LoginHandler twoFactorHandler = (client, response) -> {
    // included utility to resolve two factor
    // may specify retries. default is 3
    return IGChallengeUtils.resolveTwoFactor(client, response, inputCode);
};

IGClient client = IGClient.builder()
        .username("username")
        .password("password")
        .onTwoFactor(twoFactorHandler)
        .login();
```
### Challenge login
Sometimes a challenge may arise when logging in. Provide a LoginHandler to handle challenges. The example uses the included utility to handle challenges automatically.
#### *Example:*
```java
Scanner scanner = new Scanner(System.in);

// Callable that returns inputted code from System.in
Callable<String> inputCode = () -> {
    System.out.print("Please input code: ");
    return scanner.nextLine();
};

// handler for challenge login
LoginHandler challengeHandler = (client, response) -> {
    // included utility to resolve challenges
    // may specify retries. default is 3
    return IGChallengeUtils.resolve(client, response, inputCode);
};

IGClient client = IGClient.builder()
        .username("username")
        .password("password")
        .onChallenge(challengeHandler)
        .login();
```
### Login with proxy
You may provide Proxy and Authenticator for the Proxy thru configuring an OkHttpClient and passing it in thru the builder.
```java
OkHttpClient httpClient = new OkHttpClient.Builder().proxy(...).build();
IGClient client = IGClient.builder()
        .username("username")
        .password("password")
        .client(httpClient)
        .login();
```
## Serialization
IGClient is a Serializable object that can be saved and later reconstructed. **Session cookies however must be separately serialized**. Session cookies for OkHttpClient are done through an implementation of CookieJar. You may provide your own implementation of a serializable cookie jar and then serialize your cookies for later use. Session cookies are good for 90 days and avoids relogins.

See example for serialization and deserialization here.
# Key concepts
May be moved into Wiki section for detailed documentation.

## IGClient Actions
This library contains currently a very **limited** fluent wrapper for common actions. Such actions usually are composed of multiple requests (e.g. uploading a photo), so for convenience the action is wrapped into a single call. Common requests are also wrapped. This is very **experimental** and subject to design changes at any time.
### *Example:*
Uploading a photo to your timeline using the fluent wrapper
```java
IGClient client = ...
// Upload photo using fluent wrapper. Also returns the appropriate IGResponse
client.actions().timeline().uploadPhoto(new File("myPhoto.jpg"), "My Caption");
```
## IGRequest
IGRequest is the base abstract class for IGPostRequest and IGGetRequest, similar to the previous version. These abstract classes allow for requests to conform to a "standard" for maintainability and readability. The idea is for developers to easily make new requests for new endpoints in the future. IGRequest objects are passed into the **sendRequest** method in IGClient to be executed and parsed into its corresponding response or view. For convenience, IGRequest objects have an **execute** method that takes in IGClient to execute the request. Additionally, **formRequest** in IGRequest objects may be used to form a Request to be executed by OkHttpClient.
### IGPostRequest
For POST actions in the Instagram app, a JSON **payload** is used. The library makes use of jackson-databind to serialize POJO into JSON. In previous versions, the payload had to be "signed". However in newer versions, Instagram does not seem to require that anymore. Some requests do still require a url encoded form with "SIGNATURE" as the signature.
### *Example:*
```java
IGClient client = ...
FeedTimelineRequest request = new FeedTimelineRequest();
// Use sendRequest in IGClient
FeedTimelineResponse response = client.sendRequest(request);
```
```java
IGClient client = ...
FeedTimelineRequest request = new FeedTimelineRequest();
// Alternatively use execute
FeedTimelineResponse response = request.execute(client);
```
## IGResponse
POJO for JSON deserialization by jackson-databind. All IGResponse subclasses have a fallback map for properties not explicitly declared. Additionally, a different view entirely can be used to deserialize JSON. The idea is to support newer properties should there ever be updates instead of relying on hard coded properties as previously.
### *Example:*
Extracting story_cta ("See More") link text and then logging it.

Sample JSON for FeedReelsTrayResponse:
```
{
  "tray":[
    {
      "id":"...",
      "story_cta":[
        {
          "links":[
            {
              "webUri":"..."
            }
          ]
        }
      ]
    }
  ]
}
```
Sample code:
```java
IGClient client = ...
FeedReelsTrayResponse response = client.actions().story().tray();

// loops through each user's story (found at the top of the timeline) 
// if there is a "See More" link then log it
response.getTray().forEach(tray -> {
    tray.getItems().stream()
    .map(x -> x.get("story_cta"))
    .filter(Objects::nonNull)
    .forEach(story_cta -> {
        ArrayNode node = IGUtils.convertToView(story_cta, ArrayNode.class);
        log.info(node.get(0).get("links").get(0).get("webUri").asText());
    });
});
```
Alternatively by constructing a POJO and using jackson,
```java
// using lombok for getters
@Getter
public class StoryCta {
    private List<StoryCtaLink> links;
    ...
    public static List<StoryCta> convert(Object o) {
        return IGUtils.convertToView(o, new TypeReference<List<StoryCta>>() {});
    }
    
    @Getter
    public static class StoryCtaLink {
        private String webUri;
        ...
    }
}
```
```java
IGClient client = ...
FeedReelsTrayResponse response = client.actions().story().tray();

// loops through each user's story and if there is a "See More" link, log it
response.getTray().forEach(tray -> {
     tray.getItems().stream()
    .map(item -> item.get("story_cta"))
    .filter(Objects::nonNull)
    .flatMap(story_cta -> StoryCta.convert(story_cta).stream())
    .forEach(item -> {
       log.debug(item.getLinks().get(0).getWebUri());
    });
});
```
### Feeds
Feeds are **paginated** which means a request for one only returns a certain amount of objects and a **max_id** (or **cursor** for direct) is needed in future requests for more data. For example, a request for a users followers may return only the first twenty. To retrieve the next amount of followers, you need to pass in **max_id** (which is retrieved from the response of this first request). For convenience, the library provides a FeedIterator for paginated requests. It is **recommended** to wait in between requests for a reasonable (human) amount of time in order to not trigger a soft ban for spam.
#### *Example:*
```Java
IGClient client = ...
// form a FeedIterator for FeedTimelineRequest
FeedIterator<FeedTimelineResponse> iter = new FeedIterator<>(client, new FeedTimelineRequest());
// setting a limit of 2 responses (initial and one paginated)
int limit = 2;
while (iter.hasNext() && limit-- > 0) {
    FeedTimelineResponse response = iter.next();
    // Actions here
    response.getFeed_items().forEach(...);
    // Recommended to wait in between iterations
}
```
