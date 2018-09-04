package com.agriext.willn.agriext.Boundary.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agriext.willn.agriext.Boundary.Activites.ResultActivity;
import com.agriext.willn.agriext.Control.CallBack;
import com.agriext.willn.agriext.Control.ControlLoading;
import com.agriext.willn.agriext.Control.ControlResult;
import com.agriext.willn.agriext.Control.ControlSpeaker;
import com.agriext.willn.agriext.Control.ControlStation;
import com.agriext.willn.agriext.Entity.Culture;
import com.agriext.willn.agriext.Entity.Station;
import com.agriext.willn.agriext.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class AdapterStation extends BaseAdapter {
    private List<Station> myList;
    private LayoutInflater inflater;
    private ControlSpeaker controlSpeaker;
    private ControlStation controlStation;

    public AdapterStation(Context context, List<Station> myList, ControlSpeaker controlSpeaker, ControlStation controlStation) {
        this.myList = myList;
        this.controlSpeaker = controlSpeaker;
        this.controlStation = controlStation;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public Station getItem(int position) {
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
            convertView = inflater.inflate(R.layout.layout_station, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);

        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        final Station currentListData = getItem(position);
        mViewHolder.stationName.setText(currentListData.getName());
        mViewHolder.stationDescription.setText(currentListData.getDescription());
        mViewHolder.btnSpeakerStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlSpeaker.speak("O nome da estação é:"+currentListData.getName()+", a descrição é "+currentListData.getDescription());
            }
        });

        mViewHolder.stationContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlStation.writeToFile("teste");
            }
        });

        return convertView;
    }

    private class MyViewHolder {
        TextView stationName, stationDescription;
        Button btnSpeakerStation;
        LinearLayout stationContent;
        MyViewHolder(View item) {
            stationContent = item.findViewById(R.id.stationContent);
            stationName = item.findViewById(R.id.stationName);
            stationDescription = item.findViewById(R.id.stationDescription);
            btnSpeakerStation = item.findViewById(R.id.btnSpeakerStationText);
        }
    }
}
