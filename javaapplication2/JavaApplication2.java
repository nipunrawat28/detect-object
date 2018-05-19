package javaapplication2;

import algoritharium.*;
import java.awt.Color;
//import shapes.*;

 
public class JavaApplication2 {
    public static void main(String[] args) {
        new ImageViewer();
    }
     
    public static void identifyShape() {
        
        Image img = ImageViewer.getImage();
        Color[][] c = img.getPixelColors();
        recolor(c);
         
        int rowCount = 0;
        int row = 0;
        int rowTotal = 0;
        //Number of rows with black pixels
        for (int x = 0; x < img.getWidth() - 1; x++){
            for (int y = 0; y < img.getHeight() - 1; y++) {
                while (c[x][y] == Color.BLACK && x < img.getWidth()) {
                    row++;
                    y++;
                }
                if (row > rowTotal)
                    rowTotal = row;
                 
                row = 0;
           }
        }
        System.out.println(rowTotal);
        double[] blackPixels = new double[rowTotal];
        int arrayNumber = 0;
        //Number of black pixels per row
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                if (c[x][y] == Color.BLACK)
                    rowCount++;
            }
            if (rowCount > 0) {
                blackPixels[arrayNumber] = rowCount;
                arrayNumber++;
                rowCount = 0;
            }
        }
        double maxB = max(blackPixels);
        double meanB = mean(blackPixels);
        double varB = var(blackPixels);
        double stddevB = stddev(blackPixels);
        System.out.println();
        //mean(blackPixels);
        //var(blackPixels);
        //stddev(blackPixels);
        System.out.println("Total # Rows: " + rowTotal);
        System.out.println("Height: " + maxB);
        System.out.println("Width: " + blackPixels.length);
        //ID SHAPES HERE
        if (maxB == meanB && rowTotal == maxB)
            System.out.println("The shape is a square.");
        else if (maxB == meanB && rowTotal != maxB)
            System.out.println("The shape is a rectangle.");
        else if (maxB == rowTotal && stddevB != varB)
            System.out.println("The shape is a circle.");
        else if (.5 * maxB < meanB && rowTotal > maxB)
            System.out.println("The shape is a triangle.");
        else if (meanB != maxB&& (1 / 3) * maxB == (1 / 2) * rowTotal)
            System.out.println("The shape is a polygon.");
        else
            System.out.println("The shape is unknown.");
    }
     
    private static void recolor(Color[][] c) {
        for(int y = 0; y < c.length; y++)
            for(int x = 0; x < c[y].length; x++)
                if (c[y][x].getRed() < 50)
                    c[y][x] = Color.BLACK;
                else
                    c[y][x] = Color.white;
        
        ImageViewer.createImage(c);
    }
     
    public static double max(double[] a) { // Compute maximum value in a[]
        double max = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < a.length; i++)
            if(a[i] > max)
                max = a[i];
 
        System.out.println("max: " + max);
        return max;
    }
     
    public static double mean(double[] a) { // Compute the average of the values in a[]
        double sum = 0.0;
        for (int i = 0; i < a.length; i++)
            sum += a[i];
 
        System.out.println("mean: " + sum / a.length);
        return sum / a.length;
    }
     
    public static double var(double[] a) { // Compute the sample variance of the values in a[]
        double avg = mean(a);
        double sum = 0.0;
        for (int i = 0; i < a.length; i++)
            sum += (a[i] - avg) * (a[i] - avg);
 
        System.out.println("var: " + sum / (a.length - 1));
        return sum / (a.length - 1);
    }
     
    public static double stddev(double[] a) { 
        double dev = Math.sqrt(var(a));
        System.out.println("stddev: " + dev);
        return dev;
         
    }
}