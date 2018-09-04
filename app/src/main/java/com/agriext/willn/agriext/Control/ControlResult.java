package com.agriext.willn.agriext.Control;
import android.content.Context;
import android.os.Handler;

import com.agriext.willn.agriext.Entity.Culture;
import com.agriext.willn.agriext.Entity.Result;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;


public class ControlResult implements CallBack {
    public static Result result;
    private Context context;

    public ControlResult(Context context) {
        this.context = context;
    }

    public void calculate(final Culture culture, final CallBack callBack){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                result = new Result(culture, 50);callBack.callBack();
            }
        }, 5000);
    }

    public void callBack() { }

    private ArffSaver convertCSVToARFF(InputStream inputStream) {
        CSVLoader loader = new CSVLoader();
        Instances data = null;
        try {
            loader.setSource(inputStream);
            data = loader.getDataSet();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Salva ARFF
        ArffSaver saver = new ArffSaver();
        if (data != null) {
            saver.setInstances(data);
            return saver;
        }
        return saver;
    }

    public String quixadaHC(double kc) throws Exception {

        Classifier m5pModel;
        try {
            String filename = "m5p_new.model";
//            ObjectInputStream objectStream = new ObjectInputStream(context.getAssets().open(filename));
//            Object obj = objectStream.readObject();
//            m5pModel = (Classifier) obj;
            m5pModel = (Classifier) weka.core.SerializationHelper.read(context.getAssets().open(filename));
        } catch (FileNotFoundException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        } catch (ClassNotFoundException e) {
            return e.getMessage();
        } catch (Exception e) {
            return e.getMessage();
        }

        //ERROR lenght=15; index=15;
        //2016-total.csv
        //2016total-semoutlier2.csv
        //cr200series_table1_agosto.csv
        //fevereiro-quixada-sem-et0.csv
        //fortaleza-fevereiro.csv
        //quixada-fevereiro-mes-et0.csv
        //dados_quixe.csv
        //dadosfortal.csv
        //up.csv
        //updado.csv
        //upquixe.csv

        //Outros erros
        //dados_ufcup.csv não convertido
        //mediafortal.csv não convertido

        Instances test = convertCSVToARFF(context.getAssets().open("2016total-semoutlier2.csv")).getInstances();



//        ArffLoader arffLoader = new ArffLoader();
//        arffLoader.setSource(context.getAssets().open("limpos.arff"));
//        Instances test = arffLoader.getDataSet();
//        CSVLoader csvLoader = new CSVLoader();
//        csvLoader.setSource(context.getAssets().open("limpos.csv"));
//        Instances test = csvLoader.getDataSet();
        int index = test.numAttributes() - 1;
        if (test.classIndex() == -1)
            test.setClassIndex(index);

        String resultado = "";
        double evapo = 0.0;
        double value, kcValue;

//        Log.e("willneto",m5pModel.toString());
//        Log.e("willneto",test.toString());

        for (int i = 0;i<test.numInstances();i++){
            //ArrayIndexOutOfBoundsException está acontecendo aqui
            double label = m5pModel.classifyInstance(test.get(i));
            test.instance(i).setClassValue(label);
            value = test.instance(i).value(index);
            kcValue = value * kc;
            resultado = resultado + value + ", " + kcValue + "\n";
            evapo = evapo + value;
        }

        double irriga = evapo * kc;
        resultado = resultado + evapo +", " + irriga;
        return resultado;
    }
}
