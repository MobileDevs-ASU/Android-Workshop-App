# Workshop Application for Android
This is the repository for the application written in our workshop for Spring 2017. Use this code for reference if you didn't make it to our meeting and are trying to catch up.

Files:

[AndroidManifest]: Important information about the application which must be provided before running the app. Here, we specify the app's permissions and our activities.

[activity_main]: The XML layout for the MainActivity.

[other_activity]: The XML layout for the activity that MainActivity navigates to.

[listitem_layout]: The XML layout for the items being stored in the ListView inside other activity.

[MainActivity]: The Java code that creates the MainActivity. It also provides functionality to the buttons and other views implemented in activity_main.xml

[OtherActivity]: The Java code that creates the activity that MainActivity navigates to.

[APIRequest]: An [AsyncTask] that takes an input and searches for related events. This class makes API calls to the [Tickemaster API].

Any questions about the app can be posted to our [slack] page in the *#android* channel. 

MobileDevs will hold Android workshops until February 8th, every Tuesday and Wednesday from **6:00** to **8:00 p.m.** in **Stauffer B125**. 


[AndroidManifest]: <https://github.com/MobileDevs-ASU/Android-Workshop-App/blob/master/app/src/main/AndroidManifest.xml>
[activity_main]: <https://github.com/MobileDevs-ASU/Android-Workshop-App/blob/master/app/src/main/res/layout/activity_main.xml>
[other_activity]: <https://github.com/MobileDevs-ASU/Android-Workshop-App/blob/master/app/src/main/res/layout/other_activity.xml>
[listitem_layout]: <https://github.com/MobileDevs-ASU/Android-Workshop-App/blob/master/app/src/main/res/layout/listitem_layout.xml>
[MainActivity]: <https://github.com/MobileDevs-ASU/Android-Workshop-App/blob/master/app/src/main/java/com/example/central/myapplication/MainActivity.java>
[OtherActivity]: <https://github.com/MobileDevs-ASU/Android-Workshop-App/blob/master/app/src/main/java/com/example/central/myapplication/OtherActivity.java>
[APIRequest]: <https://github.com/MobileDevs-ASU/Android-Workshop-App/blob/master/app/src/main/java/com/example/central/myapplication/APIRequest.java>
[AsyncTask]: <https://developer.android.com/reference/android/os/AsyncTask.html>
[Ticketmaster API]: <http://developer.ticketmaster.com/products-and-docs/apis/discovery-api/v2/>
[slack]: <https://mobiledevs-asu.slack.com/>
