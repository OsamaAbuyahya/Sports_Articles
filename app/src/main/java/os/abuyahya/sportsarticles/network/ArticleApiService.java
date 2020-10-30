package os.abuyahya.sportsarticles.network;


import io.reactivex.rxjava3.core.Observable;
import os.abuyahya.sportsarticles.pojo.ArticleResponse;
import retrofit2.http.GET;

public interface ArticleApiService {

    @GET("top-headlines?country=gb&category=sports&apiKey=80277b2cf5e44d8b899066c1d39635c3")
    Observable<ArticleResponse> getArticles();
}
