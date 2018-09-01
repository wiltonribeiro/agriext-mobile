package com.agriext.willn.agriext.Control;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.agriext.willn.agriext.Entity.Culture;
import com.agriext.willn.agriext.Entity.Result;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import weka.classifiers.Classifier;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.M5P;
import weka.core.Capabilities;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.filters.unsupervised.attribute.StringToWordVector;


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
            ObjectInputStream objectStream = new ObjectInputStream(context.getAssets().open(filename));
            Object obj = objectStream.readObject();
            m5pModel = (Classifier) obj;
        } catch (FileNotFoundException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        } catch (ClassNotFoundException e) {
            return e.getMessage();
        } catch (Exception e) {
            return e.getMessage();
        }

        Instances test = convertCSVToARFF(context.getAssets().open("quixada-fevereiro-mes-et0.csv")).getInstances();
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

        Log.e("willneto",m5pModel.toString());
        Log.e("willneto",test.toString());

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
