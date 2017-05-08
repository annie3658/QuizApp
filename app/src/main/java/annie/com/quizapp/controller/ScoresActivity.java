package annie.com.quizapp.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import annie.com.quizapp.R;
import annie.com.quizapp.adapter.ListviewAdapter;
import annie.com.quizapp.model.User;
import annie.com.quizapp.model.UserSorted;

/**
 * Created by Annie on 28/04/2017.
 */

public class ScoresActivity extends AppCompatActivity {


    private ListView listView;
    private DatabaseReference databaseUsers;
    private List<User> usersList;
    private Button btnStartGame;
    private TextView listSIze;
    private List<UserSorted> sortedList;
    private String language, difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scores_activity);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        databaseUsers = database.getReference("usersScores");
        listView = (ListView) findViewById(R.id.scoresListView);
        btnStartGame = (Button) findViewById(R.id.startGameBtn);
        usersList = new ArrayList<>();
        sortedList=new ArrayList<>();
        listSIze=(TextView) findViewById(R.id.tvScore);
        Bundle b = getIntent().getExtras();
        language=b.getString("Language");
        difficulty=b.getString("Difficulty");

        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoresActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                usersList.clear();
                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                while (items.hasNext()) {
                    DataSnapshot item = items.next();

                    String id,username,language,difficulty,score;
                    username = item.child("username").getValue().toString();
                    id = item.child("userId").getValue().toString();
                    language=item.child("language").getValue().toString();
                    difficulty=item.child("difficulty").getValue().toString();
                    score=item.child("score").getValue().toString();
                    UserSorted aux=new UserSorted(id,username,language,difficulty,Integer.parseInt(score));
                    sortedList.add(aux);
                }

                listSIze.setText(String.valueOf(sortedList.size()));
                Collections.sort(sortedList);
                for(int i=0;i<sortedList.size();i++)
                {
                    if(sortedList.get(i).getLanguage().equals(language)==true && sortedList.get(i).getDifficulty().equals(difficulty)==true ) {
                        Log.e("User", sortedList.get(i).toString());
                        String id, username, language, difficulty, score;
                        username = sortedList.get(i).getUsername();
                        id = sortedList.get(i).getUserId();
                        language = sortedList.get(i).getLanguage();
                        difficulty = sortedList.get(i).getDifficulty();
                        score = String.valueOf(sortedList.get(i).getScore());
                        User user = new User(id, username, language, score, difficulty);
                        usersList.add(user);
                    }

                }
                ListviewAdapter adapter = new ListviewAdapter(ScoresActivity.this, usersList);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();


    }


}