package com.nepdesk.scanme.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;
import com.nepdesk.scanme.R;
import com.nepdesk.scanme.utils.AdsUtils;



public class PrivacyPolicyActivity extends BaseActivity {
    private AdView adView;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_privacy_policy);
        adView = findViewById(R.id.adView);
        AdsUtils.showGoogleBannerAd(this, adView);

        TextView txt= (TextView) findViewById(R.id.googleplayservices); //txt is object of TextView
        txt.setMovementMethod(LinkMovementMethod.getInstance());
        txt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse("https://www.google.com/policies/privacy/"));
                startActivity(browserIntent);
            }
        });

        TextView txt2= (TextView) findViewById(R.id.AdMob); //txt is object of TextView
        txt2.setMovementMethod(LinkMovementMethod.getInstance());
        txt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse("https://support.google.com/admob/answer/6128543?hl=en"));
                startActivity(browserIntent);
            }
        });

        TextView txt3= (TextView) findViewById(R.id.FirebaseCrashlytics); //txt is object of TextView
        txt3.setMovementMethod(LinkMovementMethod.getInstance());
        txt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse("https://firebase.google.com/support/privacy/"));
                startActivity(browserIntent);
            }
        });

// Removed OneSignal link as the SDK was removed

        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
