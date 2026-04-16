<h1 align="center">
  <br>
  <img src="/jxinsta.png" alt="Markdownify" width="250">
  <br>
  JxInsta
  <br>
</h1>

<h4 align="center">An Object Oriented java library for Instagram Private API.</h4>

<p align="center">
  <img src="https://img.shields.io/badge/Version-2.0-green?style=for-the-badge">
  <img src="https://img.shields.io/github/stars/ErrorxCode/JxInsta?style=for-the-badge">
  <img src="https://img.shields.io/github/issues/ErrorxCode/JxInsta?color=red&style=for-the-badge">
  <img src="https://img.shields.io/github/forks/ErrorxCode/JxInsta?color=teal&style=for-the-badge">
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Author-Rahil--Khan-cyan?style=flat-square">
  <img src="https://img.shields.io/badge/Open%20Source-Yes-cyan?style=flat-square">
  <img src="https://img.shields.io/badge/Written%20In-Java-cyan?style=flat-square">
</p>

<p align="center">
	<img src="/jxinsta-code.png" alt="Markdownify" width="750">
</p>


An Object Oriented Java library of Instagram Private API. This library is built as a replacement for old **instagram4j** since it is too old and is no longer maintained. If you still want to use **instagram4j** (as it has quite more features as of now), you can check out [EasyInsta](https://github.com/ErrorxCode/EasyInsta/) - A Easy2Use Instagram4j wrapper.

## 🎯Key Features

-   Lightweight and Easy 2 use, Object-oriented
-   No need API token
-   Supports  **Sending messages**
-   Supports  **Getting/fetching messages**
-   Supports  **Deleting message**
-   Supports  **_Realtime direct messages listener_** (Comming soon)
-   Supports  **Login using cache/saving sessions**
-   Supports  **Posting (Picture)**
-   Supports  **Adding stories (Photo)**
-   Supports  **Following/Unfollowing**
-   Supports  ***Acception/Ignoring follow request*** (Comming soon)
-   Supports  **Scrapping followings and followers**
-   Supports  **Getting profile data**
-   Supports  **Liking/commenting on post**
-   Supports  **Fetching feeds/users post**
-   Supports  **Downloading posts and pfp**
-   Supports **Fetching Post, liking and commening**
-   Supports **Scrapping comments of a post**
-   Supoorts **Fetching reels, stories & highlight**

## Implimentation
### Gradle
Step 1. Add the JitPack repository to your build file

```groovy
	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
```
  
Step 2. Add the dependency

```groovy
	dependencies {
	        implementation 'com.github.Errorxcode.jxinsta:mobile:2.0'   // If you want to use mobile API
          implementation 'com.github.Errorxcode.jxinsta:web:2.0'   // If you want to use web API
	}
```

### Maven
Step 1. Add to pom.xml
```xml
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
Step 2. Add the dependency
if you want `Mobile` API :
```xml
	<dependency>
	    <groupId>com.github.Errorxcode.jxinsta</groupId>
	    <artifactId>mobile</artifactId>
	    <version>2.0</version>
	</dependency>
```
or if you want `Web` API :
```xml
	<dependency>
	    <groupId>com.github.Errorxcode.jxinsta</groupId>
	    <artifactId>web</artifactId>
	    <version>2.0</version>
	</dependency>
```
[![](https://jitpack.io/v/Errorxcode/JxInsta.svg)](https://jitpack.io/#Errorxcode/JxInsta)


## Acknowledgements

-   [Instagram usage limits](https://www.linkedin.com/pulse/stay-within-boundaries-complete-breakdown-instagrams-cmscc/)
- [Instagram daily limit](https://socialpros.co/instagram-daily-limits/#:~:text=Instagram's%20Daily%20Limits%20%E2%80%93%20Like,than%2030%20likes%20per%20hour)
-   [API Policies](https://developers.facebook.com/devpolicy/)
-   [About Instagram checkpoints and challenges](https://github.com/ErrorxCode/JxInsta/blob/main/Instagram%20checkpoints.md)

## Its easy :)

```java
JxInsta insta = new JxInsta("username", "password");  
var profile = insta.getProfile("username");
profile.follow();
System.out.print("User Bio : " + profile.bio);
```
For detailed guide, check out [User guide](https://github.com/ErrorxCode/JxInsta/wiki)


## FAQs

<details>
  <summary>Can we use this library to make bots?</summary>

  **Answer.** Yes. But Instagram doesn't allow them to make bots with their official graph APIs. Although this is not the official API, you should follow the usage limits to prevent detection.
</details>

<details>
  <summary>Can we download stories or posts using this library?</summary>

  **Answer.** Yes, and that too without login
</details>

<details>
  <summary>Does the use of this library require any tokens or other keys?</summary>

  **Answer.** No. You only need to have the username and password of the account. You can also log in using cookies and bearer tokens.
</details>


<details>
  <summary>In Android, can we use Webview to log in?</summary>

  **Answer.**  Yes, check  [this](https://github.com/ErrorxCode/JxInsta/wiki/Android-users#using-webview-for-login)  example on how to use that
</details>




## Contributing

Contributions are always welcome! There is a lot of scope for contribution in this library.

Please refer to  [Contribution guide](https://github.com/ErrorxCode/JxInsta/blob/main/CONTRIBUTING.md). Also, see the  [code of conduct](https://github.com/ErrorxCode/JxInsta/blob/main/CODE_OF_CONDUCT.md).
To get started, you can check out "Projects" or "Issues" of the Repo. There are many thing left for implementation


## Support

The fastest channel to contact me is **Instagram**, just DM me and I'll reply to you within 24 hours. My Instagram : [x0.rahil](https://instagram.com/x0.rahil)
You can show your support by giving a ⭐.
