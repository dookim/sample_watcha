package data;

/**
 * event bus를 통해 현재 사용자가 바꾼 변화된 레이팅정보이다.
 * caching된 fragment(현재 사용자가 보고있는 왼쪽과 오른쪽의 fragment)에게 해당 정보로 바꾸라고 통지한다.
 * Created by dookim on 10/12/15.
 */
public class ChangedRating {
    public String mTitle;
    public float mRating;

    public ChangedRating(String title, float rating) {
        this.mTitle = title;
        this.mRating = rating;
    }
}
