package com.example.daggerarchtddtrial.activities.mainactivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daggerarchtddtrial.R;
import com.example.daggerarchtddtrial.networking.UserSchema;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.example.daggerarchtddtrial.activities.mainactivity.UserListRecyclerViewAdapter.*;

public class UserListRecyclerViewAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private List<UserSchema> users = new ArrayList<>();
    private Context context;

    @Inject
    public UserListRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    void setUsers(List<UserSchema> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.layout_user_list_item, parent, false);
        return new UserViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserSchema user = users.get(position);
        holder.idText.setText(user.getId().toString());
        holder.titleText.setText(user.getTitle());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView idText;
        TextView titleText;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);

            idText = itemView.findViewById(R.id.idText);
            titleText = itemView.findViewById(R.id.titleText);
        }
    }
}
