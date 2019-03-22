package com.tekit.software.mystonecalculator.Singleton;

import com.tekit.software.mystonecalculator.Model.StoneDimention;

import java.util.ArrayList;
import java.util.List;

public class StoneSingleton {

         private ArrayList<ArrayList<StoneDimention>> listArrayList = new ArrayList<>();
         private ArrayList<StoneDimention> arrayList = new ArrayList<>();
        // static variable single_instance of type Singleton
        private static StoneSingleton single_instance = null;

        // variable of type String
        public String s;

        // private constructor restricted to this class itself
        private StoneSingleton()
        {
            s = "Hello I am a string part of Singleton class";
        }

        // static method to create instance of Singleton class
        public static StoneSingleton getInstance()
        {
            if (single_instance == null)
                single_instance = new StoneSingleton();

            return single_instance;
        }


    public ArrayList<StoneDimention> getArrayList() {
        return arrayList;

    }

    public void setArrayList(ArrayList<StoneDimention> arrayList) {
        this.arrayList = arrayList;
        listArrayList.add(this.arrayList);
    }

    public ArrayList<ArrayList<StoneDimention>> getListArrayList() {
        return listArrayList;
    }


}
