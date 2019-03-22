package com.tekit.software.mystonecalculator.Util;

import com.tekit.software.mystonecalculator.Model.StoneDimention;

import java.util.Comparator;

public class StoneSortingComparator implements Comparator<StoneDimention> {
    @Override
    public int compare(StoneDimention stoneDimention, StoneDimention stoneDimention1) {

        int noOfNug =stoneDimention1.getNoOfNug().compareTo(stoneDimention.getNoOfNug());
        int lengthOfNug = stoneDimention1.getLengthOfNug().compareTo(stoneDimention.getLengthOfNug());
        int heightOfNug = stoneDimention1.getHeightOfNug().compareTo(stoneDimention.getHeightOfNug());
        if(noOfNug==0){
            return (lengthOfNug==0)? heightOfNug: lengthOfNug;
        }else {
            return noOfNug;
        }

    }
}
