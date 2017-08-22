package com.example.anhtu.demologingamil;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.squareup.picasso.Picasso;

public class Activity2 extends AppCompatActivity  implements GoogleApiClient.OnConnectionFailedListener{
    private static final String LOG_TAG = Activity2.class.getSimpleName();

    // ánh xạ các component
    TextView txtName, txtEmail;
    ImageView imdHinh;
    Button btnLogout;
    private GoogleApiClient mGoogleApiClient;
    private GoogleSignInAccount acct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        txtEmail = (TextView)findViewById(R.id.txtEmail);
        txtName = (TextView)findViewById(R.id.txtName);
        imdHinh = (ImageView)findViewById(R.id.imageView);
        btnLogout = (Button)findViewById(R.id.btnLogout);

        Intent intent = getIntent();
        acct = intent.getParcelableExtra("SignInData");
        Log.e(LOG_TAG, acct.toString());
        mGoogleApiClient = intent.getParcelableExtra("Google");


//        String extraEmail = getIntent().getStringExtra("Email");
//        txtEmail.setText(extraEmail);
//        String  extraName = getIntent().getStringExtra("Name");
//        txtName.setText(extraName);
//        String extraImage = getIntent().getStringExtra("Image");
//        Picasso.with(this).load(extraImage).into(imdHinh);
//        //Glide.with(this).load(extraImage).into(imdHinh);
//
//        btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Activity2.this, MainActivity.class));
//                finish();
//            }
//        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e(LOG_TAG, "On Connection failed");
    }


    // button fuction run
//    public void onClick(View v) {
//        switch (v.getId()){
//
//            case R.id.btnLogout:
//                signOut();
//                break;
//        }
//    }
}
