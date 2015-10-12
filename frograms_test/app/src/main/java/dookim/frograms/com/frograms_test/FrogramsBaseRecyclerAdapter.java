package dookim.frograms.com.frograms_test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.Card;
import data.ChangedRating;
import de.greenrobot.event.EventBus;

/**
 * 각 recyclerView는 card의 리스트와 mRatingBarMap을 갖고 있다.
 * ratingBarMap은 영화와 RatingViewHolder를 연결시켜주는 역할을 하고있으며
 * 현재 fragment에서 왼쪽이나 오른쪽 fragment가 rating을 했다면 EventBus를 통해
 * subscribe하고 만약 현재 ratingViewHolder를 mRatingBarMap에 캐싱하고 있다면
 * 데이터를 바꿔주는 역할을 한다.
 * 쉽게 말하면, 현재 화면에 보여지는 부분을 왼쪽이나 오른쪽 fragment에서 rating을 바꾼 경우
 * 점수를 수정해도록 한다.
 * Created by dookim on 10/12/15.
 */
public abstract class FrogramsBaseRecyclerAdapter extends RecyclerView.Adapter {
    protected List<Card> mCards;
    protected Map<String, RatingViewHolder> mRatingBarMap;
    protected Context mContext;

    FrogramsBaseRecyclerAdapter(Context context, List<Card> cards) {
        this.mCards = cards;
        this.mContext = context;
        this.mRatingBarMap = new HashMap<>();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Card curCard = mCards.get(position);
        if(holder instanceof RatingViewHolder) {
            RatingViewHolder ratingViewHolder = (RatingViewHolder) holder;
            RatingViewHolder.SettingHelper.setRatingViewHolder(mContext, ratingViewHolder, curCard);
            mRatingBarMap.put(curCard.movie.title, ratingViewHolder);
        } else {
            ContentViewHolder contentViewHolder = (ContentViewHolder) holder;
            ContentViewHolder.SettingHelper.setContentViewHolderBy(mContext, contentViewHolder, curCard);
        }
    }


    public void onEvent(ChangedRating changedRating) {
        Log.e("dookim", "onEvent2");
        RatingViewHolder.SettingHelper.changeRatingBarIfExistInMap(mRatingBarMap, changedRating);
    }


    @Override
    public int getItemCount() {
        return mCards.size();
    }


}
