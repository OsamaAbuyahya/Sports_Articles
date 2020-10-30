package os.abuyahya.sportsarticles.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import os.abuyahya.sportsarticles.R;
import os.abuyahya.sportsarticles.pojo.Article;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private OnItemClickListener clickListener;
    private ArrayList<Article> list;
    private Context context;


    public ArticleAdapter() {
    }

    public ArticleAdapter(Context context, ArrayList<Article> list) {

        this.context = context;
        this.list = list;
    }

    public interface OnItemClickListener{
        void onItemClick(Article article, ImageView imageArticle);
    }

    public void setOnItemClickListener(OnItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imageArticle;
        TextView textTitle, textAuthor;

        public ArticleViewHolder(@NonNull View itemView, OnItemClickListener clickListener) {
            super(itemView);

            imageArticle = itemView.findViewById(R.id.image_article);
            textTitle = itemView.findViewById(R.id.text_title);
            textAuthor = itemView.findViewById(R.id.text_author);

            itemView.setOnClickListener(view -> clickListener.onItemClick(list.get(getAdapterPosition()), imageArticle));
        }
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_article, parent, false);

        return new ArticleViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getUrlToImage())
                .placeholder(R.drawable.ic_launcher_background).into(holder.imageArticle);

        holder.textTitle.setText(list.get(position).getTitle());
        holder.textAuthor.setText(list.get(position).getAuthor());

    }

    public void setList(ArrayList<Article> list) {

        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

}
