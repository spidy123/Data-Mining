//PRN  : 2014BIT017
//Name : Shubham Hupare
//Date : 31/07/2017
//Time : 3:20 PM

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.swing.*;

public class Binning {
    ArrayList<Float> list;
    ArrayList<Float> bins[];
    int size, width, binCnt;

    public Binning(){
        list = new ArrayList<>();
    }
   
    void input(){
    	size = Integer.parseInt(JOptionPane.showInputDialog("Enter number of elements"));
        width = Integer.parseInt(JOptionPane.showInputDialog("Enter width of bins"));
         
        for(int i=0;i<size;i++)
        {
        	float n = Float.parseFloat(JOptionPane.showInputDialog("Element "+i));	
            list.add(n);
        }
        binCnt = (size%width==0)?(size/width):(size/width+1);
        Collections.sort(list);           
    }
    
    void binEquiWidth(){
        bins = null;
        bins = new ArrayList[binCnt];
        int cnt=0;
        for(int i=0;i<binCnt;i++){
            bins[i]=new ArrayList<>();
            for(int j=0;j<width && cnt<list.size();j++){
                bins[i].add(list.get(cnt));
                cnt++;
            }
        }
    }
    
    void binBoundaries(){
        float left, right, near;
        for(int i=0;i<binCnt;i++){
            left = bins[i].get(0);
            right = bins[i].get((bins[i].size()-1));
            for(int j=1;j<bins[i].size()-1;j++){
                float no = bins[i].get(j);
                near = (no-left<=right-no)?left:right;
                bins[i].set(j, near);
            }
        }
    }
    
    void binMean(){
        for(int i=0;i<binCnt;i++){
            float mean=0.0f;
            for(int k=0;k<bins[i].size();k++)
                mean += bins[i].get(k);
            mean =(float) mean/bins[i].size();
            for(int j=0;j<bins[i].size();j++){
                bins[i].set(j, mean);
            }
        }
    }
    
    void output(){
        for(int i=0;i<binCnt;i++){
            for(int j=0;j<bins[i].size();j++){
                System.out.print(String.format("%.2f\t",bins[i].get(j)));
            }
            System.out.println();
        }
    }
    
    public static void main(String []arg){
        Binning ew = new Binning();
        ew.input();
        System.out.println("\nEqui Width Binning :");
        ew.binEquiWidth();
        ew.output();
        System.out.println("\nBin Boundaries :");
        ew.binBoundaries();
        ew.output();
        System.out.println("\nBin Mean :");
        ew.binEquiWidth();
        ew.binMean();
        ew.output();
    }
}
