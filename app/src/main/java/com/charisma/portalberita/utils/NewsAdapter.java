package com.charisma.portalberita.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.charisma.portalberita.R;
import com.charisma.portalberita.model.News;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Bayu Charisma Putra on 4/5/2019.
 */
public class NewsAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<News> newsList;

    public NewsAdapter(Context context, int rowLayout, List<News> newsList){
        super(context, rowLayout);
        this.context = context;
        this.newsList = newsList;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.adapter_news, parent, false);

        TextView tvTitle = rowView.findViewById(R.id.tvTitle);
        TextView tvAuthor = rowView.findViewById(R.id.tvAuthor);
        TextView tvDateCreated = rowView.findViewById(R.id.tvDateCreated);
        ImageView ivPicture = rowView.findViewById(R.id.ivPicture);
        tvTitle.setText(newsList.get(position).getTitle());
        tvAuthor.setText(newsList.get(position).getAuthor());
        tvDateCreated.setText(newsList.get(position).getDateCreatedStr());

        try {
            Picasso.with(context).load(newsList.get(position).getPicture()).into(ivPicture); }
        catch (Exception e) {
            Picasso.with(context).load(R.drawable.no_image).into(ivPicture);
        }

        return rowView;

    }
}
