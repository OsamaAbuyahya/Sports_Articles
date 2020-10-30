package os.abuyahya.sportsarticles.repository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import os.abuyahya.sportsarticles.network.ArticleApiService;
import os.abuyahya.sportsarticles.pojo.ArticleResponse;

public class ArticleRepository {

    private ArticleApiService apiService;

    @Inject
    public ArticleRepository(ArticleApiService apiService) {
        this.apiService = apiService;
    }

    public Observable<ArticleResponse> getArticles(){
        return apiService.getArticles();
    }
}
