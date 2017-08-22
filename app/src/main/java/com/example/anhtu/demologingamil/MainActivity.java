package com.example.anhtu.demologingamil;

import android.content.Intent;
import android.icu.text.StringSearch;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    int RC_SIGN_IN = 001;
    // ánh xạ các component
//    TextView txtName, txtEmail;
//    ImageView imdHinh;
//    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Yêu cầu người dùng cung cấp các thông tin cơ bản của người dùng như
        // Email, tên, hình ảnh
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Kết nối với google api clinet
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

//        txtEmail = (TextView)findViewById(R.id.txtEmail);
//        txtName = (TextView)findViewById(R.id.txtName);
//        imdHinh = (ImageView)findViewById(R.id.imageView);
//        btnLogout = (Button)findViewById(R.id.btnLogout);

        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
                Intent intent = new Intent(MainActivity.this,Activity2.class);
                startActivity(intent);

            }
        });
//        findViewById(R.id.btnLogout).setOnClickListener(this);

    }

    // khi kết nời fail thì sẽ in ra cái này thông báo cho người dùng biết
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("Failed ", connectionResult +"");
    }

    // function sign-in khi đăng nhập thành công
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
        Log.d("Success", mGoogleApiClient.isConnected()+"");
    }


    // include basic information
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }

    }

    // lấy thông tin in ra màn hình
    private void handleSignInResult(GoogleSignInResult result) {

        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            Intent intent = new Intent(MainActivity.this, Activity2.class);
//            intent.putExtra("Name",acct.getDisplayName().toString());
//            intent.putExtra("Email",acct.getEmail().toString());
////            txtEmail.setText(acct.getEmail().toString());
////            txtName.setText(acct.getEmail().toString());
////            Picasso.with(this).load(acct.getPhotoUrl()).into(imdHinh);
//            intent.putExtra("Image", acct.getPhotoUrl());
            intent.putExtra("SignInData", acct);
            intent.putExtra("Google", (Parcelable) mGoogleApiClient);
            startActivity(intent);

        } else {

        }
    }
    public GoogleApiClient getmGoogleApiClient(){
        return mGoogleApiClient;
    }
    @Override
    protected void onStop() {
        super.onStop();
    }

    // logout gamil
    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {

                    }
                });
    }


    // button fuction run
    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.sign_in_button:
//                signIn();
//                break;
//            case R.id.btnLogout:
//                signOut();
//                break;
        }
    }

}
