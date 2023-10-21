package com.kepler.leaderboardt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.kepler.leaderboardt.adapter.UserAdapter;

public class LeaderboardActivity extends AppCompatActivity {
    private UserViewModel userViewModel;

    private int currentUserRank = 10;

    private View currentUserRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        currentUserRow = findViewById(R.id.currentUserRow);

        recyclerView.setHasFixedSize(true);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        final UserAdapter userAdapter = new UserAdapter(currentUserRank);
        userViewModel.userPagedListLiveData.observe(this, userAdapter::submitList);

        recyclerView.setAdapter(userAdapter);
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                Drawable yourDrawable = getResources().getDrawable(R.drawable.round_corner);
                currentUserRow.findViewById(R.id.rootLayout).setBackground(yourDrawable);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) currentUserRow.getLayoutParams();

                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

                if (currentUserRank > firstVisibleItemPosition && currentUserRank <= lastVisibleItemPosition) {
                    currentUserRow.setVisibility(View.GONE);
                } else {
                    currentUserRow.setVisibility(View.VISIBLE);

                    if (currentUserRank < lastVisibleItemPosition) {
                        layoutParams.removeRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                    } else if (currentUserRank > lastVisibleItemPosition) {
                        layoutParams.removeRule(RelativeLayout.ALIGN_PARENT_TOP);
                        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                    }
                    currentUserRow.setLayoutParams(layoutParams);

                }


            }
        });


    }
}