package com.example.tasteandrate.Auth;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;

public abstract class Firebase {

    private static final String TAG = "EmailPassword";

    // Declare_auth
    private FirebaseAuth mAuth;

    public void onCreate(Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();

    }
    @Override
    public void onStart(){
        super.onStart();
        //Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void createAccount(String email, String password){
        Log.d(Tag, "createAccount:" + email);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Log.d(TAG, "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });
    }

    private void signIn(String email, String password){
        Log.d(TAG, "signIn:" + email);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
             if(task.isSuccessful()){
                 Log.d(TAG, "signInWithEmail: success");
                 FirebaseUser user = mAuth.getCurrentUser();
                 updateUI(user);
             }
             else {
                 Log.w(TAG, "signInWithEmail:failure"), task.getException());
                Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                 Toast.LENGTH_SHORT) .show();
                updateUI(null);
             }
            }
        });
    }

    private void getCurrentUser(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            String name = user.getDisplayName();
            String email = user.getEmail();

            // Check if user's if email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }
    }
}
