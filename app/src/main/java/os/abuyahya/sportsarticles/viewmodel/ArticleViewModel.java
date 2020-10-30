package os.abuyahya.sportsarticles.viewmodel;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import os.abuyahya.sportsarticles.pojo.Article;
import os.abuyahya.sportsarticles.pojo.ArticleResponse;
import os.abuyahya.sportsarticles.repository.ArticleRepository;

public class ArticleViewModel extends ViewModel {

    private ArticleRepository repository;
    private MutableLiveData<ArrayList<Article>> articleList = new MutableLiveData<>();

    @ViewModelInject
    public ArticleViewModel(ArticleRepository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ArrayList<Article>> getArticleList() {
        return articleList;
    }

    public void getArticles(){

        repository.getArticles()
                .subscribeOn(Schedulers.io())
                .map(new Function<ArticleResponse, ArrayList<Article>>() {
                    @Override
                    public ArrayList<Article> apply(ArticleResponse articleResponse) throws Throwable {
                        return articleResponse.getArticles();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> articleList.setValue(result),
                            error -> Log.e("View Model", error.getMessage()));
    }


}

