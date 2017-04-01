package cs246.oliveave;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;

public class AboutUs extends AppCompatActivity {

    public static String FACEBOOK_URL = "https://www.facebook.com/OliveAveBoutique";
    public static String FACEBOOK_PAGE_ID = "OliveAveBoutique";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        DisplayMetrics wSize = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(wSize);

        int width = wSize.widthPixels;
        int height = wSize.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.5));
    }

    public void phoneCall(View view){
        Intent dialPhone = new Intent(Intent.ACTION_DIAL);
        dialPhone.setData(Uri.parse("tel:208-356-9874"));
        startActivity(dialPhone);
    }

    public void emailUs(View view){
        Intent sendEmail = new Intent(Intent.ACTION_SEND);
        sendEmail.setType("plain/text");
        sendEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{"olivemaster@gmail.com"});
        sendEmail.putExtra(Intent.EXTRA_SUBJECT, "test");
        sendEmail.putExtra(Intent.EXTRA_TEXT,"mail body");
        startActivity(Intent.createChooser(sendEmail,""));
    }

    public void facebook(View view){
        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
        String facebookUrl = getFacebookPageURL(this);
        facebookIntent.setData(Uri.parse(facebookUrl));
        startActivity(facebookIntent);
    }

    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) {
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else {
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL;
        }
    }
}
