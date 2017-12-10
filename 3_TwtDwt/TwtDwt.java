//PRN  : 2014BIT017
//Name : Shubham Hupare
//Date : 21/08/2017
//Time : 3:15 PM

import java.util.*;
import java.io.*;

public class TwtDwt {
    String table[][], line="";
    StringTokenizer st;
    int rows=0, columns=0;
    
    public TwtDwt(){
        table = new String[rows][columns];

    }
    
    void dataInput(){
    	try{
			FileReader file = new FileReader("Input.csv");
			BufferedReader br = new BufferedReader(file);
			line = br.readLine();
		    columns = (line.split(",")).length;
		    columns += (columns-1)*2;
		    do{
		    	rows++;
		    }while((line = br.readLine())!=null);
		    
		    file = new FileReader("Input.csv");
			br = new BufferedReader(file);
		    table = new String[rows][columns];
		    for(int i=0;i<rows;i++){
		    	st = new StringTokenizer(br.readLine(),",");
		    	for(int j=0;j<columns;j++){
		    		if(i==0){
		    			if(j==0 || j%3==1)
		    				table[i][j]=st.nextToken();
	   					else if(j%3==2)
	   						table[i][j]=table[i][j-1]+"-Tw";
	   					else
	   						table[i][j]=table[i][j-2]+"-Dw";
		    		}else
		    			if(j==0 || j%3==1)
		    				table[i][j]=st.nextToken();
		    	}
		    }
        }catch(Exception e){
        	e.printStackTrace();
       	}
    }
    
    void calcultions(){
    	for(int i=1;i<rows;i++){
		    for(int j=1;j<columns;j++){
		    	if(j%3==2)//twt
		    		table[i][j]=(100*Float.parseFloat(table[i][j-1])/Float.parseFloat(table[i][columns-3]))+"";
		    	if(j%3==0)//dwt
		    		table[i][j]=(100*Float.parseFloat(table[i][j-2])/Float.parseFloat(table[rows-1][j-2]))+"";
		    }
		}
    }
    
    void output(){
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
            	if(i==0 || j==0)
                	System.out.print(table[i][j]+"\t|");
                else
                	System.out.print(String.format("%.1f",Float.parseFloat(table[i][j]))+"\t|");
            }
            System.out.println();
        }         
    }
    
    public static void main(String args[]){
        TwtDwt obj = new TwtDwt();
        System.out.println("\nData is taken from 'Input.csv'.");
        obj.dataInput();
        
        obj.calcultions();
        System.out.println("\nTable after calculations :");
        obj.output();
        System.out.println();
    }
}
