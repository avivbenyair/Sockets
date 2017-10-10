package aviv.androidexercise_sockets.model.realm;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class QuoteLDB extends RealmObject {

    @PrimaryKey
    private String symbol;
    private String averageDailyVolume;


    public QuoteLDB() {
    }

    public QuoteLDB(String symbol, String averageDailyVolume) {
        this.symbol = symbol;
        this.averageDailyVolume = averageDailyVolume;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getAverageDailyVolume() {
        return averageDailyVolume;
    }

    public void setAverageDailyVolume(String averageDailyVolume) {
        this.averageDailyVolume = averageDailyVolume;
    }
}
