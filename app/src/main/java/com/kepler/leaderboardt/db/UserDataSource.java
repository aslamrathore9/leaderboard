package com.kepler.leaderboardt.db;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.kepler.leaderboardt.R;
import com.kepler.leaderboardt.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UserDataSource extends PageKeyedDataSource<Integer, UserModel> {
    private static final int PAGE_SIZE = 10;
    private static final int INITIAL_LOAD_SIZE = 30; // Load more data initially

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, UserModel> callback) {
        int pageNumber = 0; // Initial page number
        List<UserModel> data = loadData(pageNumber);

        // Limit the initial load size to INITIAL_LOAD_SIZE
        int initialLoadSize = Math.min(INITIAL_LOAD_SIZE, data.size());

        callback.onResult(data.subList(0, initialLoadSize), null, pageNumber + 1);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, UserModel> callback) {
        // This method is not needed for your use case.
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, UserModel> callback) {
        int pageNumber = params.key;
        List<UserModel> data = loadData(pageNumber);
        callback.onResult(data, pageNumber + 1);
    }

    private List<UserModel> loadData(int pageNumber) {
        List<UserModel> data = new ArrayList<>();
        int start = pageNumber * PAGE_SIZE;
        int end = Math.min(start + PAGE_SIZE, 1000); // Limit to a maximum of 100 items

        for (int i = start; i < end; i++) {
            Log.d("UserDataSource", String.valueOf(i));
            data.add(new UserModel(R.drawable.other_player, "User " + (i + 1), 98000, 500, (i + 1)));
        }

        return data;
    }
}
