package com.example.jsonplaceholderappnew;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class UserViewModel
{
    UserRepository userRepository;

    public UserViewModel()
    {
        userRepository = new UserRepository();
    }

    public MutableLiveData<User> getUser(int userID)
    {
        return userRepository.getUser(userID);
    }

    public void createNewUser(User user)
    {
        userRepository.createNewUser(user);
    }
}
