package dookim.frograms.com.frograms_test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import data.Card;
import data.ChangedRating;
import data.RatingMapContainer;
import de.greenrobot.event.EventBus;

/**
 * Created by dookim on 10/12/15.
 */
public class RatingViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.rating_imageview)
    ImageView imageView;
    @Bind(R.id.rating_title)
    TextView ratingTitle;
    @Bind(R.id.rating_year)
    TextView ratingYear;
    @Bind(R.id.rating_bar)
    RatingBar ratingBar;

    public RatingViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    /**
     * ratingViewHolder를 채워주는 helper다.
     * 함수내용은
     * 1.card를 사용해 ratingview를 채우는 함수.
     * 2.ratingContainer(싱글턴인 객체)를 사용해서 raingBar를 채우는 함수
     * 3.ratingBar changeListener를 채우는 함수.
     * Created by dookim on 10/12/15.
     */
    public static class SettingHelper {

        public static void setRatingViewHolder(Context context, RatingViewHolder ratingViewHolder, Card card) {
            setRatingViewHolderByCard(context, ratingViewHolder, card);
            setRatingBarByRatingContainer(card.movie.title, ratingViewHolder.ratingBar);
            ratingViewHolder.ratingBar.setOnRatingBarChangeListener(getOnRatingBarChangeListener(card));
        }

        public static RatingBar.OnRatingBarChangeListener getOnRatingBarChangeListener(final Card card) {
            return new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float v, boolean fromUser) {
                    if(fromUser) {
                        EventBus.getDefault().post(new ChangedRating(card.movie.title, v));
                        RatingMapContainer.getRatingValues().put(card.movie.title, v);
                    }
                }
            };

        }

        public static void setRatingViewHolderByCard(Context context, RatingViewHolder ratingViewHolder, Card card) {
            ratingViewHolder.ratingTitle.setText(card.movie.title);
            ratingViewHolder.ratingYear.setText(card.movie.year + "");
            Glide.with(context).load(card.movie.poster).into(ratingViewHolder.imageView);
        }

        public static void setRatingBarByRatingContainer(String title, RatingBar ratingBar) {
            Float rating = RatingMapContainer.getRatingValues().get(title);
            if(rating == null) {
                rating = 0F;
            }
            ratingBar.setRating(rating);
        }

        /**
         * viewHolder의 ratingTitle(타이틀 이름이) 다른 fragment에서 바꾼 영화제목과 같다면 ratingBar를 현재 broadcasted된
         * rating점수로 바꾼다. 라는 의미이다.
         * 이렇게 한 이유는 String에 ratingViewHolder를 캐싱하고 있지만 ratingViewHolder에 있는 view객체들은 재사용되기 때문에 mutable이다.
         * 따라서 ratingViewHolder에 있는 객체가 title에 캐싱되었다고 해당 title에 해당되는 view가 아닐수도 있다는 의미이다.
         * 그러므로, ratingViewHolder에 있는 ratingTitle의 타이틀과 map의 키인 title이 맞다면 원하는 ratingBar의 값을 바꿀수 있는 것이다.
         * @param ratingBarMap
         * @param changedRating
         */
        public static void changeRatingBarIfExistInMap(Map<String, RatingViewHolder> ratingBarMap, ChangedRating changedRating) {
            RatingViewHolder ratingViewHolder=ratingBarMap.get(changedRating.mTitle);
            if(ratingViewHolder != null && changedRating.mTitle.equals(ratingViewHolder.ratingTitle.getText().toString())) {
                ratingViewHolder.ratingBar.setRating(changedRating.mRating);
            }
        }


    }
}