package com.example.trivial;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trivial.data.AnswerListAsynqResponse;
import com.example.trivial.data.QuestionBank;
import com.example.trivial.model.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;
    private TextView questionCounterTextView;
    private Button trueButton;
    private Button falseButton;
    private ImageButton nextButton;
    private ImageButton prevButton;
    private int currentQuestionIndex = 0;
    private List<Question> mQuestionList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


                List<Question> questionList = new QuestionBank().getQuestions(new AnswerListAsynqResponse() {
                    @Override
                    public void processFinished(ArrayList<Question> questionArrayList) {

                    }
                });
        Log.d("Main", "onCreate: " + questionList);


    }
}
