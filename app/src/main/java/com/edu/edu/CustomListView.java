package com.edu.edu;

import android.app.Activity;
import android.content.Context;
import android.graphics.ColorSpace;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomListView extends BaseAdapter{

    Context context;

    LayoutInflater inflater;

    List<Model> modelList;

    ArrayList<Model> arrayList;

    public CustomListView(Context context, List<Model> modelList){

        this.context = context;

        this.modelList = modelList;

        inflater = LayoutInflater.from(context);

        this.arrayList = new ArrayList<Model>();

        this.arrayList.addAll(modelList);
    }

    class ViewHolder{

        TextView tv1;

        TextView tv2;

        ImageView iv;
    }

    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public Object getItem(int position) {
        return modelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder holder;

        if(view == null){

            holder = new ViewHolder();

            view = inflater.inflate(R.layout.list_view, null);

            holder.tv1 = view.findViewById(R.id.textViewNome);

            holder.tv2 = view.findViewById(R.id.textViewDescricao);

            holder.iv = view.findViewById(R.id.imageView);

            view.setTag(holder);
        }

        else{

            holder = (ViewHolder) view.getTag();
        }

        holder.tv1.setText(modelList.get(position).getTitulo());

        holder.tv2.setText(modelList.get(position).getDsc());

        holder.iv.setImageResource(modelList.get(position).getImg());

        return view;
    }

    public void filter(String charText){

        charText = charText.toLowerCase(Locale.getDefault());

        modelList.clear();

        if(charText.length() == 0){

            modelList.addAll(arrayList);
        }

        else{

            for(Model model : arrayList){

                if(model.getTitulo().toLowerCase(Locale.getDefault()).startsWith(charText)){

                    modelList.add(model);
                }
            }
        }
    }
}
