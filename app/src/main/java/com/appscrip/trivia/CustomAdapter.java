package com.appscrip.trivia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class CustomAdapter extends BaseAdapter {

    private Context mContext;
    Controllerdb controldb;
    SQLiteDatabase db;
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> Name = new ArrayList<String>();

    public CustomAdapter(Context  context,ArrayList<String> Id,ArrayList<String> Name)
    {
        this.mContext = context;
        this.Id = Id;
        this.Name = Name;

    }
    @Override
    public int getCount() {
        return Id.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final    viewHolder holder;
        controldb =new Controllerdb(mContext);
        LayoutInflater layoutInflater;
        if (view == null) {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.layout, null);
            holder = new viewHolder();
            holder.id = (TextView) view.findViewById(R.id.tvid);
            holder.name = (TextView) view.findViewById(R.id.tvname);

            view.setTag(holder);
        } else {
            holder = (viewHolder) view.getTag();
        }
        holder.id.setText(Id.get(i));
        holder.name.setText(Name.get(i));
        return view;
    }

    public class viewHolder {
        TextView id;
        TextView name;
    }
}
