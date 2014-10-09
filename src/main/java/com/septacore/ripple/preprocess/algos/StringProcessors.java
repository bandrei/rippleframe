/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.septacore.ripple.preprocess.algos;

import com.septacore.ripple.preprocess.apps.PPAppBase;
import com.septacore.ripple.preprocess.types.PPType;
import com.septacore.ripple.preprocess.types.PPTypeDouble;
import com.septacore.ripple.preprocess.types.PPTypeString;

import java.util.Arrays;

/**
 *
 * @author rory
 */
public class StringProcessors {

    /**
     * mean(string)
     *
     * @author rory
     */
    public static class PPMean extends PPAppBase {

        public PPMean() {
            super(new PPTypeDouble(), new PPType[]{new PPTypeString()});
        }
        // Returns the mean of the ASCII/UNICODE values of each char.
        // Works for UTF-8
        // Works for ASCII

        public static double Mean(String data) {

            int i;
            double sum = 0;
            int length = data.length();

            for (i = 0; i < length; i++) {
                sum = sum + (float) data.charAt(i);
            }

            return sum / length;

        }

        @Override
        public Object applyPreprocessor(Object[] argVals) {
            return (Object) Mean((String) argVals[0]);
        }
    }

    /**
     * sd(string)
     *
     * @author rory
     */
    public static class PPStandardDeviation extends PPAppBase {

        public PPStandardDeviation() {
            super(new PPTypeDouble(), new PPType[]{new PPTypeString()});
        }
        // Returns the standard deviation of the ASCII/UNICODE values of each char.
        // Works for UTF-8
        // Works for ASCII

        public static double StandardDeviation(String data) {

            double variance = 0;
            int length = data.length();
            // Use existing function to calculate the mean.
            double mean = PPMean.Mean(data);

            for (int i = 0; i < length; i++) {
                variance += Math.pow(data.charAt(i) - mean, 2);
            }

            return Math.sqrt(variance / length);

        }

        @Override
        public Object applyPreprocessor(Object[] argVals) {
            return (Object) StandardDeviation((String) argVals[0]);
        }
    }

    /**
     * len(string)
     *
     * @author rory
     */
    public static class PPLength extends PPAppBase {

        public PPLength() {
            super(new PPTypeDouble(), new PPType[]{new PPTypeString()});
        }
        // Returns length of data.

        public static double Length(String data) {

            return data.length();

        }

        @Override
        public Object applyPreprocessor(Object[] argVals) {
            return (Object) Length((String) argVals[0]);
        }
    }

    /**
     * skewlen(string)
     *
     * @author andrei
     */
    public static class PPSkewLength extends PPAppBase {

        public PPSkewLength() {
            super(new PPTypeDouble(), new PPType[]{new PPTypeString()});
        }
        // Returns length of data.

        public static double SkewLength(String data) {
            return data.length() * data.length();
        }

        @Override
        public Object applyPreprocessor(Object[] argVals) {
            return (Object) SkewLength((String) argVals[0]);
        }
    }
    
    

            /**
     * log(double,double)
     *
     * @author andrei
     */
    public static class PPLog extends PPAppBase {

        public PPLog() {
            super(new PPTypeDouble(), new PPType[]{new PPTypeDouble(), new PPTypeDouble()});
        }
        // Returns length of data.

        public static double Log(Double val, Double base) {
            return Math.log(val)/Math.log(base);
        }

        @Override
        public Object applyPreprocessor(Object[] argVals) {
            return (Object) Log((Double) argVals[0], (Double) argVals[1]);
        }
    }

    /**
     * extractdomain(string,string)
     *Extract the domain name from the string (i.e. the first occurence of
     * dom in data) and return the remaining of it.
     * @author andrei
     */
    public static class PPSExtractDomain extends PPAppBase {

        public PPSExtractDomain() {

            super(new PPTypeString(), new PPType[]{new PPTypeString(), new PPTypeString()});
        }
        // Returns length of data.

        public static String ExtractDomain(String data, String dom) {
            int indexOfDom = data.indexOf(dom);
            String d;
            if (indexOfDom >= 0) {
             //   System.out.println(dom);
                d = data.substring(indexOfDom + dom.length());
                if (d.length() == 0) {
                    d = "/";
                }
            } else {
                d = data;
            }
            
            //System.out.println(d+ " "+d.length());
            return d;
        }

        @Override
        public Object applyPreprocessor(Object[] argVals) {
            return (Object) ExtractDomain((String) argVals[0], (String) argVals[1]);
        }
    }

    /**
     * skewlog(string)
     *
     * Skew the length of a string using log in base e.
     * @author andrei
     */
    public static class PPSkewLog extends PPAppBase {

        public PPSkewLog() {
            super(new PPTypeDouble(), new PPType[]{new PPTypeString()});
        }
        // Returns length of data.

        public static double SkewLog(String data) {
            Double len = (double) data.length();
            if (len == 0) {
                len = 1.0;
            }
            return Math.log(len);
        }

        @Override
        public Object applyPreprocessor(Object[] argVals) {
            return (Object) SkewLog((String) argVals[0]);
        }
    }

    /**
     *
     * Get median.
     */
    public static class PPMedian extends PPAppBase {

        public PPMedian() {
            super(new PPTypeDouble(), new PPType[]{new PPTypeString()});
        }

        private static double getMedian(String data) {

            char[] chars = data.toCharArray();
            Arrays.sort(chars);

            int length = chars.length;
            int middle = (length + 1) / 2;
            if (length % 2 != 0) {
                return (double) chars[middle - 1];
            } else {
                return ((double) chars[middle - 1] + chars[middle]) / 2;
            }

        }

        @Override
        public Object applyPreprocessor(Object[] argVals) {
            return (Object) getMedian((String) argVals[0]);
        }
    }

    /**
     *
     * Get interquartilerange.
     */
    public static class PPInterquartilerange extends PPAppBase {

        public PPInterquartilerange() {
            super(new PPTypeDouble(), new PPType[]{new PPTypeString()});
        }

        private static int getInterquartileRange(String data) {

            char[] chars = data.toCharArray();
            Arrays.sort(chars);

            int length = chars.length;
            int lowerquartile = (length + 1) / 4;
            int upperquartile = 3 * (length + 1) / 4;

            upperquartile = chars[upperquartile];
            lowerquartile = chars[lowerquartile];

            return upperquartile - lowerquartile;

        }

        @Override
        public Object applyPreprocessor(Object[] argVals) {
            return (Object) getInterquartileRange((String) argVals[0]);
        }
    }

    public static class PPCharcount extends PPAppBase {

        public PPCharcount() {
            super(new PPTypeDouble(), new PPType[]{new PPTypeString()});
        }
        // Calculates the unicode/ascii values of each character and uses the value
        // as the index and increments that for each instance of the character.

        private static int[] getCharcount(String input) {

            // "For type int, the default value is zero, that is, 0."
            int[] characters = new int[255];

            int length = input.length();

            // Iterate through the string and access each char in turn to calculate
            // the unicode/ascii value.
            for (int i = 0; i < length; i++) {
                characters[input.codePointAt(i)]++;
            }
            //codePointAt() Returns the character value.

            return characters;
        }

        @Override
        public Object applyPreprocessor(Object[] argVals) {
            return (Object) getCharcount((String) argVals[0]);
        }
    }
}
