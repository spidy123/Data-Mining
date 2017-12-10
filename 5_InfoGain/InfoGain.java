//PRN  : 2015BIT213
//Name : Harshvardhan Kadam
//Date : 21/08/2017
//Time : 3:15 PM
import java.util.*;
import java.io.*;

public class InfoGain {
    String table[][], line="";
    Map<String,Integer> counts[];
    Map<String,Double> info;
    StringTokenizer st;
    int rows=0, columns=0;
    
    public InfoGain(){
        table = new String[rows][columns];
        info = new LinkedHashMap<>();
    }
    
    void dataInput(){
    	try{
			FileReader file = new FileReader("Input.csv");
			BufferedReader br = new BufferedReader(file);
			line = br.readLine();
		    columns = (line.split(",")).length;
		    counts = new LinkedHashMap[columns];
		    do{
		    	rows++;
		    }while((line = br.readLine())!=null);
		    
		    file = new FileReader("Input.csv");
			br = new BufferedReader(file);
		    table = new String[rows][columns];
		    for(int i=0;i<rows;i++){
		    	st = new StringTokenizer(br.readLine(),",");
		    	for(int j=0;j<columns;j++)
		    		table[i][j]=st.nextToken();
		    }
        }catch(Exception e){
        	e.printStackTrace();
       	}
    }
    
    void calcultions(){
    	for(int i=0;i<columns;i++){
    		counts[i] = new LinkedHashMap<>();
		    for(int j=1;j<rows;j++){
		    	if(!counts[i].containsKey(table[j][i]))
		    		counts[i].put(table[j][i],1);
		    	else
		    		counts[i].put(table[j][i],counts[i].get(table[j][i])+1);
		    }
		}
		
		double targetEntropy = findTargetInfo();
		for(int i=0;i<columns-1;i++)
			info.put(table[0][i],(double)targetEntropy-findInformation(i));
    }
    
    double findInformation(int col){
    	double infor=0.0f;
    	Iterator it = counts[col].keySet().iterator();
    	for(int k=0;k<counts[col].size();k++){
			int yes=0, no=0;
			String key = (String) it.next();
			for(int i=0;i<rows;i++){
				if(table[i][col].equals(key) && table[i][columns-1].equals("Yes"))
					yes++;
				else if(table[i][col].equals(key) && table[i][columns-1].equals("No"))
					no++;
			}
			float prob = (float) counts[col].get(key)/(rows-1);
			int cnt = counts[col].get(key);
			infor += (float)prob*findEntropy(cnt,yes,no);
    	}
    	return infor;
    }
    
    double findEntropy(int cnt, int y, int n){
    	double entropy = -(((double)y/cnt)*Math.log((double)y/cnt)/Math.log(2.0f) + ((double)n/cnt)*Math.log((double)n/cnt)/Math.log(2.0f));
    	return entropy;
    }
    
    double findTargetInfo(){
    	int yes = counts[columns-1].get("Yes"); 
    	int no = counts[columns-1].get("No"); 
    	int tot = yes + no;
    	double entropy = -(((double)yes/tot)*Math.log((double)yes/tot)/Math.log(2.0f) + ((double)no/tot)*Math.log((double)no/tot)/Math.log(2.0f));
    	return entropy;
    }
       
    void output(){
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                System.out.print(table[i][j]+"\t|");
            }
            System.out.println();
        }
            
        String imp = "";
        System.out.println("\nInformation Gain: ");
   		for (Map.Entry<String, Double> entry : info.entrySet()) {
   			System.out.println(entry.getKey()+" : "+String.format("%.2f",entry.getValue()));
            if (entry.getValue().equals(Collections.min(info.values()))) 
                imp = entry.getKey();
        }    
        System.out.println("\nMost Important Attribute : "+imp);           
    }
    
    public static void main(String args[]){
        InfoGain obj = new InfoGain();
        System.out.println("\nData is taken from 'Input.csv'.\n");
        obj.dataInput();
        obj.calcultions();
        obj.output();
        System.out.println();
    }
}
