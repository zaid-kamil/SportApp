package com.example.sportapp;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportapp.databinding.FragmentFirstBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private List<Sport> mSportsData;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSportsData = new ArrayList<>();
        CharSequence[] titleArray = getActivity().getResources().getTextArray(R.array.sports_titles);
        CharSequence[] infoArray = getActivity().getResources().getTextArray(R.array.sports_info);
        TypedArray imgArray = getActivity().getResources().obtainTypedArray(R.array.sports_images); //  A TypedArray allows you to store an array of other XML resources. Using a TypedArray

        for (int i = 0; i < titleArray.length; i++) {
            mSportsData.add(new Sport(titleArray[i], infoArray[i], imgArray.getResourceId(i, 0)));
        }
        binding.sportRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        SportAdapter adapter = new SportAdapter(getActivity(), mSportsData);
        binding.sportRecycler.setAdapter(adapter);
        imgArray.recycle(); // remove images from memory

        /* swipeable cards */
        int swipeDirs = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int dragDirs = ItemTouchHelper.UP | ItemTouchHelper.DOWN|ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        ItemTouchHelper touchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {
            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(mSportsData,from,to);
                adapter.notifyItemMoved(from,to);
                return true;
            }

            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
                mSportsData.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });

        touchHelper.attachToRecyclerView(binding.sportRecycler);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}