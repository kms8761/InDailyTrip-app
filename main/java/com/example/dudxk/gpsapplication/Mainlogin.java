package com.example.dudxk.gpsapplication;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.common.api.Scope;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.protobuf.NullValue;

public class Mainlogin extends AppCompatActivity implements
        View.OnClickListener,
        GoogleApiClient.OnConnectionFailedListener{
    EditText email_edittext;
    EditText password_edittext;
    String TAG = "Mainlogin";

    String stEMAIL;
    String stPassword;

    private GoogleApiClient mGoogleApiClient;
    private GoogleSignInClient googleSignInClient;
    private FirebaseAuth firebaseAuth;
    private SignInButton buttonGoogle;
    private int RC_SIGN_IN = 1000;
    private final String tag = "TAG";
    private TextView mStatusTextView;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        buttonGoogle = findViewById(R.id.google_sign_in_button);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        buttonGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

///editext에 글을 썻을 떄 그걸 가져오는거
       email_edittext = (EditText)findViewById(R.id.email_edittext);
       password_edittext = (EditText)findViewById(R.id.password_edittext);


        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

       Button email_register_button = (Button)findViewById(R.id.email_register_button);

        email_register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String stEMAIL;
                stEMAIL= email_edittext.getText().toString();
                String stPassword;
                stPassword = password_edittext.getText().toString();
                if(!stEMAIL.isEmpty()&& !stPassword.isEmpty()) {
                    registerUser(stEMAIL, stPassword);
                }
                else{
                    Toast.makeText(Mainlogin.this,"id, password is not correct",Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(Mainlogin.this, stEMAIL+","+stPassword, Toast.LENGTH_SHORT).show();




            }
        });

        Button email_login_button = (Button)findViewById(R.id.email_login_button);
        email_login_button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String stEMAIL = email_edittext.getText().toString();
                String stPassword =password_edittext.getText().toString();
                userLogin(stEMAIL,stPassword);
            }
        });
    }
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }

    }
        public void registerUser(String email, String password) {

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                            Toast.makeText(Mainlogin.this, "Authentication success",
                                    Toast.LENGTH_SHORT).show();
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Toast.makeText(Mainlogin.this, "Authentication failed",
                                        Toast.LENGTH_SHORT).show();
                            }
//toast 로 뜨게하는거 로그로 보는거

                            // [END_EXCLUDE]
                        }
                    });
        }
        private void userLogin(String email, String password){

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                            // the auth state listener will be notified and logic to handle the
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Log.w(TAG, "signInWithEmail:failed", task.getException());
                                Toast.makeText(Mainlogin.this, "Authentication failed",
                                        Toast.LENGTH_SHORT).show();
                            } else
                            {
                                Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                                                               startActivity(i);
                            }

                            // ...
                        }
                    });

        }

    @Override
    public void onClick(View v) {
        signIn();
    }

    private void signIn(){
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent,RC_SIGN_IN);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(getApplicationContext(), ""+connectionResult, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // 구글 로그인 성공
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {

            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 로그인 성공
                            Toast.makeText(Mainlogin.this,"success_login", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // 로그인 실패
                            Toast.makeText(Mainlogin.this,"failed_login", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}




