As we all know Instagram is improving its platform security day by day. It is making it hard to automate Instagram or use its API for any kind of private use. Because of this, I need to create a separate file for addressing such issues.


## Types of checkpoints/challenges
-  [Suspicious Login Attempt](/#suspicious-login-attempt)
-  [We Detected An Unusual Login Attempt](/#we-detected-an-unusual-login-attempt)
- [We suspected automation behaviour](/#we-suspected-automation-behaviour)

These are the most frequently occured checkpoints. If discovered, it will be appended to this table.
Below is the detail on each checkpoint and how to resolve them.



## We Detected An Unusual Login Attempt
Don’t panic! You’re not the only one!

**Many people all over the Internet are having this issue too.**

“Suspicious Login Attempt” and “We Detected an Unusual Login Attempt”, like this:

![](https://thepreviewapp.com/wp-content/uploads/2019/08/suspicious-login-attempt-instagram-solution-1.jpg)

This usually comes when there are too many login attempts in a short time. Currently, the period is not known but it's around 10 min. 

### Solution
After solving the challenge/checkpoint, don't log in immediately. Also, avoid sending more than 3 login requests in less than 2 min.


## Suspicious Login Attempt
This type of checkpoint occurs when your account is logged in from a new device, location or IP. For example, if you suddenly log in from a cloud server hosted in a different country, you might get this.

![What is a 'suspicious login attempt' on Instagram? - Quora](https://qph.cf2.quoracdn.net/main-qimg-aa83d9405024db95f659190d01a061e2-pjlq)

### Solution
From the account, click on "This was me". This will add that device to Instagram-verified devices and you will not face this checkpoint again. Also, don't change the device ID/uuid/user-agent while sending multiple login requests for the same account. Frequently changing these parameters for login same account, also causes this checkpoint.


## We suspected automation behaviour
This is another kind of checkpoint that comes when Instagram suspect automation on our account.
![INSTAGRAM: "We suspect automated behavior on your account" Anybody else got  this after the update (01-08-2023) ? : r/imagus](https://preview.redd.it/instagram-we-suspect-automated-behavior-on-your-account-v0-ylj1h5hpcpgb1.jpg?width=600&format=pjpg&auto=webp&s=3c72ba0470d031398b46cea32ab4040d477f316b)

This occurs mainly due to frequent requests made on the same endpoint or for the same action without natural breaks or delays. For example, if you are following many accounts in a short time without any human-like breaks, then Instagram suspect this behaviour. It may sometimes also ask captcha to be filled in to pass this check.


### Solution
Don't rush with the library/API. Give a minimum of 5-sec delay between subsequent requests
