package com.example.mad.ui.home;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.mad.R;
import com.example.mad.databinding.ActivityMainBinding;
import com.example.mad.databinding.FragmentHomeBinding;
import com.example.mad.databinding.NewQuestionBottomSheetBinding;
import com.example.mad.question.Question;
import com.example.mad.question.QuestionDB;
import com.example.mad.user.User;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BottomSheetFragment extends BottomSheetDialogFragment {

    public BottomSheetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.new_question_bottom_sheet , container , false);
        EditText title = (EditText) v.findViewById(R.id.question_title);
        EditText question = (EditText) v.findViewById(R.id.question);
        Button saveQuestion = v.findViewById(R.id.save_question);
        saveQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionDB.newQuestion(User.getUserID(),title.getText().toString(),question.getText().toString(),"spaceID");
                dismiss();
            }
        });
        return v;
    }
}