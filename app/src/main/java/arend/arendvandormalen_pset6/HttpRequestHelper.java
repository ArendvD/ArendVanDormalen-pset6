package arend.arendvandormalen_pset6;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Arend on 2016-12-10.
 *
 * This class sends a request to the server and returns the results in a String.
 */

public class HttpRequestHelper {

    private static final String url1 = "https://www.rijksmuseum.nl/api/nl/collection?q=";
    private static final String url2 = "&key=VPovhf3D&format=json";

    protected static synchronized String downloadFromServer(String... params){

        String result = "";

        String searchTerm = params[0];

        String completeUrl = url1 + searchTerm + url2;

        // Create URL, check on mistakes.
        URL url = null;
        try {
            url = new URL(completeUrl);
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

        HttpURLConnection connection;
        if (url != null){
            try{
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                Integer responseCode = connection.getResponseCode();

                if (200 <= responseCode && responseCode < 300){
                    BufferedReader br = new BufferedReader(new
                            InputStreamReader(connection.getInputStream()));
                    String line = br.readLine();
                    while (line != null){
                        result = result + line;
                        line = br.readLine();
                    }
                } else {

                    Log.d("responseCodeError", responseCode.toString());

                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(connection.getErrorStream()));
                }

            } catch (IOException e){
                e.printStackTrace();
            }
        }

        return result;

    }


}
