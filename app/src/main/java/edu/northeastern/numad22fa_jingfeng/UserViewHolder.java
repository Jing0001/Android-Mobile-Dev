package edu.northeastern.numad22fa_jingfeng;

import android.content.Intent;
import android.net.Uri;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.TextView;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import org.jetbrains.annotations.NotNull;

public class UserViewHolder extends RecyclerView.ViewHolder {

    public TextView name;
    public TextView link;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        this.name = itemView.findViewById(R.id.mTitle);
        this.link = itemView.findViewById(R.id.mSubTitle);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.e("mytest", "AaAAAAAA");
                String url = link.getText().toString();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                itemView.getContext().startActivity(i);
            }
        });
//        link.setMovementMethod(LinkMovementMethod.getInstance());
//        Log.e("mytest", "here1111");
    }
}
