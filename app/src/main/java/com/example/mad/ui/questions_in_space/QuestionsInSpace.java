package com.example.mad.ui.questions_in_space;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.mad.R;
import com.example.mad.databinding.ActivityQuestionsInSpaceBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class QuestionsInSpace extends AppCompatActivity {

    public static String spaceID;
    private QuestionsInSpaceViewModel questionsInSpaceViewModel;
    private ActivityQuestionsInSpaceBinding binding;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        spaceID = intent.getStringExtra("id");
        questionsInSpaceViewModel =
                new ViewModelProvider(this,new QuestionsInSpaceViewModelFactory(spaceID)).get(QuestionsInSpaceViewModel.class);

        binding = ActivityQuestionsInSpaceBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        setContentView(root);

        final FloatingActionButton newQuestionInSpaceButton = binding.newQuestionInSpaceButton;
        newQuestionInSpaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewQuestionInSpace frag= new NewQuestionInSpace();
                frag.show(getSupportFragmentManager(),"");
            }
        });
        recyclerView =(RecyclerView) root.findViewById(R.id.questionInSpaceList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.Adapter adapter = questionsInSpaceViewModel.getAdapter();
        recyclerView.setAdapter(adapter);
    }
}