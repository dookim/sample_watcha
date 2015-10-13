package dookim.frograms.com.frograms_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import data.Card;
import data.CardsContainer;

/**
 * 두번째 fragment simpleRecyclerAdapter를 사용해 data를 binding한다.
 * SimpleRecyclerAdapter를 사용하는 fragment이다.
 * Created by dookim on 10/8/15.
 */
public class ChildFragment2 extends Fragment {

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    SimpleRecyclerAdapter mSimpleRecyclerAdapter;
    List<Card> mCards = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("dookim", "onCreateView2");
        View rootView = inflater.inflate(R.layout.child_fragment2, container, false);
        ButterKnife.bind(this, rootView);

        CardsContainer.getCardContainerOnBackground(new CardsContainer.OnCardContainerCompleted() {
            @Override
            public void onCompleted(CardsContainer cardsContainer) {
                mCards.addAll(cardsContainer.clone());
                mSimpleRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Collections.sort(mCards, new Comparator<Card>() {
            @Override
            public int compare(Card card, Card card2) {
                return card.type.compareTo(card2.type);
            }
        });
        mSimpleRecyclerAdapter = new SimpleRecyclerAdapter(mCards, getActivity());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mSimpleRecyclerAdapter);
        return rootView;
    }


}
