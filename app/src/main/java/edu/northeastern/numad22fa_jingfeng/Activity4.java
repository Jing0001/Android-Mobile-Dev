package edu.northeastern.numad22fa_jingfeng;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class Activity4 extends AppCompatActivity {

    RecyclerView recv;
    UserAdapter userAdapter;
    ArrayList<UserData> userLinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
//        ArrayList userList = new ArrayList<>();
        if (savedInstanceState != null && savedInstanceState.containsKey("user_links")) {
//
            userLinks = (ArrayList<UserData>)savedInstanceState.getSerializable("user_links");
        } else {
//            Log.e("mytest", "not serial");
            userLinks = new ArrayList<>();
        }

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

//                Log.e("mytest", String.valueOf(viewHolder.getAdapterPosition()));
//                Log.e("mytest", String.valueOf(userLinks.size()));
                userLinks.remove(viewHolder.getAdapterPosition());
//                Log.e("mytest", String.valueOf(userLinks.size()));
//            userAdapter.removeItem(viewHolder.getAdapterPosition());
//            userAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
//            userLinks.remove(viewHolder.getBindingAdapterPosition());
                userAdapter.notifyDataSetChanged();
            }
        };

        recv = findViewById(R.id.mRecycler);
        FloatingActionButton addsBtn = findViewById(R.id.fab);
        userAdapter = new UserAdapter(userLinks, this);
        recv.setHasFixedSize(true);
        recv.setLayoutManager(new LinearLayoutManager(this));
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recv);
        recv.setAdapter(userAdapter);

        addsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addInfo();
//                TextView textView = findViewById(R.id.mSubTitle);
//                textView.setMovementMethod(LinkMovementMethod.getInstance());
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("user_links", userLinks);
//        Log.e("mytest", "xxxxxx");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getSerializable("user_links");
//        Log.e("mytest", "uyyyyyy");
    }

    private void addInfo() {
//        Toast.makeText(this, "test", Toast.LENGTH_LONG).show();
        LayoutInflater inflter = LayoutInflater.from(this);
        View v = inflter.inflate(R.layout.add_item, null);
        EditText userName = v.findViewById(R.id.userName);
        EditText userLink = v.findViewById(R.id.link);
        AlertDialog.Builder addDialog = new AlertDialog.Builder(this);
        addDialog.setView(v);
        addDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
//                dialog.cancel();
                String name = userName.getText().toString();
                String link = userLink.getText().toString();
                userLinks.add(new UserData(name, link));
                userAdapter.notifyDataSetChanged();

                dialog.dismiss();
                Snackbar mySnackbar = Snackbar.make(recv, "Adding Success", Snackbar.LENGTH_SHORT);
                mySnackbar.show();
            }
        });
        addDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                dialog.dismiss();
                Snackbar mySnackbar = Snackbar.make(recv, "Cancel", Snackbar.LENGTH_SHORT);
                mySnackbar.show();
            }
        });
        addDialog.create();
        addDialog.show();
    }


}