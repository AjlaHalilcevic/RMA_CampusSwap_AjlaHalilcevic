package com.ajla.campusswap.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.ajla.campusswap.repository.UserRepository;
import com.google.firebase.auth.FirebaseUser;
public class LoginViewModel extends ViewModel {
    private UserRepository repository;
    private LiveData<FirebaseUser>userLiveData;

    public LoginViewModel() {
        repository = new UserRepository();
        userLiveData = repository.getUserLiveData();
    }
    public void login(String email, String password) {
        repository.login(email, password);
    }
    public LiveData<FirebaseUser>getUserLiveData() {
        return userLiveData;
    }
}
