package edu.northeastern.numad22fa_jingfeng;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private final List<UserData> userData;
    private final Context context;
    public UserAdapter(List<UserData> userData, Context context) {
        this.context = context;
        this.userData = userData;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.name.setText(userData.get(position).getName());
        holder.link.setText(String.valueOf(userData.get(position).getLink()));
        holder.itemView.setOnClickListener(view -> {
            Toast.makeText(context, userData.get(position).getName(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return userData.size();
    }
}
