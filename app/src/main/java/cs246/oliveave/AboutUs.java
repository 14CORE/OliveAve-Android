package cs246.oliveave;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.List;

public class AboutUs extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        DisplayMetrics wSize = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(wSize);

        int width = wSize.widthPixels;
        int height = wSize.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.7));
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
        final String url = "facebook.com" + "oliveaveboutique";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));

        final PackageManager packageManager = getPackageManager();
        List<ResolveInfo> list=
                packageManager.queryIntentActivities(i,
                        packageManager.MATCH_DEFAULT_ONLY);
        if(list.size() == 0){
            final String urlBrowser = "www.facebook.com/oliveaveboutique";
            i.setData(Uri.parse(urlBrowser));
        }
        startActivity(i);
    }
}
