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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.agriext.willn.agriext.Boundary.Activites.ResultActivity;
import com.agriext.willn.agriext.Control.CallBack;
import com.agriext.willn.agriext.Control.ControlLoading;
import com.agriext.willn.agriext.Control.ControlResult;
import com.agriext.willn.agriext.Control.ControlSpeaker;
import com.agriext.willn.agriext.Entity.Culture;
import com.agriext.willn.agriext.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;


public class AdapterCulture extends BaseAdapter {
    private List<Culture> myList;
    private LayoutInflater inflater;
    private ControlSpeaker controlSpeaker;
    private Context context;

    public AdapterCulture(Context context, List<Culture> myList, ControlSpeaker controlSpeaker) {
        this.myList = myList;
        this.context = context;
        this.controlSpeaker = controlSpeaker;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public Culture getItem(int position) {
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
            convertView = inflater.inflate(R.layout.layout_culture, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);

        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        final Culture currentListData = getItem(position);
        mViewHolder.textCulture.setText(currentListData.getName().toUpperCase());
        Picasso.get().load(currentListData.getUrlImage()).into(mViewHolder.imgCulture);
        mViewHolder.btnSpeakCulture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlSpeaker.speak(currentListData.getName());
            }
        });

        mViewHolder.allContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Activity activity = (Activity)context;
                final ControlSpeaker controlSpeaker = new ControlSpeaker(context);
                final ControlLoading controlLoading = new ControlLoading(activity, controlSpeaker);
                final ControlResult controlResult = new ControlResult(context);


                controlLoading.initLoading();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        controlResult.calculate(currentListData, new CallBack() {
                            @Override
                            public void callBack() {
                                controlLoading.finishLoading();
                                activity.startActivity(new Intent(activity, ResultActivity.class));
                            }
                        });
                    }
                };

                Runnable speaker = new Runnable() {
                    @Override
                    public void run() {
                        controlSpeaker.speak("AGUARDE UM POUCO, ESTAMOS CARREGANDO");
                    }
                };

                new Handler().postDelayed(speaker,100);
                new Handler().postDelayed(runnable,2000);
            }
        });

        return convertView;
    }

    private class MyViewHolder {
        TextView textCulture;
        ImageView imgCulture;
        Button btnSpeakCulture;
        RelativeLayout allContent;
        MyViewHolder(View item) {
            textCulture = item.findViewById(R.id.textCulture);
            imgCulture = item.findViewById(R.id.imgCulture);
            btnSpeakCulture = item.findViewById(R.id.btnSpeakCulture);
            allContent = item.findViewById(R.id.allContent);
        }
    }
}
