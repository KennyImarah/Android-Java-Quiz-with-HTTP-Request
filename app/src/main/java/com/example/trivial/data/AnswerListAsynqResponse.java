package com.example.trivial.data;

import com.example.trivial.model.Question;

import java.util.ArrayList;

public interface AnswerListAsynqResponse {

    void processFinished(ArrayList<Question> questionArrayList);
}
