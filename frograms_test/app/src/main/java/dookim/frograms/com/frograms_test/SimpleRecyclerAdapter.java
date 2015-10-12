package dookim.frograms.com.frograms_test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import data.Card;

/**
 * Created by dookim on 10/11/15.
 */


public class SimpleRecyclerAdapter extends FrogramsBaseRecyclerAdapter {

    private int ratingLayout;
    private int contentLayout;

    SimpleRecyclerAdapter(List<Card> cards, Context context) {
        super(context, cards);
        this.ratingLayout = R.layout.rating;
        this.contentLayout = R.layout.content;
    }

    public void setRatingLayout(int ratingLayout) {
        this.ratingLayout = ratingLayout;
    }

    @Override
    public int getItemViewType(int position) {
        return mCards.get(position).getViewType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        if(viewType == Card.Type.CONTENT.getTypeVal()) {
            View itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(contentLayout, viewGroup, false);
            return new ContentViewHolder(itemView);
        } else if(viewType == Card.Type.RATING.getTypeVal()) {
            View itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(ratingLayout, viewGroup, false);
            return new RatingViewHolder(itemView);
        }

        throw new IllegalStateException("cannot find viewHolder");

    }



}


