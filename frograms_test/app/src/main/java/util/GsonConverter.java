package util;

import android.os.Environment;

import com.google.common.io.Files;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import data.CardsContainer;

/**
 * Created by dookim on 10/9/15.
 */
public class GsonConverter {

    public static final String SD_CARD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String SAMPLE_FILE_NAME = "sample.json";
    public static final String SAMPLE_FILE_PATH = SD_CARD_PATH + "/" + SAMPLE_FILE_NAME;

    public static CardsContainer jsonToObject(String path) throws IOException {
        String str =Files.toString(new File(path),  Charset.forName("UTF-8"));
        return new Gson().fromJson(str, CardsContainer.class);
    }

    public static CardsContainer strToObject(String data)  {
        return new Gson().fromJson(data, CardsContainer.class);
    }


}
