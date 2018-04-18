package com.akhil.mvvmsample.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.akhil.mvvmsample.R;
import com.akhil.mvvmsample.databinding.ItemActorBinding;
import com.akhil.mvvmsample.model.Movies;

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
        ItemActorBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_actor, parent, false);
        return new ItemHolder(binding);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        Movies movies = mMoviesList.get(position);
        holder.binding.setMovies(movies);
        // Picasso.with(holder.itemView.getContext()).load(movies.getImageurl()).into(holder.mActorImg);
    }

    @Override
    public int getItemCount() {
        return mMoviesList.size();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {
        private final ItemActorBinding binding;

        public ItemHolder(ItemActorBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}