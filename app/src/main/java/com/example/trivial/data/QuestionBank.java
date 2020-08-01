package com.example.trivial.data;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.trivial.controller.AppController;
import com.example.trivial.model.Question;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {
    ArrayList<Question>mQuestionArrayList = new ArrayList<>();  //  mQuestionArrayList object
    private String url = " https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";  // url instance

    //getQuestions method
    public List<Question>getQuestions(final AnswerListAsynqResponse callBack){

        //jsonArrayRequest instance object
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url, (JSONArray)
                null,
                new Response.Listener<JSONArray>() {
                    /**
                     * Called when a response is received.
                     *
                     * @param response
                     */
                    @Override
                    public void onResponse(JSONArray response) {
//                        Log.d("JSON Stuff", "onResponse: " + response);

                        for (int i = 0; i < response.length(); i++) {    // transverse through response length

                            try {   //HTTP calls requires try and catch

                                Question question = new Question();    // question object
                                question.setAnswer(response.getJSONArray(i).get(0).toString());       // set index on question
                                question.setAnswerTrue(response.getJSONArray(i).getBoolean(1));

                                //Add question object to list
                                mQuestionArrayList.add(question);
                                //Log.d("Hey there", "onResponse: " + question);

//                                Log.d("JSON", "onResponse: " +  response.getJSONArray(i).get(0));   // call the JSON array index
//                                Log.d("JSON2", "onResponse: " +  response.getJSONArray(i).getBoolean(1));   // call the JSON array index



                            } catch (JSONException e) {  // get result for exception
                                e.printStackTrace();
                            }

                        }

                        if (null != callBack ) callBack.processFinished(mQuestionArrayList);  // check if callBack is null, and set to value of mQuestionArrayList

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);


            return mQuestionArrayList;
        }

    }

