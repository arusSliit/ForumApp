package com.example.mad.ui.user_profile.mySpaces;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mad.R;
import com.example.mad.space.Space;
import com.example.mad.ui.dashboard.DashboardViewModel;
import com.example.mad.user_profile.UserProfileDB;


import java.util.ArrayList;

public class MySpaceAdapter extends RecyclerView.Adapter<MySpaceAdapter.MySpaceViewHolder> {

    ArrayList<Space> list;
    FragmentManager manager;

    public MySpaceAdapter(ArrayList<Space> spaces,FragmentManager m){
        manager = m;
        list = spaces;
    }

    @NonNull
    @Override
    public MySpaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_space,parent,false);

        return  new MySpaceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MySpaceViewHolder holder, int position) {
        Space s = list.get(position);
        holder.title.setText(s.getName());
        holder.description.setText(s.getDescription());
        MySpaceAdapter adapter = this;
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DashboardViewModel.deleteSpace(s.getSpaceID());
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateMySpace frag = new UpdateMySpace(s.getSpaceID(),adapter);
                frag.show(manager,"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MySpaceViewHolder extends RecyclerView.ViewHolder{

        TextView title, description;
        ImageButton delete;

        public MySpaceViewHolder(@NonNull View itemView) {

            super(itemView);
            title = itemView.findViewById(R.id.s_title);
            description = itemView.findViewById(R.id.description);
            delete = itemView.findViewById(R.id.delete_my_space);
        }
    }
}

