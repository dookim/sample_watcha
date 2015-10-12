package dookim.frograms.com.frograms_test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import data.Card;

/**
 * Created by dookim on 10/12/15.
 */
public class ContentViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.content_imageview)
    ImageView imageView;
    @Bind(R.id.content_title)
    TextView title;

    public ContentViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public static class SettingHelper {
        public static void setContentViewHolderBy(Context mContext,  ContentViewHolder contentViewHolder, Card curCard) {
            Glide.with(mContext).load(curCard.movie.stillcut).into(contentViewHolder.imageView);
            contentViewHolder.title.setText(curCard.movie.title);
        }
    }
}
