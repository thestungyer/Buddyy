package com.example.buddyy;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewHome extends RecyclerView.Adapter<RecyclerViewHome.ViewHolder> {

    private LayoutInflater mInflater;
    private List<String> names;
    private List<Integer> pictures;
    private List<String> titles;
    private List<String> descriptions;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    RecyclerViewHome(Context context, List<String> names, List<Integer> pictures, List<String> titles, List<String> descriptions) {
        this.mInflater = LayoutInflater.from(context);
        this.names = names;
        this.pictures = pictures;
        this.titles = titles;
        this.descriptions = descriptions;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_home, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = names.get(position);
        holder.name.setText(name);
        holder.pic.setImageResource(pictures.get(position));
        String title = titles.get(position);
        holder.title.setText(title);
        holder.description.setText(descriptions.get(position));
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return names.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        CircleImageView pic;
        TextView title;
        TextView description;

        // TODO: add the comments thing later (when connected to the db).
        //List<String> comments;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_home);
            pic = itemView.findViewById(R.id.profile_image_home);
            description = itemView.findViewById(R.id.home_text_post);
            title = itemView.findViewById(R.id.title_home);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return names.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}