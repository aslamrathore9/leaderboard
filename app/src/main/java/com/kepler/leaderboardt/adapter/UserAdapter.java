package com.kepler.leaderboardt.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.kepler.leaderboardt.R;
import com.kepler.leaderboardt.model.UserModel;

public class UserAdapter extends PagedListAdapter<UserModel, UserAdapter.UserViewHolder> {
    private int currentUserRank; // The integer value you want to pass to the adapter

    public UserAdapter(int currentUserRank) {
        super(DIFF_CALLBACK);
        this.currentUserRank = currentUserRank;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_user_rank, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserModel user = getItem(position);
        if (user != null) {
            holder.userImage.setImageResource(user.getImageResource());
            holder.userName.setText(user.getName());
            holder.userPoint.setText(user.getPoints() + " pts");
            holder.userAmount.setText(holder.itemView.getContext().getString(R.string.rupee_symbol) + user.getAmount());
            holder.playerRank.setText(String.valueOf(user.getPlayerRank()));

            if (user.getPlayerRank() == currentUserRank) {
                holder.rootLayout.setBackground(holder.itemView.getContext().getDrawable(R.drawable.round_corner));
            }else {
                holder.rootLayout.setBackground(holder.itemView.getContext().getDrawable(R.drawable.row_rank_bg));

            }

        }

    }



    public static final DiffUtil.ItemCallback<UserModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<UserModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull UserModel oldItem, @NonNull UserModel newItem) {
            return oldItem.getName().equals(newItem.getName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull UserModel oldItem, @NonNull UserModel newItem) {
            return oldItem.equals(newItem);
        }
    };

    public class UserViewHolder extends RecyclerView.ViewHolder {
        ImageView userImage;
        TextView userName;
        TextView userPoint;
        TextView userAmount;
        TextView playerRank;
        LinearLayout rootLayout;

        public UserViewHolder(View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.userImage);
            userName = itemView.findViewById(R.id.userName);
            userPoint = itemView.findViewById(R.id.userPoint);
            userAmount = itemView.findViewById(R.id.userAmount);
            playerRank = itemView.findViewById(R.id.playerRank);
            rootLayout = itemView.findViewById(R.id.rootLayout);
        }
    }
}
