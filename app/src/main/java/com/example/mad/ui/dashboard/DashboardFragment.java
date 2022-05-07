package com.example.mad.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.mad.ui.edit_profile.EditProfile;
import com.example.mad.ui.user_profile.myAnswers.MyAnswers;
import com.example.mad.ui.user_profile.myQuestions.MyQuestionsFragmnet;
import com.example.mad.ui.user_profile.mySpaces.MySpacesFragment;
import com.example.mad.R;
import com.example.mad.databinding.FragmentDashboardBinding;
import com.example.mad.ui.sign_in.SignIn;
import com.example.mad.user.User;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

public class DashboardFragment extends Fragment {

    public static DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this,new DashboardViewModelFactory(getFragmentManager())).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        viewPager = root.findViewById(R.id.viewpager);
        Picasso.get().load(User.getProfileImage()).into(binding.profilePhoto);
        binding.editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(container.getContext(), EditProfile.class);
                startActivity(intent);
            }
        });
        binding.signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance()
                        .signOut(root.getContext())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                Intent signin = new Intent(requireContext(), SignIn.class);
                                signin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(signin);

                            }
                        });
            }
        });

        // setting up the adapter
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());

        // add the fragments
        viewPagerAdapter.add(MyQuestionsFragmnet.newInstance("",""), "My questions");
        viewPagerAdapter.add(MyAnswers.newInstance("",""),"My answers");
        viewPagerAdapter.add(MySpacesFragment.newInstance("",""), "My spaces");

        // Set the adapter
        viewPager.setAdapter(viewPagerAdapter);

        // The Page (fragment) titles will be displayed in the
        // tabLayout hence we need to  set the page viewer
        // we use the setupWithViewPager().
        tabLayout = root.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}