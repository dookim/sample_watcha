package data;

import java.util.HashMap;
import java.util.Map;

/**
 * 영화별 rating점수는 싱글턴이다. 사용자가 조작한 rating점수는 ratingmapcontainer에서 caching된다.
 * Created by dookim on 10/12/15.
 */
public class RatingMapContainer {

    private RatingMapContainer() {}

    private static Map<String, Float> ratingValues = new HashMap<>();

    public static Map<String, Float> getRatingValues() {
        return ratingValues;
    }

}
