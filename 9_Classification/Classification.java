//PRN  : 2015BIT213
//Name : Harshvardhan Kadam
//Date : 21/08/2017
//Time : 3:15 PM
import java.util.*;
import java.io.*;

public class Classification {
    String table[][], line="", qry="";
    Map<String,Integer> counts[];
    StringTokenizer st;
    int rows=0, columns=0;
    float ans=1.0f, tmp;
    
    public Classification(){
        table = new String[rows][columns];
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
		
		StringTokenizer st = new StringTokenizer(qry,",");
		
		for(int i=0;i<columns-1;i++){
			tmp = findProbability(i,st.nextToken());
			ans = ans*tmp;
		}
		ans = ans * counts[columns-1].get("Yes")/(rows-1);
    }
    
    float findProbability(int col, String key){
    	int yes = 0;
    	for(int i=0;i<rows;i++){
			if(table[i][col].equals(key) && table[i][columns-1].equals("Yes"))
				yes++;
		}
		return (float)yes/counts[col].get(key);
    }
              
    void output(){
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                System.out.print(table[i][j]+"\t|");
            }
            System.out.println();
        }
        System.out.println("\nQuery : "+qry+"\nProbability : "+ans);           
    }
    
    public static void main(String args[]){
        Classification obj = new Classification();
        obj.qry = "Green,Large,Round";
        System.out.println("\nData is taken from 'Input.csv'.\n");
        obj.dataInput();
        obj.calcultions();
        obj.output();
        System.out.println();
    }
}
