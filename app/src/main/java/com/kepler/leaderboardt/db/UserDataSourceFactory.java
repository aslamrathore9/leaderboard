package com.kepler.leaderboardt.db;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.kepler.leaderboardt.model.UserModel;

public class UserDataSourceFactory extends DataSource.Factory<Integer, UserModel> {
    private MutableLiveData<UserDataSource> userDataSourceLiveData = new MutableLiveData<>();

    @Override
    public DataSource<Integer, UserModel> create() {
        UserDataSource userDataSource = new UserDataSource();
        userDataSourceLiveData.postValue(userDataSource);
        return userDataSource;
    }
}
