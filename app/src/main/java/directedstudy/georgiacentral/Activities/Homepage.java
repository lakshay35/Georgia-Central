package directedstudy.georgiacentral.Activities;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import directedstudy.georgiacentral.Objects.User;
import directedstudy.georgiacentral.R;
import directedstudy.georgiacentral.Session.SessionManager;
import directedstudy.georgiacentral.Tables.NotificationSchema;
import directedstudy.georgiacentral.Tables.UserSchema;

public class Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        checkNotifications();
    }

    /**
     * Checks for any notifications a user might have
     */
    private void checkNotifications() {
        NotificationSchema notificationSchema = new NotificationSchema(this);

        SessionManager sessionManager = new SessionManager(this);

        UserSchema userSchema = new UserSchema(this);
        User user = userSchema.retrieveUser(sessionManager.getUserDetails().get(SessionManager.KEY_EMAIL));

        ArrayList<String> notifications = notificationSchema.retrieveNotifications(user.getUserID());
        for(String s: notifications) {
            notifyUser(s);
        }
    }

    /**
     * Notifies user of the availability of requested books
     * @param book
     */
    private void notifyUser(String book) {
        int CHANNEL_ID = (int) System.currentTimeMillis();
//
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(Integer.toString(CHANNEL_ID), name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) getSystemService(
                    NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, Integer.toString(CHANNEL_ID))
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Now Available")
                .setContentText(book)
                .setAutoCancel(true);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(CHANNEL_ID, mBuilder.build());
    }

    /**
     * Transfers user to the PostTextBook activity
     * @param view
     */
    public void onClickPostTextBook(View view) {
        Intent intent = new Intent(this, PostTextbook.class);
        startActivity(intent);
    }

    /**
     * Transfers user to the Profile activity
     * @param view
     */
    public void onClickProfile(View view) {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }

    /**
     * Transfers user to the TextbookSearch activity
     * @param view
     */
    public void onClickSearch(View view){
        Intent intent = new Intent(this, TextbookSearch.class);
        startActivity(intent);
    }//onClickSearch

    /**
     * Transfers user to the Notifications activity
     * @param view
     */
    public void onClickRegistration(View view) {
        Intent intent = new Intent(this, Notifications.class);
        startActivity(intent);
    }

}//class

