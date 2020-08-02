package com.example.trivial;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.trivial.data.AnswerListAsynqResponse;
import com.example.trivial.data.QuestionBank;
import com.example.trivial.model.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView questionTextView;          // questionTextView instance
    private TextView questionCounterTextView;   // questionCounterTextView instance
    private Button trueButton;                  // trueButton instance
    private Button falseButton;                 // falseButton instance
    private ImageButton nextButton;             // nextButton instance
    private ImageButton prevButton;             // prevButton instance
    private int currentQuestionIndex = 0;       // currentQuestionIndex instance set to 0
    private List<Question> mQuestionList;       // mQuestionList instance
    private boolean mUserChooseCorrect;


    //onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // add activity to view
        nextButton = findViewById(R.id.next_button);
        prevButton = findViewById(R.id.prev_button);
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        questionTextView = findViewById(R.id.question_textview);
        questionCounterTextView = findViewById(R.id.counter_text);

        //setOnclick to context
        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);

                 mQuestionList = new QuestionBank().getQuestions(new AnswerListAsynqResponse() {
                    @Override
                    public void processFinished(ArrayList<Question> questionArrayList) {
                        questionTextView.setText(questionArrayList.get(currentQuestionIndex).getAnswer());   // setText on questionTextView

                        questionCounterTextView.setText(currentQuestionIndex + " / " + questionArrayList.size() ); // Set questionCounterTextView

                    }
                });
//        Log.d("Main", "onCreate: " + mQuestionList);


    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()){


            case R.id.next_button:
                currentQuestionIndex = (currentQuestionIndex + 1) % mQuestionList.size();  // set currentQuestionIndex for button
                updateQuestion();

                break;

            case R.id.prev_button:                          // add action on prev_button
               if (currentQuestionIndex > 0 )               // if statement to avoid out of bound call
                currentQuestionIndex = (currentQuestionIndex - 1) % mQuestionList.size();
               updateQuestion();

                break;

            case R.id.true_button:
                checkAnswer(true);
                updateQuestion();
                break;

            case R.id.false_button:
                checkAnswer(false);
                updateQuestion();
                break;

        }

    }
                // checkAnswer method
    private void checkAnswer(boolean userChooseCorrect) {
        boolean answerIsTrue = mQuestionList.get(currentQuestionIndex).isAnswerTrue();
        int toastMessageId = 0;
        if (userChooseCorrect == answerIsTrue) {
            fadeView();
            toastMessageId = R.string.correct_answer;  // add resource for correct answer
        } else {
            shakeAnimation();
            toastMessageId = R.string.wrong_answer;
        }
        Toast.makeText(MainActivity.this, toastMessageId,
                Toast.LENGTH_SHORT).show();
    }

    // updateQuestion method
    private void updateQuestion() {

        String question = mQuestionList.get(currentQuestionIndex).getAnswer();   //assign currentQuestionIndex on question object
        questionTextView.setText(question);          // Set question object on questionTextView

        questionCounterTextView.setText(currentQuestionIndex + " / " + mQuestionList.size() ); // Set questionCounterTextView

    }

            //fadeView method
    private void fadeView() {

        final CardView cardView = findViewById(R.id.cardView);                         // cardView object
        AlphaAnimation animation= new AlphaAnimation(1.0f, 0.0f);                // animation object

        animation.setDuration(350);
        animation.setRepeatCount(1);
        animation.setRepeatMode(Animation.REVERSE);

        cardView.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            /**
             * <p>Notifies the start of the animation.</p>
             *
             * @param animation The started animation.
             */
            @Override
            public void onAnimationStart(Animation animation) {
                cardView.setCardBackgroundColor(Color.GREEN);
            }

            /**
             * <p>Notifies the end of the animation. This callback is not invoked
             * for animations with repeat count set to INFINITE.</p>
             *
             * @param animation The animation which reached its end.
             */
            @Override
            public void onAnimationEnd(Animation animation) {
                cardView.setCardBackgroundColor(Color.WHITE);
            }

            /**
             * <p>Notifies the repetition of the animation.</p>
             *
             * @param animation The animation which was repeated.
             */
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

            // shakeAnimation method
    private void shakeAnimation() {
        Animation shake = AnimationUtils.loadAnimation(MainActivity.this,           // LoadAnimationUtils on shake
                R.anim.shake_animation);    //recourse added from anim res folder

        final CardView cardView = findViewById(R.id.cardView);    //cardView activity added on cardView object
        cardView.setAnimation(shake);                       // shake set on carView object

        shake.setAnimationListener(new Animation.AnimationListener() {

            /**
             * <p>Notifies the start of the animation.</p>
             *
             * @param animation The started animation.
             */
            @Override
            public void onAnimationStart(Animation animation) {
                cardView.setCardBackgroundColor(Color.RED);

            }

            /**
             * <p>Notifies the end of the animation. This callback is not invoked
             * for animations with repeat count set to INFINITE.</p>
             *
             * @param animation The animation which reached its end.
             */
            @Override
            public void onAnimationEnd(Animation animation) {
                cardView.setCardBackgroundColor(Color.WHITE);

            }

            /**
             * <p>Notifies the repetition of the animation.</p>
             *
             * @param animation The animation which was repeated.
             */
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
