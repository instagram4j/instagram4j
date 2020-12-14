



instagram4j
========
[ ![Download](https://api.bintray.com/packages/instagram4j/maven/instagram4j/images/download.svg?version=2.0.2) ](https://bintray.com/instagram4j/maven/instagram4j/2.0.2/link)
![Java CI with Gradle](https://github.com/instagram4j/instagram4j/workflows/Java%20CI%20with%20Gradle/badge.svg?branch=develop)
[![Apache License](http://img.shields.io/badge/license-ASL-blue.svg)](https://github.com/brunocvcunha/instagram4j/blob/master/LICENSE)
[![Discord](https://img.shields.io/discord/777326687326044171?label=chat)](https://discord.com/invite/gR2EPwZGEZ)

:camera: Java wrapper using OkHttpClient for Instagram's private api (Android emulation)
## Table of contents
 - [Install](#install)
 - [Overview](#overview)
     - [Features](#features)
 - [Usage](#usage)
     - [Terms and Conditions](#terms-and-conditions)
     - [Quick Usage](#quick-usage)
     - [Setup & Login](#setup-and-login)
         - [Simple Login](#simple-login)
         - [Two factor login](#two-factor-login)
         - [Challenge login](#challenge-login)
         - [Login with proxy](#login-with-proxy)
     - [Sending requests and actions](#sending-requests-and-actions)
     - [Serialization](#serialization)
 - [Contributing](#contributing)

# Install
The latest stable release: [ ![Download](https://api.bintray.com/packages/instagram4j/maven/instagram4j/images/download.svg?version=2.0.2) ](https://bintray.com/instagram4j/maven/instagram4j/2.0.2/link)

#### Example for gradle:
```java
dependencies {
    implementation 'com.github.instagram4j:instagram4j:2.0.2'
}
```

#### Example for maven:
```
  <dependencies>
    <dependency> 
      <groupId>com.github.instagram4j</groupId>
      <artifactId>instagram4j</artifactId>
      <version>2.0.2</version>
    </dependency> 
  </dependencies>
```

For develop (unreleased to central) builds, jitpack can be used.

```java
repositories {
    maven {
        url 'https://jitpack.io'
    }
}
```
```java
dependencies {
    implementation 'com.github.instagram4j:instagram4j:master-SNAPSHOT'
}
```

#### Example for maven:
```xml
  ...
  <dependencies>
      ...
    <dependency> 
      <groupId>com.github.instagram4j</groupId>
      <artifactId>instagram4j</artifactId>
      <version>master-SNAPSHOT</version>
    </dependency> 
  </dependencies>
  ...
  <repositories>
    ...
    <repository>
      <id>jitpack</id>
      <url>https://jitpack.io</url>   
    </repository>
 </repositories>
```

## Requirements
This project depends on
- Java 8+
- okhttpclient
- okhttpclient url connection
- jackson data-bind
- jackson annotations
- slf4j-api
- apache commons codec
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
## Quick Usage
**Login:**
```java
IGClient client = IGClient.builder()
        .username("username")
        .password("password")
        .login();
```
**Actions:**
```java
IGClient client = ...

client.actions()
.timeline()
.uploadPhoto(new File("myPhoto.jpg"), "My Caption")
.thenAccept(response -> {
    System.out.println("Successfully uploaded photo!");
})
.join(); // block current thread until complete
```
**Executing requests:**
```java
IGClient client = ...

// Alternatively use client.sendRequest
new FeedTimelineRequest().execute(client)
.thenAccept(response -> {
    response.getFeed_items().forEach(...);
})
.join(); // block current thread until complete
```
## Setup and Login
An IGClient instance must be constructed and logged in to send most requests. 
### Simple Login
Basic login using IGClient builder method.
#### *Example:*
```java
IGClient client = IGClient.builder()
        .username("username")
        .password("password")
        .login();
```
#### *Example:*
```java
// simulate pre login flow (synchronous) and post login flow (asynchronous)
IGClient client = IGClient.builder()
        .username("username")
        .password("password")
        .simulatedLogin();
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
## Sending requests and actions
This library provides a limited wrapper api that may be used to locate and send common requests. Not all features are supported through actions currently. Actual requests are located under the requests package and can be sent through IGClient like in previous versions. Requests can be sent asynchronously. All requests return a [CompletableFuture](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html).

#### *Simple Example:*
```java
IGClient client = ...

client.actions().timelime()
.uploadPhoto(new File("myPhoto.jpg"), "My Caption")
.thenAccept(res -> {
    // perform actions with response
    log.info("Uploaded photo {}", response.getMedia().getId());
})
.exceptionally(tr -> {
    // something has terribly gone wrong!
    // handle exception
    
    return null;
})
.join(); // blocks currect thread until completion
```
#### *Chainng Example:*
```java
IGClient client = ...

// finds user by username then gets friendship status
client.actions().users()
.findByUsername("instagram")
.thenCompose(UserAction::getFriendship)
.thenAccept(friendship -> {
    // response here
})
.join();

// uploads a photo then comments on it
client.actions().timeline()
.uploadPhoto(new File("myPhoto.jpg"), "My Caption")
.thenCompose(response -> {
    // action with response
    return new MediaCommentRequest(response.getMedia().getId(), "First comment!").execute(client)
})
.join();

```
## Serialization
IGClient is a Serializable object that can be saved and later reconstructed. **Session cookies however must be separately serialized**. Session cookies for OkHttpClient are done through an implementation of CookieJar. You may provide your own implementation of a serializable cookie jar and then serialize your cookies for later use. Session cookies are good for 90 days and avoids relogins.

See example for serialization and deserialization [here](https://github.com/instagram4j/instagram4j/blob/c2c4a0da8b18dce86900bb1af2393b6d0265b096/src/examples/java/serialize/SerializeTestUtil.java#L34).
# Contributing
See Issues tab to find good starting issues to work on. If you have an addition you would like to make, please do not hesitate to make a pull request!
