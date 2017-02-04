instagram4j
========

[![Apache License](http://img.shields.io/badge/license-ASL-blue.svg)](https://github.com/brunocvcunha/instagram4j/blob/master/LICENSE)
[![Build Status](https://travis-ci.org/brunocvcunha/instagram4j.svg)](https://travis-ci.org/brunocvcunha/instagram4j)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.brunocvcunha.instagram4j/instagram4j/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.brunocvcunha.instagram4j/instagram4j)
[![Coverage Status](https://coveralls.io/repos/github/brunocvcunha/instagram4j/badge.svg?branch=master)](https://coveralls.io/github/brunocvcunha/instagram4j?branch=master)

:camera: Java client to Instagram's private API. Allows access to all the features that Instagram app provides.

(under construction)

Based on the [Instagram PHP Api](https://github.com/mgp25/Instagram-API)


Usage
--------

```java
    public static void main(String[] args) throws Exception {

        Instagram4j instagram = Instagram4j.builder().username("username").password("password").build();
        instagram.setup();
        
        instagram.sendRequest(new InstagramUploadPhotoRequest(
                new File("/tmp/file-to-upload.jpg",
                "Posted with Instagram4j, how cool is that?"));
                
```



Download
--------

Download [the latest JAR][1] or grab via Maven:
```xml
<dependency>
  <groupId>org.brunocvcunha.instagram4j</groupId>
  <artifactId>instagram4j</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```
or Gradle:
```groovy
compile 'org.brunocvcunha.instagram4j:instagram4j:1.0-SNAPSHOT'
```

Snapshots of the development version are available in [Sonatype's `snapshots` repository][snap].

instagram4j requires at minimum Java 8.


 [1]: https://search.maven.org/remote_content?g=org.brunocvcunha.instagram4j&a=instagram4j&v=LATEST
 [snap]: https://oss.sonatype.org/content/repositories/snapshots/
