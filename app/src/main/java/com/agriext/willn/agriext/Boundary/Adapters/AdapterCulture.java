package com.agriext.willn.agriext.Boundary.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agriext.willn.agriext.Control.ControlCulture;
import com.agriext.willn.agriext.Control.ControlSpeaker;
import com.agriext.willn.agriext.Entity.Culture;
import com.agriext.willn.agriext.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;


public class AdapterCulture extends BaseAdapter {
    private HashMap<String,Culture> myList;
    private LayoutInflater inflater;
    private ControlSpeaker controlSpeaker;
    private String[] mKeys;
    private Context context;

    public AdapterCulture(Context context, HashMap<String,Culture> myList, ControlSpeaker controlSpeaker) {
        this.myList = myList;
        this.context = context;
        this.controlSpeaker = controlSpeaker;
        this.mKeys = myList.keySet().toArray(new String[myList.size()]);
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public Culture getItem(int position) {
        return myList.get(mKeys[position]);
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


        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            mViewHolder.imgCulture.setBackgroundDrawable(currentListData.getImage());
        } else {
            mViewHolder.imgCulture.setBackground(currentListData.getImage());
        }
        mViewHolder.textCulture.setText(currentListData.getName().toUpperCase());
        mViewHolder.btnSpeakCulture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlSpeaker.speak(currentListData.getName());
            }
        });

        if(ControlCulture.checkIfCultureIsSelected(currentListData))
            mViewHolder.imgChecked.setVisibility(View.VISIBLE);

        mViewHolder.allContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ControlCulture.checkIfCultureIsSelected(currentListData)){
                    mViewHolder.imgChecked.setVisibility(View.GONE);
                    ControlCulture.removeSelectedCulture(currentListData);
                } else {
                    mViewHolder.imgChecked.setVisibility(View.VISIBLE);
                    ControlCulture.addSelectedCulture(currentListData);
                }
            }
        });

        return convertView;
    }

    private class MyViewHolder {
        TextView textCulture;
        ImageView imgCulture, imgChecked;
        Button btnSpeakCulture;
        RelativeLayout allContent;
        MyViewHolder(View item) {
            textCulture = item.findViewById(R.id.textCulture);
            imgChecked = item.findViewById(R.id.imgChecked);
            imgCulture = item.findViewById(R.id.imgCulture);
            btnSpeakCulture = item.findViewById(R.id.btnSpeakCulture);
            allContent = item.findViewById(R.id.allContent);
        }
    }
}
