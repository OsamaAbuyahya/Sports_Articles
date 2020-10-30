package os.abuyahya.sportsarticles.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import os.abuyahya.sportsarticles.R;
import os.abuyahya.sportsarticles.databinding.ActivityDetailBinding;
import os.abuyahya.sportsarticles.pojo.Article;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this ,R.layout.activity_detail);

        Article article = getIntent().getParcelableExtra("article");

        assert article != null;
        loadArticleData(article);

        binding.iconBack.setOnClickListener(this);
    }

    private void loadArticleData(Article article) {

        Glide.with(this).load(article.getUrlToImage())
                .placeholder(R.drawable.ic_launcher_background).into(binding.imageArticleDetail);
        binding.textTitleDetail.setText(article.getTitle());
        binding.textAuthorDetail.setText(article.getAuthor());
        binding.textDateDetail.setText(article.getPublishedAt());
        binding.textDescriptionDetail.setText(article.getDescription());
    }

    @Override
    public void onClick(View view) {

        onBackPressed();
    }
}