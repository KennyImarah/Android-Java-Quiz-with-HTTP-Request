package util;

import android.app.Activity;
import android.content.SharedPreferences;

public class Prefs {

    private SharedPreferences mPreferences;   // SharedPreferences instance

    public Prefs(Activity activity) {         // prefs constructor with Activity refenence
       this.mPreferences = activity.getPreferences(activity.MODE_PRIVATE);    // assign activity.getPreference on SharedPrefernce object, to enable access to the activity context
    }
        //saveHighestScore method
    public void saveHighestScore(int score){     // score argument
        int currentScore = score;                // currentScore assign with score

        int lastScore = mPreferences.getInt("h", 0);   // lastScore instance assigned with mPreference with getInt method call, with a default of 0

        if (currentScore > lastScore) {  // check if currentScore is greater than lastScore

            // new highestScore
            mPreferences.edit().putInt("highScoreTextView", currentScore).apply();  //mPreference edit assigns currentScore as new highestScore and apply to save

        }
    }
                // getHighestScore method
        public int getHighestScore(){
                return mPreferences.getInt("highScoreTextView",  0);   // highScoreTextView resource ID added, else default


        }
}
