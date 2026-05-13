package com.ajla.campusswap.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.ajla.campusswap.repository.UserRepository;
import com.google.firebase.auth.FirebaseUser;
public class RegisterViewModel extends ViewModel {
    private UserRepository repository;
    private LiveData<FirebaseUser>userLiveData;

    public RegisterViewModel() {
        repository = new UserRepository();
        userLiveData = repository.getUserLiveData();
    }
    public void register(String email, String password) {
        repository.register(email, password);
    }
    public LiveData<FirebaseUser>getUserLiveData() {
        return userLiveData;
    }
}
