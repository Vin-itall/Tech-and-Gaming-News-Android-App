package com.example.atmc.techknowlogy_l;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ATMC on 01-05-2017.
 */




public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ArticleHolder> {


    private ArrayList<Article> mArticles;


    public RecyclerAdapter(ArrayList<Article> articles) {
        mArticles = articles;
    }

    @Override
    public RecyclerAdapter.ArticleHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.car_view_row, parent, false);
        return new ArticleHolder(inflatedView);


    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ArticleHolder holder, int position) {

        Article itemArticle = mArticles.get(position);
        holder.bindArticle(itemArticle);

    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }




    public static class ArticleHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //2
        private ImageView mItemImage;
        private TextView mItemDate;
        private TextView mItemDescription;
        private Article mArticle;

        //3
        private static final String PHOTO_KEY = "ARTICLE";

        //4
        public ArticleHolder(View v) {
            super(v);

            mItemImage = (ImageView) v.findViewById(R.id.item_image);
            mItemDate = (TextView) v.findViewById(R.id.item_date);
            mItemDescription = (TextView) v.findViewById(R.id.item_description);
            v.setOnClickListener(this);
        }


        public void bindArticle(Article article) {
            mArticle = article;
            Picasso.with(mItemImage.getContext()).load(article.getUrl()).into(mItemImage);
            mItemDate.setText(article.getHumanDate());
            mItemDescription.setText(article.getExplanation());
        }

        //5
        @Override
        public void onClick(View v) {
            Log.d("RecyclerView", "CLICK!");
        }
    }


}