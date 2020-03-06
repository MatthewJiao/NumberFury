package number.fury.application;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import org.jsoup.Jsoup;

import java.io.IOException;

public class DownloadService extends IntentService {
    public static final String PARAM_IN_MSG = "imsg";
    public static final String PARAM_OUT_MSG = "omsg";


    public DownloadService() {
        super("SimpleIntentService");
    }
    @Override

    protected void onHandleIntent(Intent intent) {

        final ResultReceiver receiver = intent.getParcelableExtra("receiver");
        Bundle bundle = new Bundle();
        String url = "https://numberfury.000webhostapp.com/leaderboard.html";
        String source = null;
        String newS1;
        String newS2;
        String Sscore1;
        String Sscore2;
        int num1;
        int num2;
        int allHigh1 = 100;
        int allHigh2 = 110;

        try {
            source = Jsoup.connect(url).get().html();
        } catch (IOException ex) {
            Log.d("addj", "hdiuahiuhiuha");
        }

        num1 = source.indexOf("Matt");
        newS1 = source.substring(num1, num1+70);
        Sscore1=extractNumber(newS1);

        allHigh1 = Integer.parseInt(Sscore1);

        num2 = source.indexOf("Lambert");
        newS2 = source.substring(num2, num2+70);
        Sscore2=extractNumber(newS2);

        allHigh2 = Integer.parseInt(Sscore2);

        //     String test1 = Integer.toString(allHigh1);
        //    String test2 = Integer.toString(allHigh2);

        //    Log.d("myTag", test1+" dkjadn"+test2);

        int [] highs = {allHigh1, allHigh2};
        bundle.putIntArray("HIGH", highs);
        receiver.send(10, bundle);
    }
    public static String extractNumber(String str) {

        if(str == null || str.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        boolean found = false;
        for(char c : str.toCharArray()){
            if(Character.isDigit(c)){
                sb.append(c);
                found = true;
            } else if(found){
                // If we already found a digit before and this char is not a digit, stop looping
                break;
            }
        }

        return sb.toString();
    }
}



