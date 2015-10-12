package dookim.frograms.com.frograms_test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import data.Card;

/**
 * Created by dookim on 10/12/15.
 */
public class StaggeredRecyclerAdapter extends FrogramsBaseRecyclerAdapter {

    StaggeredRecyclerAdapter(List<Card> cards, Context context) {
        super(context, cards);
    }
    @Override
    public int getItemViewType(int position) {
        if(mCards.get(position).getViewType() == Card.Type.RATING.getTypeVal() && position%3 == 0) {
            return R.layout.rating;
        } else {
            if(mCards.get(position).getViewType() == Card.Type.RATING.getTypeVal()) {
                return R.layout.staggered_rating;
            } else {
                return R.layout.content;
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(viewType, viewGroup, false);
        if(viewType == R.layout.staggered_rating || viewType == R.layout.rating) {
            return new RatingViewHolder(itemView);
        } else if(viewType == R.layout.content) {
            return new ContentViewHolder(itemView);
        }
        throw new IllegalStateException("cannot match viewType");

    }

}
