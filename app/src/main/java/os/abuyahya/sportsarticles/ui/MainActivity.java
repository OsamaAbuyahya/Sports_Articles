package os.abuyahya.sportsarticles.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;
import os.abuyahya.sportsarticles.R;
import os.abuyahya.sportsarticles.adapter.ArticleAdapter;
import os.abuyahya.sportsarticles.databinding.ActivityMainBinding;
import os.abuyahya.sportsarticles.pojo.Article;
import os.abuyahya.sportsarticles.viewmodel.ArticleViewModel;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements ArticleAdapter.OnItemClickListener {

    private ActivityMainBinding binding;
    private ArticleViewModel viewModel;
    private ArticleAdapter adapter;

    private ArrayList<Article> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(ArticleViewModel.class);

        adapter = new ArticleAdapter(this, list);
        adapter.setOnItemClickListener(this);
        binding.recArticles.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.recArticles.setAdapter(adapter);

        checkNetworkConnection();

        binding.swipeToRefresh.setOnRefreshListener(() -> {
            checkNetworkConnection();
            binding.swipeToRefresh.setRefreshing(false);
        });
    }

    private void checkNetworkConnection(){

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()){

            binding.imageNoConnection.setVisibility(View.INVISIBLE);
            binding.textNoConnection.setVisibility(View.INVISIBLE);
            binding.recArticles.setVisibility(View.VISIBLE);
            getArticles();
        }
        else {
            binding.recArticles.setVisibility(View.INVISIBLE);
            binding.imageNoConnection.setVisibility(View.VISIBLE);
            binding.textNoConnection.setVisibility(View.VISIBLE);
        }

    }
    private void getArticles() {

        viewModel.getArticles();
        viewModel.getArticleList().observe(this, articles -> {

            adapter.setList(articles);
        });
    }


    @Override
    public void onItemClick(Article article, ImageView imageArticle) {

        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("article", article);

        Pair<View,String> p1 = Pair.create(imageArticle, "imageTransaction");
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, p1);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(intent, optionsCompat.toBundle());
        }
        else
            startActivity(intent);
    }
}