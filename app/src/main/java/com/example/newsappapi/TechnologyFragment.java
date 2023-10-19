package com.example.newsappapi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TechnologyFragment extends Fragment {
    String api_key = "2c3642e577e147abab7fd2a8a6642581";
    ArrayList<ModelClass> list;
    Adapter adapter;
    String country = "in";
    private RecyclerView recyclerViewTechnology;
    private String category="technology";

    public TechnologyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_technology,null);
        recyclerViewTechnology = v.findViewById(R.id.rvTechnology);
        list = new ArrayList<>();
        recyclerViewTechnology.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(list, getContext());
        recyclerViewTechnology.setAdapter(adapter);

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