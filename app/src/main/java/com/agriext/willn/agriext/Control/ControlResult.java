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
        try {
            double water = quixadaHC(culture.getKc());
            result = new Result(culture, water);
            callBack.callBack();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private double quixadaHC(double kc) throws Exception {

        Classifier m5pModel = null;
        try {
            String filename = "model_test.model";
            m5pModel = (Classifier) weka.core.SerializationHelper.read(context.getAssets().open(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

        Instances test = convertCSVToARFF(context.getAssets().open("2016total-semoutlier2.csv")).getInstances();
        int index = test.numAttributes() - 1;
        if (test.classIndex() == -1)
            test.setClassIndex(index);

        double evapo = 0.0;
        double value;

        for (int i = 0;i<test.numInstances();i++){
            double label = m5pModel.classifyInstance(test.get(i));
            test.instance(i).setClassValue(label);
            value = test.instance(i).value(index);
            evapo = evapo + value;
        }

        return evapo * kc;
    }
}
