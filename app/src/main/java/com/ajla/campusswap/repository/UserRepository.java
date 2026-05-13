package com.ajla.campusswap.repository;

import androidx.lifecycle.MutableLiveData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class UserRepository {
    private FirebaseAuth mAuth;
    private MutableLiveData<FirebaseUser>userLiveData;

    public UserRepository() {
        mAuth = FirebaseAuth.getInstance();
        userLiveData = new MutableLiveData<>();
    }
    public void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                userLiveData.postValue(mAuth.getCurrentUser());
            } else {
                userLiveData.postValue(null);
            }
        });
    }

    public void register(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                userLiveData.postValue(mAuth.getCurrentUser());
            } else {
                userLiveData.postValue(null);
                if (task.getException() != null) {
                    android.util.Log.e("FIREBASE_ERROR", task.getException().getMessage());
                }
                userLiveData.postValue(null);
            }
        });
    }
    public MutableLiveData<FirebaseUser> getUserLiveData() {
        return userLiveData;
    }
}
