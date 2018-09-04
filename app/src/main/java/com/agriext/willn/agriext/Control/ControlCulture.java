package com.agriext.willn.agriext.Control;

import android.content.Context;
import android.widget.ListView;

import com.agriext.willn.agriext.Boundary.Adapters.AdapterCulture;
import com.agriext.willn.agriext.Entity.Culture;

import java.util.ArrayList;
import java.util.List;

public class ControlCulture {

    private Context context;

    public ControlCulture(Context context) {
        this.context = context;
    }

    public void listCultures(ListView listView){
        listView.setAdapter(null);
        listView.requestLayout();
        listView.setAdapter(new AdapterCulture(context,fillCultureData(), new ControlSpeaker(context)));
    }

    private List<Culture> fillCultureData(){
        List<Culture> cultures = new ArrayList<>();
        cultures.add(new Culture("Arroz", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSx9mIK7jiHgbRMTpU5YJtPotn8Sxhnpwx6aysL1WUh-tOwUl9RKQ"));
        cultures.add(new Culture("Café", "http://www.agronovas.com.br/wp-content/uploads/2015/05/cafe_2.jpg"));
        cultures.add(new Culture("Soja", "http://www.brasil.gov.br/economia-e-emprego/2016/01/safra-2015-2016-atingira-210-5-milhoes-de-toneladas-de-graos/img_8861.jpg/@@images/ba1a4d16-cfaa-4907-ab68-2e523f121683.jpeg"));

        return  cultures;
    }
}
