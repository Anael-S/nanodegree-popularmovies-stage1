package popularmovies.anaels.com;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import popularmovies.anaels.com.api.ApiService;
import popularmovies.anaels.com.api.model.Movie;

public class DetailMovieDialog extends Dialog {

    Activity mActivity;

    private ImageView posterImageView;
    private TextView titleTextView;
    private TextView plotTextView;
    private TextView ratingTextView;
    private TextView releaseDateTextView;
    private ImageButton favoriteButton;

    private Movie mMovie;

    public DetailMovieDialog(Activity a, Movie movie) {
        super(a, R.style.DialogMovie);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        mActivity = a;
        mMovie = movie;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_detail_movie);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        posterImageView = (ImageView) findViewById(R.id.posterImageView);
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        plotTextView = (TextView) findViewById(R.id.plotTextView);
        ratingTextView = (TextView) findViewById(R.id.ratingTextView);
        releaseDateTextView = (TextView) findViewById(R.id.releaseDateTextView);
        favoriteButton = (ImageButton) findViewById(R.id.favoriteButton);

        displayData();
    }

    private void displayData() {
        if (mMovie != null) {
            //Image
            String lUrlImage = ApiService.BASE_URL_IMAGES + mMovie.getPosterPath();
            Picasso.with(mActivity).load(lUrlImage).placeholder(R.drawable.progress_animation).into(posterImageView);
            //Text
            titleTextView.setText(mMovie.getTitle());
            plotTextView.setText(mMovie.getOverview());
            ratingTextView.setText(mActivity.getString(R.string.rating, mMovie.getVoteAverage()));
            releaseDateTextView.setText(mMovie.getReleaseDate());

            //Favorite
            favoriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
    }
}