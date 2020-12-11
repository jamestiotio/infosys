package com.jamestiotio.travbud;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TravRecordAdapter extends BaseAdapter {
    Context context;

    TravRecordImpl travRecordImpl;
    LayoutInflater inflter;

    public TravRecordAdapter(Context applicationContext, TravRecordImpl travRecordImpl) {
        this.context = applicationContext;
        this.travRecordImpl = travRecordImpl;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return this.travRecordImpl.count();
    }

    @Override
    public TravRecord getItem(int i) {
        return this.travRecordImpl.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.activity_summary_listview, null);
        TextView date = (TextView) view.findViewById(R.id.textViewDateTime);
        TextView description = (TextView) view.findViewById(R.id.textViewDescription);
        TextView amount = (TextView) view.findViewById(R.id.textViewAmount);

        TravRecord record = this.getItem(i);

        date.setText(record.getRecordDate().toString());
        description.setText(record.getDescription());
        amount.setText(String.valueOf(record.getAmount()));

        return view;
    }
}
