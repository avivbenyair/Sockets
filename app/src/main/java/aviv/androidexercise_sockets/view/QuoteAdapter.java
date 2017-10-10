package aviv.androidexercise_sockets.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import aviv.androidexercise_sockets.R;
import aviv.androidexercise_sockets.model.realm.QuoteLDB;

/**
 * Created by DELL on 10/10/2017.
 */

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.QuoteHolder> {

    private ArrayList<QuoteLDB> data;

    public QuoteAdapter(ArrayList<QuoteLDB> data) {
        this.data = data;
    }

    public void setData(ArrayList<QuoteLDB> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void addData(QuoteLDB quoteLDB) {
        this.data.add(quoteLDB);
        notifyItemInserted(data.size() - 1);
    }

    @Override
    public QuoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new QuoteHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.quote_viewholder, parent, false));
    }

    @Override
    public void onBindViewHolder(QuoteHolder holder, int position) {
        holder.setData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class QuoteHolder extends RecyclerView.ViewHolder {
        private TextView volumeRateTv;
        private TextView symbolTv;

        public QuoteHolder(View itemView) {
            super(itemView);
            symbolTv = itemView.findViewById(R.id.viewholder_symbol);
            volumeRateTv = itemView.findViewById(R.id.viewholder_volume_rate);
        }

        public void setData(QuoteLDB quoteData) {
            volumeRateTv.setText(quoteData.getAverageDailyVolume());
            symbolTv.setText(quoteData.getSymbol());
        }


    }
}
