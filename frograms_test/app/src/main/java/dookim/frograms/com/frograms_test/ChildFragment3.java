package dookim.frograms.com.frograms_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import data.Card;
import data.CardsContainer;

/**
 * mSimpleRecyclerAdapter 사용해 data를 binding한다.
 * gridlayout을 사용해 격자로 보여주도록 한다.
 * Created by dookim on 10/8/15.
 */
public class ChildFragment3 extends Fragment{
    List<Card> mCards;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    SimpleRecyclerAdapter mSimpleRecyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("dookim", "onCreateView3");
        View rootView = inflater.inflate(R.layout.child_fragment2, container, false);
        ButterKnife.bind(this, rootView);

        try {
            mCards = CardsContainer.getInstance().clone();
        } catch (IOException e) {
            e.printStackTrace();
            mCards = new ArrayList<>();
        }

        mSimpleRecyclerAdapter = new SimpleRecyclerAdapter(mCards, getActivity());
        mSimpleRecyclerAdapter.setRatingLayout(R.layout.staggered_rating);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);


        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mSimpleRecyclerAdapter);
        return rootView;
    }

    @Override
    public void onDestroy() {
        Log.e("dookim", "detroy3");
        super.onDestroy();
    }

}
