package dookim.frograms.com.frograms_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import data.Card;
import data.CardsContainer;

/**
 * 첫번째 fragment baseadapter를 사용해 data를 binding한다.
 * findviewById대신 butterknife를 사용해 stub코드를 간소화하였다.
 * Created by dookim on 10/8/15.
 */
public class ChildFragment1 extends Fragment {

    List<Card> mCards;
    SimpleBaseAdapter mFrogramsBaseAdapter;

    @Bind(R.id.listview)
    ListView mListView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("dookim", "onCreateView1");
        View rootView = inflater.inflate(R.layout.child_fragment, container, false);
        ButterKnife.bind(this, rootView);

        try {
            //deepcopy를 통해 cards들을 가져온다.
            mCards = CardsContainer.getInstance().clone();
        } catch (IOException e) {
            e.printStackTrace();
            mCards = new ArrayList<>();
        }
        mFrogramsBaseAdapter = new SimpleBaseAdapter(mCards, getActivity());
        mListView.setAdapter(mFrogramsBaseAdapter);

        return rootView;
    }


}
