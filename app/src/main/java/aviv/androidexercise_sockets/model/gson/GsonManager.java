package aviv.androidexercise_sockets.model.gson;

import com.google.gson.Gson;

import java.util.List;



public class GsonManager {

    public static List<Quote> convertMessageJsonToStocksArray(Gson gson, String jsonResults) {
        StockResults results = gson.fromJson(jsonResults, StockResults.class);
        return results.getResults().getQuote();
    }


}
