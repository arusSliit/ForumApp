package com.example.mad.ui.notifications;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mad.R;
import com.example.mad.space.Space;
import com.example.mad.ui.questions_in_space.QuestionsInSpace;

import java.util.ArrayList;

public class SpaceAdapter extends RecyclerView.Adapter<SpaceAdapter.SpaceViewHolder> {

    ArrayList<Space> list;

    public SpaceAdapter(ArrayList<Space> spaces){
        list = spaces;
    }

    @NonNull
    @Override
    public SpaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.space,parent,false);

        return  new SpaceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SpaceViewHolder holder, int position) {
        Space s = list.get(position);
        holder.title.setText(s.getName());
        holder.description.setText(s.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), QuestionsInSpace.class);
                intent.putExtra("id", s.getSpaceID());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class SpaceViewHolder extends RecyclerView.ViewHolder{

        TextView title, description;

        public SpaceViewHolder(@NonNull View itemView) {

            super(itemView);
            title = itemView.findViewById(R.id.spaceTitle);
            description = itemView.findViewById(R.id.space_description);
        }
    }
}
