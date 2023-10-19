package com.example.newsappapi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntertainmentFragment extends Fragment {
    String api_key = "2c3642e577e147abab7fd2a8a6642581";
    ArrayList<ModelClass> list;
    Adapter adapter;
    String country = "in";
    private RecyclerView recyclerViewEntertainment;
    private String category="entertainment";

    public EntertainmentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_entertainment,null);
        recyclerViewEntertainment = v.findViewById(R.id.rvEntertainment);
        list = new ArrayList<>();
        recyclerViewEntertainment.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(list, getContext());
        recyclerViewEntertainment.setAdapter(adapter);

        findNews();

        return v;
    }

    private void findNews() {

        ApiUtilities.getApiInterface().getCategoryNews(
                country,category,100,api_key
        ).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if (response.isSuccessful()) {
                    list.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {

            }
        });

    }

}