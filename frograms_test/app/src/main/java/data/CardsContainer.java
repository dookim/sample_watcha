package data;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import util.GsonConverter;
import util.SimpleJsonMockupData;

/**
 * 카드를 담고 있는 객체, 만들어지는 순간 simple.json에서 가져온다.
 * 한번 만들어지면(simple.json에서 가져오면), 그 이후부터는 더이상 io가 일어나지 않는다.
 * 각각의 fragment는 다른 cards을 갖고 있다. 하나의 공유 메모리로 card를 갖고 있게 되면, 잠재적인 버그나
 * 추후 다른 프래그먼트에서 card데이터를 바꾸게 될때 문제가 생길가능성이 높기 때문이다.
 * 따라서 각각의 fragment는 clone메서드를 사용해서 별개의 cards을 갖고 있는다.
 * Created by dookim on 10/8/15.
 */
public class CardsContainer {
    public List<Card> cards;
    private static CardsContainer mCardsContainer;

    @Override
    public List<Card> clone()  {
        List<Card> newCards = new ArrayList<>();

        for(Card card : cards) {
            newCards.add(new Card(card));
        }

        return newCards;
    }

    /**
     * sdcard에 file을 넣기 귀찮다면,
     * mCardsContainer = GsonConverter.strToObject(SimpleJsonMockupData.SIMPLE_JSON_DATA);
     * 으로 구동하시면 편합니다.
     * @return
     * @throws IOException
     */
    public static CardsContainer getInstance() throws IOException {
        if(mCardsContainer == null) {
            mCardsContainer = GsonConverter.strToObject(SimpleJsonMockupData.SIMPLE_JSON_DATA);
            //mCardsContainer = GsonConverter.jsonToObject(GsonConverter.SAMPLE_FILE_PATH);
        }
        return mCardsContainer;
     }

    private static ExecutorService mExecutorService = Executors.newSingleThreadExecutor();
    private static Handler handler = new Handler(Looper.getMainLooper());

    /**
     * cardcontiner를 생성하는 부분은 io가 발생하므로 background에서 비동기적으로 가져오는 부분
     * @param onCardContainerCompleted
     */
    public static void getCardContainerOnBackground(final OnCardContainerCompleted onCardContainerCompleted) {
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    mCardsContainer = getInstance();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            onCardContainerCompleted.onCompleted(mCardsContainer);
                        }
                    });
                } catch (final IOException e) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            onCardContainerCompleted.onError(e);
                        }
                    });

                }
            }
        });
    }

    public interface OnCardContainerCompleted {
        public void onCompleted(CardsContainer cardsContainer);
        public void onError(Exception e);
    }

}
