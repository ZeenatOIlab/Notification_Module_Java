package com.example.notification_module_java;

import static android.app.Notification.EXTRA_NOTIFICATION_ID;
import static android.app.Notification.VISIBILITY_PRIVATE;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.HandlerThread;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MainActivity extends AppCompatActivity {
    String CHANNEL_ID = "123";
    private static final String KEY_TEXT_REPLY = "key_text_reply";
    Button simple_notificationButton, tap_notificationButton, action_notificationButton,
            reply_notificationButton, progress_notificationButton, urgent_notificationButton, messageStyle_notificationButton, bigPicture_notificationButton, bigText_notificationButton, headsup_notificationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        createNotificationChannel();
        simple_notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simple_notification();
            }
        });
        tap_notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tap_notification();
            }
        });
        action_notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionButton_notification();
            }
        });
        reply_notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionButton_notification();
            }
        });
        progress_notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    progressNotification();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        urgent_notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                urgent_notification();
            }
        });
        messageStyle_notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message_style_notification();
            }
        });
        bigPicture_notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bigPicture_notification();
            }
        });
        bigText_notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bigText_notification();
            }
        });
        headsup_notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                headsup_notification();
            }
        });
    }

    private void initView() {
        simple_notificationButton = findViewById(R.id.simple_notificationButton);
        tap_notificationButton = findViewById(R.id.tap_notificationButton);
        action_notificationButton = findViewById(R.id.action_notificationButton);
        reply_notificationButton = findViewById(R.id.reply_notificationButton);
        progress_notificationButton = findViewById(R.id.progress_notificationButton);
        urgent_notificationButton = findViewById(R.id.urgent_notificationButton);
        messageStyle_notificationButton = findViewById(R.id.messageStyle_notificationButton);
        bigPicture_notificationButton = findViewById(R.id.bigPicture_notificationButton);
        bigText_notificationButton = findViewById(R.id.bigText_notificationButton);
        headsup_notificationButton = findViewById(R.id.headsup_notificationButton);
    }

    private void simple_notification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("My notification")
                .setContentText("Much longer text that cannot fit one line...")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit one line..."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, builder.build());
    }

    private void tap_notification() {
        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, MainActivity2.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(2, builder.build());
    }

    private void actionButton_notification() {
        Intent snoozeIntent = new Intent(this, MyBroadcastReceiver.class);
        snoozeIntent.setAction("action");
        snoozeIntent.putExtra(EXTRA_NOTIFICATION_ID, 0);
        PendingIntent snoozePendingIntent =
                PendingIntent.getBroadcast(this, 0, snoozeIntent, 0);

        @SuppressLint("NotificationTrampoline") NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(snoozePendingIntent)
                .addAction(R.drawable.logo, "action",
                        snoozePendingIntent);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(3, builder.build());
    }

    //    private void replyNotification()
//    {
//        // Key for the string that's delivered in the action's intent.
//        String replyLabel = getResources().getString(R.string.reply_label);
//        RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
//                .setLabel(replyLabel)
//                .build();
//        // Build a PendingIntent for the reply action to trigger.
//        PendingIntent replyPendingIntent =
//                PendingIntent.getBroadcast(getApplicationContext(),
//                        conversation.getConversationId(),
//                        getMessageReplyIntent(conversation.getConversationId()),
//                        PendingIntent.FLAG_UPDATE_CURRENT);
//    }
    private void progressNotification() throws InterruptedException {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setContentTitle("Picture Download")
                .setContentText("Download in progress")
                .setSmallIcon(R.drawable.logo);

// Issue the initial notification with zero progress
        int PROGRESS_MAX = 100;
        int PROGRESS_CURRENT = 0;
        // Sets the initial progress to 0
        builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);
        notificationManager.notify(5, builder.build());
        for (int i = 1; i < 100; i++) {
            builder.setProgress(PROGRESS_MAX, i, false);
            HandlerThread.sleep(100);
        }
        // Updates the notification when the progress is done
        builder.setContentText("Download complete")
                .setProgress(0, 0, false);
        notificationManager.notify(5, builder.build());
    }

    private void urgent_notification() {
        Intent fullScreenIntent = new Intent(this, MainActivity2.class);
        PendingIntent fullScreenPendingIntent = PendingIntent.getActivity(this, 0,
                fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setFullScreenIntent(fullScreenPendingIntent, true)
                .setVisibility(NotificationCompat.VISIBILITY_PRIVATE);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(6, builder.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void message_style_notification() {
        NotificationCompat.InboxStyle iStyle = new NotificationCompat.InboxStyle();
        iStyle.addLine("Message 1.");
        iStyle.addLine("Message 2.");
        iStyle.addLine("Message 3.");
        iStyle.addLine("Message 4.");
        iStyle.addLine("Message 5.");
        iStyle.setSummaryText("+2 more");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Inbox Style Notification Example")
                .setStyle(iStyle);
        // Set the intent to fire when the user taps on notification.
        Intent resultIntent = new Intent(MainActivity.this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, resultIntent, 0);
        builder.setContentIntent(pendingIntent);
        // Sets an ID for the notification

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(7, builder.build());
    }

    private void bigPicture_notification() {
        // Assign big picture notification
        NotificationCompat.BigPictureStyle bpStyle = new NotificationCompat.BigPictureStyle();
        bpStyle.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.oilab_lgo)).build();
        // Set the intent to fire when the user taps on notification.
        Intent rIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://learning.oilab.in/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, rIntent, 0);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Big Picture Notification Example")
                .addAction(android.R.drawable.ic_menu_share, "Share", pendingIntent)
                .setStyle(bpStyle);

        mBuilder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // It will display the notification in notification bar
        notificationManager.notify(10, mBuilder.build());
    }

    private void bigText_notification() {
        //To set large icon in notification
        Bitmap licon = BitmapFactory.decodeResource(getResources(), R.drawable.oilab_lgo);
        //Assign BigText style notification
        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
        bigText.bigText("Welcome to tutlane, it provides a tutorials related to all technologies in software industry. Here we covered complete tutorials from basic to adavanced topics from all technologies");
        bigText.setSummaryText("By: Tutlane");

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                .setSmallIcon(R.drawable.oilab_lgo)
                .setContentTitle("Big Text Notification Example")
                .setLargeIcon(licon)
                .setStyle(bigText);
        // Set the intent to fire when the user taps on notification.
        Intent resultIntent = new Intent(MainActivity.this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, resultIntent, 0);
        mBuilder.setContentIntent(pendingIntent);
        // Sets an ID for the notification

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // It will display the notification in notification bar
        notificationManager.notify(11, mBuilder.build());
    }

    private void headsup_notification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Oilab Leaning ")
                .setContentText("Oilab Leaning Notification")
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://learning.oilab.in/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Intent buttonIntent = new Intent(getBaseContext(), BroadcastReceiver.class);
        buttonIntent.putExtra("notificationId", 13);
        PendingIntent dismissIntent = PendingIntent.getBroadcast(getBaseContext(), 0, buttonIntent, 0);
        builder.addAction(android.R.drawable.ic_menu_view, "VIEW", pendingIntent);
        builder.addAction(android.R.drawable.ic_delete, "DISMISS", dismissIntent);
        builder.setFullScreenIntent(dismissIntent,true);
        builder.build().flags=NotificationCompat.FLAG_AUTO_CANCEL;

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(13, builder.build());
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}