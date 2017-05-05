package annie.com.quizapp.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import annie.com.quizapp.R;
import annie.com.quizapp.adapter.ListviewAdapter;
import annie.com.quizapp.model.User;

/**
 * Created by Annie on 28/04/2017.
 */

public class ScoresActivity extends AppCompatActivity {


    private ListView listView;
    private DatabaseReference databaseUsers;
    private List<User> usersList, mediumSores, easyScores,hardScores;
    private Button btnStartGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scores_activity);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        databaseUsers = database.getReference("usersScores");
        listView = (ListView) findViewById(R.id.scoresListView);
        btnStartGame = (Button) findViewById(R.id.startGameBtn);
        usersList = new ArrayList<>();
        mediumSores=new ArrayList<>();
        easyScores=new ArrayList<>();
        hardScores=new ArrayList<>();

        Bundle b = getIntent().getExtras();
        final String language=b.getString("Language");
        final String difficulty=b.getString("Difficulty");

        Toast.makeText(this, "easy:"+easyScores.size(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "medium:"+mediumSores.size(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "hard:"+hardScores.size(), Toast.LENGTH_SHORT).show();

        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoresActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                usersList.clear();
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    User user = userSnapshot.getValue(User.class);
                    usersList.add(user);
                }

                for(int i=0;i<usersList.size();i++)
                {
                    if(usersList.get(i).getDifficulty().equals("easy")==true)
                    {
                        easyScores.add(usersList.get(i));
                    }
                    if(usersList.get(i).getDifficulty().equals("medium")==true)
                    {
                        mediumSores.add(usersList.get(i));
                    }
                    if(usersList.get(i).getDifficulty().equals("hard")==true)
                    {
                        hardScores.add(usersList.get(i));
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


}
