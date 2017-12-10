//PRN  : 2014BIT017
//Name : Shubham Hupare


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.swing.*;


public class Normalization 
{
    ArrayList<Integer> olist;
    ArrayList<Float> minMaxList, zScoreList;
    int size, min, max, newMin, newMax;
    float mean;
    
    public Normalization()
    {
        olist = new ArrayList<>();
        minMaxList = new ArrayList<>();
        zScoreList = new ArrayList<>();
    }
   
    void input(){
        size = Integer.parseInt(JOptionPane.showInputDialog("Enter number of elements"));     
        for(int i=0;i<size;i++)
        {
            int n = Integer.parseInt(JOptionPane.showInputDialog("Element "+i));
            olist.add(n);
        }
        newMin = Integer.parseInt(JOptionPane.showInputDialog("Enter new min")); 
        newMax = Integer.parseInt(JOptionPane.showInputDialog("Enter new max"));

        Collections.sort(olist);    
        min = Collections.min(olist);
        max = Collections.max(olist);
    }

	void minMaxNorm(){
		float newNum;
		for(int i=0;i<size;i++){
            newNum = (((float)olist.get(i)-min)/(max-min))*(newMax-newMin)+newMin;
            minMaxList.add(newNum);
        }
	}

    void zScoreNorm(){
        float sd, var, newNum;
        for(int i=0;i<size;i++)
            mean += olist.get(i);
        mean /= size;
        var = calVar();
        sd = (float) Math.sqrt(var);
        for(int i=0;i<size;i++){
            newNum = ((float)olist.get(i)-mean)/sd;
            zScoreList.add(newNum);
        }
    }
    
    float calVar(){
        float tmp = 0.0f;
        for(int i=0;i<size;i++)
            tmp += (mean-olist.get(i))*(mean-olist.get(i));
        return tmp/(size-1);
    }
        
    void output(ArrayList list){
        for(int i=0;i<size;i++)
            System.out.printf("%.2f ",list.get(i));
        System.out.println();
    }
    
    public static void main(String []arg){
        Normalization norm = new Normalization();
        norm.input();
        
        norm.minMaxNorm();
        System.out.println("\nAfter Min-Max Normalization:");
        norm.output(norm.minMaxList);
        
        norm.zScoreNorm();
        System.out.println("\nAfter Z-Score Normalization:");
        norm.output(norm.zScoreList);
    }
    
}
