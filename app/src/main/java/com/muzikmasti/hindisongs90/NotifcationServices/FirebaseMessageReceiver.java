package com.muzikmasti.hindisongs90.NotifcationServices;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import com.muzikmasti.hindisongs90.Activities.MainActivity;
import com.muzikmasti.hindisongs90.Activities.SplashActivity;
import com.muzikmasti.hindisongs90.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class FirebaseMessageReceiver extends FirebaseMessagingService {

    // Override onMessageReceived() method to extract the
    // title and
    // body from the message passed in FCM
    String ttitle, message, image_url, redirect = "";
    Intent intent;

    @Override
    public void
    onMessageReceived(RemoteMessage remoteMessage) {

        Log.d("MyNotificationsReceived", "" + remoteMessage.getData());

        // First case when notifications are received via
        // data event
        // Here, 'title' and 'message' are the assumed names
        // of JSON
        // attributes. Since here we do not have any data
        // payload, This section is commented out. It is
        // here only for reference purposes.
        /*if(remoteMessage.getData().size()>0){
            showNotification(remoteMessage.getData().get("title"),
                          remoteMessage.getData().get("message"));
        }*/

        // Second case when notification payload is
        // received.
        if (!remoteMessage.getData().toString().equals("")) {
            // Since the notification is received directly from
            // FCM, the title and the body can be fetched
            // directly as below.
            JSONObject jsonObject = new JSONObject(remoteMessage.getData());
            try {
                ttitle = String.valueOf(jsonObject.get("title"));
                message = String.valueOf(jsonObject.get("description"));
                image_url = String.valueOf(jsonObject.get("image"));
                redirect = String.valueOf(jsonObject.get("redirurl"));
                Log.d("MyData", "" + ttitle + message);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            showNotification(ttitle, message, image_url, redirect);
        }
    }

    // Method to get the custom Design for the display of
    // notification.


    public Bitmap getImage(String image_url) {
        Bitmap image = null;
        try {
            URL url = new URL(image_url);
            image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            System.out.println(e);
        }
        return image;
    }

    private RemoteViews getCustomDesign(String title, String message, String image_url, String redirurl) {
        RemoteViews remoteViews = new RemoteViews(
                getApplicationContext().getPackageName(),
                R.layout.notification);
        remoteViews.setTextViewText(R.id.title, title);
        remoteViews.setTextViewText(R.id.message, message);
        remoteViews.setImageViewResource(R.id.icon, R.drawable.logo_icon);
        return remoteViews;
    }

    // Method to display the notifications
    public void showNotification(String title, String message, String image_url, String redirurl) {
        // Pass the intent to switch to the MainActivity
        if (redirurl.contains("http") || redirurl.contains("https")) {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(redirurl));
        } else {
            intent = new Intent(this, SplashActivity.class);
        }
        // Assign channel ID
        String channel_id = "notification_channel";
        // Here FLAG_ACTIVITY_CLEAR_TOP flag is set to clear
        // the activities present in the activity stack,
        // on the top of the Activity that is to be launched
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // Pass the intent to PendingIntent to start the
        // next Activity
        PendingIntent pendingIntent
                = PendingIntent.getActivity(
                this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        // Create a Builder object using NotificationCompat
        // class. This will allow control over all the flags
        NotificationCompat.Builder builder
                = new NotificationCompat
                .Builder(getApplicationContext(), channel_id)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(getImage(image_url))
                        .bigLargeIcon(null))
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentText(message)
                .setVibrate(new long[]{1000, 1000, 1000,
                        1000, 1000})
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent);

        // A customized design for the notification can be
        // set only for Android versions 4.1 and above. Thus
        // condition for the same is checked here.
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            builder = builder.setContent(
//                    getCustomDesign(title, message, image_url, redirect));
//        } // If Android Version is lower than Jelly Beans,
//        // customized layout cannot be used and thus the
//        // layout is set as follows
//        else {
//            builder = builder.setContentTitle(title)
//                    .setContentText(message)
//                    .setSmallIcon(R.drawable.logo_icon);
//        }
        // Create an object of NotificationManager class to
        // notify the
        // user of events that happen in the background.
        NotificationManager notificationManager
                = (NotificationManager) getSystemService(
                Context.NOTIFICATION_SERVICE);
        // Check if the Android Version is greater than Oreo
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel
                    = new NotificationChannel(
                    channel_id, "web_app",
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(
                    notificationChannel);
        }
        notificationManager.notify(0, builder.build());
    }

}