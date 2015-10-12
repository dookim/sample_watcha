package dookim.frograms.com.frograms_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.Card;
import data.ChangedRating;
import de.greenrobot.event.EventBus;

/**
 * Created by dookim on 10/9/15.
 */
public class SimpleBaseAdapter extends BaseAdapter {

    private List<Card> mCards;
    private Context mContext;
    private LayoutInflater mInflater;
    Map<String, RatingViewHolder> mRatingBarMap;

    SimpleBaseAdapter(List<Card> cards, Context context) {
        mCards = cards;
        mContext = context;
        mInflater = LayoutInflater.from(context);
        EventBus.getDefault().register(this);
        mRatingBarMap = new HashMap<>();
    }

    @Override
    public int getCount() {
        return mCards.size();
    }

    @Override
    public Object getItem(int i) {
        return mCards.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return mCards.get(position).getViewType();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ContentViewHolder contentViewHolder = null;
        RatingViewHolder ratingViewHolder = null;
        final Card curCard = mCards.get(position);

        if (convertView == null) {

            if(curCard.type.equals(Card.RATING)) {
                convertView = mInflater.inflate(R.layout.rating, viewGroup, false);
                ratingViewHolder = new RatingViewHolder(convertView);
                convertView.setTag(ratingViewHolder);
            } else if(curCard.type.equals(Card.CONTENT)) {
                convertView = mInflater.inflate(R.layout.content, viewGroup, false);
                contentViewHolder = new ContentViewHolder(convertView);
                convertView.setTag(contentViewHolder);
            }
        }

        if(curCard.type.equals(Card.RATING)) {
            ratingViewHolder = (RatingViewHolder) convertView.getTag();
            RatingViewHolder.SettingHelper.setRatingViewHolder(mContext, ratingViewHolder, curCard);
            mRatingBarMap.put(curCard.movie.title, ratingViewHolder);
        } else if(curCard.type.equals(Card.CONTENT)) {
            contentViewHolder = (ContentViewHolder) convertView.getTag();
            ContentViewHolder.SettingHelper.setContentViewHolderBy(mContext, contentViewHolder, curCard);
        } else {
            throw new IllegalStateException("there is no type in convertview");
        }

        return convertView;
    }

    public void onEvent(ChangedRating changedRating) {
        //현재 화면에 나와있다면
        RatingViewHolder.SettingHelper.changeRatingBarIfExistInMap(mRatingBarMap, changedRating);

    }

}



