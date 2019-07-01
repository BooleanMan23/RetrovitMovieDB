package com.example.retrofitmoviedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    public static String BASE_URL = "https://api.themoviedb.org";
    public static int PAGE = 1;
    public static  String API_KEY = "b9239506432ea4c54f8b16f4a3679008";
    public static   String LANGUAGE = "en-us";
    public static  String CATEGORY = "popular";
    public static  String POSTER_BASE_URL = "https://image.tmdb.org/t/p/w185";
    private TextView myTextView;
    private ProgressBar progressBar;

    public MyAdapter movieAdapter;

    private RecyclerView movieRecyclerView;


    ArrayList<Movie> movieList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieRecyclerView  = findViewById(R.id.rv_movie);


        movieRecyclerView.setHasFixedSize(true);
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


        progressBar = findViewById(R.id.progressBar);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<MovieResults> call = apiInterface.listOfMovie(CATEGORY, API_KEY, LANGUAGE, PAGE);
        call.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                MovieResults results = response.body();
                List<MovieResults.ResultsBean> listOfMovies = results.getResults();

                for (MovieResults.ResultsBean resultsBean : listOfMovies){
                    String title = resultsBean.getTitle();
                    String overview = resultsBean.getOverview();
                    String releaseDate = resultsBean.getRelease_date();
                    String posterLink = POSTER_BASE_URL+resultsBean.getPoster_path();
                    Movie movie = new Movie(title, overview, posterLink, releaseDate);
                    Log.i("data movie judul", movie.getTitle());
                    Log.i("data movie overview", movie.getOverview());
                    Log.i("data movie releaseDate", movie.getReleaseDate());
                    Log.i("data movie posterLink", movie.getPosterLink());
                    movieList.add(movie);

                }

                movieAdapter = new MyAdapter(movieList, MainActivity.this);
                movieRecyclerView.setAdapter(movieAdapter);

                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
