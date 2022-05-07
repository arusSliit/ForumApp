package com.example.mad.ui.user_profile.myAnswers;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mad.R;
import com.example.mad.databinding.FragmentAnswersBinding;
import com.example.mad.databinding.FragmentMyAnswersBinding;
import com.example.mad.ui.answer.AnswerViewModel;
import com.example.mad.ui.dashboard.DashboardFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyAnswers#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyAnswers extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentMyAnswersBinding binding;
    private RecyclerView recyclerView;

    public MyAnswers() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyAnswers.
     */
    // TODO: Rename and change types and number of parameters
    public static MyAnswers newInstance(String param1, String param2) {
        MyAnswers fragment = new MyAnswers();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyAnswersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = root.findViewById(R.id.myAnswerList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerView.setAdapter(DashboardFragment.dashboardViewModel.getaAdapter());
        return root;
    }
}