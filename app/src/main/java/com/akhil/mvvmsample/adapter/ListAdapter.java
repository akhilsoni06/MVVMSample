package com.akhil.mvvmsample.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akhil.mvvmsample.R;
import com.akhil.mvvmsample.model.Movies;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Akhil on 09-04-2018.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemHolder> {

    private List<Movies> mMoviesList;

    public ListAdapter(List<Movies> list) {
        this.mMoviesList = list;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_actor, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        Movies movies = mMoviesList.get(position);
        holder.mActorName.setText(movies.getName());
        holder.mBio.setText(Html.fromHtml(movies.getBio()));
        Picasso.with(holder.itemView.getContext()).load(movies.getImageurl()).into(holder.mActorImg);
    }

    @Override
    public int getItemCount() {
        return mMoviesList.size();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {
        private TextView mActorName;
        private TextView mBio;
        private ImageView mActorImg;

        public ItemHolder(View itemView) {
            super(itemView);
            mActorName = (TextView) itemView.findViewById(R.id.textName);
            mBio = (TextView) itemView.findViewById(R.id.textBio);
            mActorImg = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}