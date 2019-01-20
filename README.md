# SmsApp
It fetches all the inbox messages ,shows if message if read or unread .It also shows notification as
 soon as Sms received in the device.

#Specs
*Language Used - Java
*Build with Gradle
*IDE Used -  Android Studio.

#Permissions required
INTERNET , READ_SMS , RECEIVE_SMS

#Architecture used(Design Pattern)
MVP - Model View Presenter

#Working
Install the APK,it will show the Shimmer effect as long as it will take to fetch the messages from the device.

#API Reference
App uses Telephony.SMS apis provided by google.

#Libraries Used
Retrofit,RxJava,Dagger,Shimmer RecyclerView ,Butterknife, Easy permissions and other design libraries.

