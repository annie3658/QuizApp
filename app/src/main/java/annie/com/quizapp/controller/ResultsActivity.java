package annie.com.quizapp.controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;

import annie.com.quizapp.R;

/**
 * Created by Annie on 28/04/2017.
 */

public class ResultsActivity extends AppCompatActivity {

    private RatingBar mRatingBar;
    private TextView mNumberOfQuestionsAnswered;
    private TextView mScoreRating;
    private Button mRestartGameBtn, mViewScoresBtn;
    private ShareButton mShareBtn;
    private int mScore,mTotalQuestions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_activity);

        FacebookSdk.sdkInitialize(getApplicationContext());

        init();

        mRatingBar.setNumStars(5);
        mRatingBar.setStepSize(0.5f);
        Bundle b = getIntent().getExtras();
        mScore = b.getInt("score");
        mTotalQuestions= b.getInt("totalQs");
        final String language=b.getString("Language");
        final String difficulty=b.getString("Difficulty");
        String username=b.getString("Username");

        Bitmap image=sharePhotoToFacebook(mScore);
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(image)
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();
        mShareBtn.setShareContent(content);

        mRatingBar.setRating((float)(mScore *5)/mTotalQuestions);

        switch (language)
        {
            case "English":
                setEnglish(username, mScore,mTotalQuestions);

                break;
            case "Romanian":
                setRomanian(username, mScore,mTotalQuestions);
                break;
            default:
                setEnglish(username, mScore,mTotalQuestions);
        }

        mRestartGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mViewScoresBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultsActivity.this, ScoresActivity.class);
                intent.putExtra("Language", language);
                intent.putExtra("Difficulty", difficulty);
                startActivity(intent);
            }
        });
    }

    public void init()
    {
        mRatingBar =(RatingBar)findViewById(R.id.ratingBar);
        mRestartGameBtn =(Button)findViewById(R.id.btnStartNewGame);
        mViewScoresBtn =(Button)findViewById(R.id.btnViewScores);
        mNumberOfQuestionsAnswered =(TextView)findViewById(R.id.tvNumberOfQuestionsAnswered);
        mScoreRating =(TextView)findViewById(R.id.tvScoreRating);
        mShareBtn = (ShareButton) findViewById(R.id.btnShare);
    }

    public void setEnglish(String username, int score, int noOfQs)
    {
        mNumberOfQuestionsAnswered.setText(username+" has answered "+mScore+" of "+mTotalQuestions+" questions  correctly!");

        float percentage=(score*100)/noOfQs;

        if (percentage>=80 && percentage<=100){
            mScoreRating.setText("Score is Excellent !");
        }else if(percentage>=70 && percentage<=79){
            mScoreRating.setText("Score is Best");
        }else if(percentage>=60 && percentage<=69){
            mScoreRating.setText("Score is Good");
        }else if(percentage>=50 && percentage<=59){
            mScoreRating.setText("Score is Average!");
        }else if(percentage>=33 && percentage<=49){
            mScoreRating.setText("Score is  Below Average!");
        }else{
            mScoreRating.setText("Score is Poor! You need to practice more!");
        }

    }

    public void setRomanian(String username, int score, int noOfQs)
    {
        mNumberOfQuestionsAnswered.setText(username+" ați răspuns corect la "+mScore+" întrebări din "+mTotalQuestions+" !");

        float percentage=(score*100)/noOfQs;

        if (percentage>=80 && percentage<=100){
            mScoreRating.setText("Scorul este excellent !");
        }else if(percentage>=70 && percentage<=79){
            mScoreRating.setText("Scorul este bun");
        }else if(percentage>=60 && percentage<=69){
            mScoreRating.setText("Scorul este peste medie");
        }else if(percentage>=50 && percentage<=59){
            mScoreRating.setText("Scorul este în medie");
        }else if(percentage>=33 && percentage<=49){
            mScoreRating.setText("Scorul este sub medie");
        }else{
            mScoreRating.setText("Scor mic :( ");
        }
    }



    public Bitmap sharePhotoToFacebook(int sco){
        Bitmap image=null;
        switch(sco)
        {
            case 0:
                image= BitmapFactory.decodeResource(getResources(), R.drawable.stars_0);
                break;
            case 1:
                image=BitmapFactory.decodeResource(getResources(), R.drawable.stars_1);

                break;
            case 2:
                image=BitmapFactory.decodeResource(getResources(), R.drawable.stars_2);

                break;
            case 3:
                image=BitmapFactory.decodeResource(getResources(), R.drawable.stars_3);

                break;
            case 4:
                image=BitmapFactory.decodeResource(getResources(), R.drawable.stars_4);

                break;
            case 5:
                image=BitmapFactory.decodeResource(getResources(), R.drawable.stars_5);

                break;
            case 6:
                image=BitmapFactory.decodeResource(getResources(), R.drawable.stars_6);

                break;
            case 7:
                image=BitmapFactory.decodeResource(getResources(), R.drawable.stars_7);

                break;
            case 8:
                image=BitmapFactory.decodeResource(getResources(), R.drawable.stars_8);

                break;
            case 9:
                image=BitmapFactory.decodeResource(getResources(), R.drawable.stars_9);

                break;
            case 10:
                image=BitmapFactory.decodeResource(getResources(), R.drawable.stars_10);

                break;
           /* default:
                image=BitmapFactory.decodeResource(getResources(), R.drawable.stars_0);*/


        }
        return image;

    }
}

