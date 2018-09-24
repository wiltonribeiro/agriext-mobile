package com.agriext.willn.agriext.Boundary.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.agriext.willn.agriext.Control.ControlSpeaker;
import com.agriext.willn.agriext.Entity.Result;
import com.agriext.willn.agriext.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;


public class AdapterResult extends BaseAdapter {
    private List<Result> myList;
    private LayoutInflater inflater;
    private ControlSpeaker controlSpeaker;

    public AdapterResult(Context context, List<Result> myList, ControlSpeaker controlSpeaker) {
        this.myList = myList;
        this.controlSpeaker = controlSpeaker;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public Result getItem(int position) {
        return myList.get(getCount() - position - 1);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public   View getView(int position, View convertView, ViewGroup parent) {
        final MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_result, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);

        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        final Result currentListData = getItem(position);
        final String watering = ""+String.format(Locale.US,"%.2f", currentListData.getQuantityWater()/10);
        final String liter = ""+String.format(Locale.US,"%.2f", currentListData.getQuantityWater());

        mViewHolder.textResultWatering.setText(watering);
        mViewHolder.textResultLiter.setText(liter);
        mViewHolder.textCulture.setText(currentListData.getCulture().getName());
        Picasso.get().load(currentListData.getCulture().getUrlImage()).into(mViewHolder.imgCulture);
        mViewHolder.btnSpeakerCultureResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = "Para a plantação de "+currentListData.getCulture().getName()+" você deve usar "+watering+" regadores cheios, ou, "+liter+" litros de água";
                controlSpeaker.speak(text);
            }
        });

        return convertView;
    }

    private class MyViewHolder {
        TextView textCulture, textResultWatering, textResultLiter;
        Button btnSpeakerCultureResult;
        ImageView imgCulture;
        MyViewHolder(View item) {
            imgCulture = item.findViewById(R.id.imgCulture);
            textCulture = item.findViewById(R.id.textCulture);
            textResultLiter = item.findViewById(R.id.textResultLiter);
            textResultWatering = item.findViewById(R.id.textResultWatering);
            btnSpeakerCultureResult = item.findViewById(R.id.btnSpeakCultureResult);
        }
    }
}
