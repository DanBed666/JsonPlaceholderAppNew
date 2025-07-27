package com.example.jsonplaceholderappnew;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class UserViewModel
{
    UserRepository userRepository;

    public MutableLiveData<User> getUser(int userID)
    {
        userRepository = new UserRepository();
        return userRepository.getUser(userID);
    }
}
