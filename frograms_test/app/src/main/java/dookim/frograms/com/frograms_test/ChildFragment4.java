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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import data.Card;
import data.CardsContainer;

/**
 * StaggeredRecycler 사용해 data를 binding한다.
 * Created by dookim on 10/12/15.
 */
public class ChildFragment4 extends Fragment {

    List<Card> mCards = new ArrayList<>();

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    StaggeredRecyclerAdapter mSimpleRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("dookim", "onCreateView4");
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

        mSimpleRecyclerAdapter = new StaggeredRecyclerAdapter(mCards, getActivity());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);

        /**
         * 3으로 나눠지는 경우는 2개의 span을 나머지의 경우는 1공간을 차지하도록 만든다.
         * 그러면
         * 1개
         * 2개
         * 1개
         * 2개
         * 와 같은 형식으로 grid가 나타나게 된다.
         */
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position % 3 == 0 ? 2 : 1;
            }
        });

        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mSimpleRecyclerAdapter);
        return rootView;
    }

}
