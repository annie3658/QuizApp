package annie.com.quizapp.controller;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;

import annie.com.quizapp.R;
import annie.com.quizapp.adapter.SpinnerAdapter;

public class MainActivity extends AppCompatActivity {
    private String[] mLanguages = {"English", "Română"};
    private int[] mImages = {R.drawable.ukicon, R.drawable.roicon};
    private Button mStartButton;
    private Spinner mSpinnerLanguage;
    private String mSelectedLanguage = "English", mSelectedDifficulty;
    private RadioButton mSelectedRadioButton;
    private RadioGroup mRadioGroup;
    private Configuration mConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getWindow().getDecorView().setBackgroundColor(Color.parseColor("#F7E4BE"));

        mStartButton = (Button) findViewById(R.id.startBtn);
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        mConfig = new Configuration();

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSelectedRadioButton = (RadioButton) findViewById(mRadioGroup.getCheckedRadioButtonId());

                if (mSelectedRadioButton != null) {
                    mSelectedDifficulty = mSelectedRadioButton.getText().toString();
                    Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                    intent.putExtra("Language", mSelectedLanguage);
                    intent.putExtra("Difficulty", mSelectedDifficulty);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Please select a difficulty", Toast.LENGTH_SHORT).show();
                }


            }
        });

        mSpinnerLanguage = (Spinner) findViewById(R.id.spinner);

        SpinnerAdapter adapter = new SpinnerAdapter(this, mLanguages, mImages);
        mSpinnerLanguage.setAdapter(adapter);

        mSpinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {

                {
                    switch (arg2) {
                        case 0:
                            mConfig.locale = Locale.ENGLISH;
                            mSelectedLanguage = "English";
                            break;
                        case 1:

                            Locale locale = new Locale("ro");
                            Locale.setDefault(locale);
                            mConfig.locale = locale;
                            mSelectedLanguage = "Romanian";
                            break;
                        default:
                            mConfig.locale = Locale.ENGLISH;
                            mSelectedLanguage = "English";
                            break;
                    }
                    getResources().updateConfiguration(mConfig, null);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

}
