<h1 align="center">
  <img src="/banner.png" alt="Markdownify" width="650">
</h1>

<p align="center">
  <img src="https://img.shields.io/badge/Version-3.0-green?style=for-the-badge">
  <img src="https://img.shields.io/github/stars/instagram4j/instagram4j?style=for-the-badge">
  <img src="https://img.shields.io/github/issues/instagram4j/instagram4j?color=red&style=for-the-badge">
  <img src="https://img.shields.io/github/forks/instagram4j/instagram4j?color=teal&style=for-the-badge">
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Author-ErrorxCode-cyan?style=flat-square">
  <img src="https://img.shields.io/badge/Open%20Source-Yes-green?style=flat-square">
  <img src="https://img.shields.io/badge/Made%20In-Java-orange?style=flat-square">
</p>

<p align="center">
  <img src="https://jitpack.io/v/instagram4j/instagram4j/month.svg">
</p>

> Do things that you cannot do with the official API.

An Object Oriented re-write of **instagram4j**. This library is a replacement of lagacy **instagram4j** since it is too old and is no longer maintained. If you still want to lagacy **instagram4j** (as it has quite more features as of now), you can check out [EasyInsta](https://github.com/ErrorxCode/EasyInsta/) - A Easy2Use Instagram4j wrapper.

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

## 💻 Implimentation
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
	        implementation 'com.github.instagram4j.instagram4j:mobile:3.0'   // If you want to use mobile API
          implementation 'com.github.instagram4j.instagram4j:web:3.0'   // If you want to use web API
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
	    <groupId>com.github.instagram4j.instagram4j</groupId>
	    <artifactId>mobile</artifactId>
	    <version>3.0</version>
	</dependency>
```
or if you want `Web` API :
```xml
	<dependency>
	    <groupId>com.github.instagram4j.instagram4j</groupId>
	    <artifactId>web</artifactId>
	    <version>3.0</version>
	</dependency>
```
[![](https://jitpack.io/v/instagram4j/instagram4j.svg)](https://jitpack.io/#Errorxcode/JxInsta)


## 📖 Acknowledgements

-   [Instagram usage limits](https://www.linkedin.com/pulse/stay-within-boundaries-complete-breakdown-instagrams-cmscc/)
- [Instagram daily limit](https://socialpros.co/instagram-daily-limits/#:~:text=Instagram's%20Daily%20Limits%20%E2%80%93%20Like,than%2030%20likes%20per%20hour)
-   [API Policies](https://developers.facebook.com/devpolicy/)
-   [About Instagram checkpoints and challenges](https://github.com/ErrorxCode/JxInsta/blob/main/Instagram%20checkpoints.md)

## ✅ Its easy :)

```java
JxInsta insta = new JxInsta("username", "password");  
var profile = insta.getProfile("username");
profile.follow();
System.out.print("User Bio : " + profile.bio);
```
For detailed guide, check out [User guide](https://github.com/instagram4j/instagram4j/wiki)


## ❓FAQs

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

  **Answer.**  Yes, but only for web module. All you need to do is extract the crsf and session id from the cookie after login.
</details>




## 💌 Contributing

Contributions are always welcome! There is a lot of scope for contribution in this library.

Please refer to  [Contribution guide](/CONTRIBUTING.md). Also, see the  [code of conduct](/CODE_OF_CONDUCT.md).
To get started, you can check out "Projects" or "Issues" of the Repo. There are many thing left for implementation



## 💖Support
[![ko-fi](https://ko-fi.com/img/githubbutton_sm.svg)](https://ko-fi.com/A0A01XZ1CJ)

#### for any kind of help, you can contact me at:
- Instagram : [x0.rahil](https://instagram.com/x0.rahil)
- Email : hackerinsiderahil@gmail.com

You can also show your support by giving a ⭐.



> A special thanks to **[ErrorxCode](https://github.com/ErrorxCode)**, the creator of JxInsta. 
>
> JxInsta was originally developed as a modern continuation of this library during a period of inactivity. By joining forces, we have integrated the best features, modern design patterns, and updated mobile endpoints from JxInsta directly into the core of `instagram4j`. 
>
> _**Credits:** Extensive mobile endpoint discovery and architecture improvements provided by [ErrorxCode](https://github.com/ErrorxCode)._
