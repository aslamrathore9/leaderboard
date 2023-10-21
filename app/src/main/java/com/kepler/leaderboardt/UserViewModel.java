package com.kepler.leaderboardt;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.kepler.leaderboardt.db.UserDataSourceFactory;
import com.kepler.leaderboardt.model.UserModel;

public class UserViewModel extends ViewModel {
    LiveData<PagedList<UserModel>> userPagedListLiveData;

    public UserViewModel() {
        UserDataSourceFactory factory = new UserDataSourceFactory();
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(10) // Number of items per page
                .build();

        userPagedListLiveData = new LivePagedListBuilder<>(factory, config)
                .build();
    }
}
