package aviv.androidexercise_sockets.model.realm;

import java.util.ArrayList;
import java.util.List;

import aviv.androidexercise_sockets.model.gson.Quote;
import io.realm.Realm;


public class RealmManager {

    public static ArrayList<QuoteLDB> saveToQuoteToRealm(final List<Quote> quotes, Realm realm) {
        final ArrayList<QuoteLDB> quoteLDBs = convertQuotesToQuoteLDB(quotes);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(quoteLDBs);
            }
        });
        return quoteLDBs;
    }

    private static ArrayList<QuoteLDB> convertQuotesToQuoteLDB(List<Quote> quotes) {
        ArrayList<QuoteLDB> quoteLDBs = new ArrayList<>();
        for (int i = 0; i < quotes.size(); i++) {
            String symbol = quotes.get(i).getSymbol();
            String dailyVolume = quotes.get(i).getAverageDailyVolume();
            QuoteLDB quoteLDB = new QuoteLDB(symbol, dailyVolume);
            quoteLDBs.add(quoteLDB);
        }
        return quoteLDBs;
    }
}
