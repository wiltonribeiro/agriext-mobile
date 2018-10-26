package com.agriext.willn.agriext.Control;

import android.content.Context;
import android.widget.GridView;
import android.widget.ListView;

import com.agriext.willn.agriext.Boundary.Adapters.AdapterCulture;
import com.agriext.willn.agriext.Entity.Culture;

import java.util.HashMap;
import java.util.Set;

public class ControlCulture {

    private Context context;
    private static ControlSaveDataCache controlSaveDataCache;
    private static HashMap<String,Culture> listCultures = fillCultureData();
    public static HashMap<String,Culture> listSelectedCultures = new HashMap<>();

    public ControlCulture(Context context) {
        this.context = context;
    }

    public static void initCultureData(Context context){
        controlSaveDataCache = ControlSaveDataCache.getInstance(context);
        fillSelectedCultures();
    }

    public static void addSelectedCulture(Culture culture){
        listSelectedCultures.put(culture.getName(),culture);
    }

    public static void removeSelectedCulture(Culture culture){
        listSelectedCultures.remove(culture.getName());
    }

    public static boolean checkIfCultureIsSelected(Culture culture){
        return listSelectedCultures.get(culture.getName()) != null;
    }

    private static void fillSelectedCultures(){
        Set<String> mKeys = controlSaveDataCache.getAllSelectedCulture();
        if(mKeys != null)
            for(String key : mKeys) listSelectedCultures.put(key,listCultures.get(key));
    }

    public static void saveSelectedCultures(){
        controlSaveDataCache.addSelectedCulture(listSelectedCultures);
    }

    public void listCultures(ListView listView){
        listView.setAdapter(null);
        listView.requestLayout();
        listView.setAdapter(new AdapterCulture(context,listCultures, new ControlSpeaker(context)));
    }



    private static HashMap<String,Culture> fillCultureData(){
        HashMap<String,Culture> cultures = new HashMap<>();
        cultures.put("Arroz", new Culture("Arroz", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSx9mIK7jiHgbRMTpU5YJtPotn8Sxhnpwx6aysL1WUh-tOwUl9RKQ",1.05));
        cultures.put("Café", new Culture("Café", "http://www.agronovas.com.br/wp-content/uploads/2015/05/cafe_2.jpg",0.65));
        cultures.put("Soja", new Culture("Soja", "http://www.brasil.gov.br/economia-e-emprego/2016/01/safra-2015-2016-atingira-210-5-milhoes-de-toneladas-de-graos/img_8861.jpg/@@images/ba1a4d16-cfaa-4907-ab68-2e523f121683.jpeg", 0.75));
        cultures.put("Algodão", new Culture("Algodão", "http://site1382380002.hospedagemdesites.ws/wp-content/uploads/2017/05/algod%C3%A3o-400x258.jpg", 0.85));
        cultures.put("Batata", new Culture("Batata", "https://www.montaencanta.com.br/wp-content/uploads/2014/10/wpid-Potato-Wallpaper-17.jpg", 0.75));
        cultures.put("Cana-de-Açucar", new Culture("Cana-de-Açucar", "http://cerradoeditora.com.br/cerrado/wp-content/uploads/2017/08/Benef%C3%ADcios-da-cana-de-a%C3%A7%C3%BAcar-e1504033391386.jpg", 0.85));
        cultures.put("Feijão Verde", new Culture("Feijão Verde", "http://www.kinadofeijaoverde.com.br/wp-content/uploads/2017/07/nutrientes-do-feijao-verde.jpg", 0.85));
        cultures.put("Feijão Seco", new Culture("Feijão Seco", "https://dicasdieta.com.br/wp-content/uploads/2015/11/beneficios-do-feijao-de-corda.jpg", 0.7));
        cultures.put("Melancia", new Culture("Melancia", "https://p2.trrsf.com/image/fget/cf/460/0/images.terra.com/2018/04/03/melancias.jpg", 0.75));
        cultures.put("Tomate", new Culture("Tomate", "https://static.tuasaude.com/media/article/cb/ca/beneficios-do-tomate_18598_l.jpg", 0.75));
//        cultures.put("Uva", new Culture("Uva", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQZuG8j6slxkBLkj0_skZmei98cDT1_Dwldd0WFY0ynIriUcy8osg", 0.5));
        return  cultures;
    }
}
