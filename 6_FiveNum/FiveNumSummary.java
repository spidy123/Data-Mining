//PRN  : 2015BIT213
//Name : Harshvardhan Kadam
//Date : 04/09/2017
//Time : 3:15 PM

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class FiveNumSummary {
	int size, arr[];
    float max, min, median, q1, q3;
    Scanner sc;

    void init(){
        sc = new Scanner(System.in);
        System.out.println("\nEnter Count of Numbers:");
        size = sc.nextInt();
        arr = new int[size];
        System.out.println("\nEnter "+size+" Numbers:");
        for(int i=0;i<size;i++)
            arr[i]=sc.nextInt();
        Arrays.sort(arr);
        min = arr[0];
        max = arr[size-1];
    }
        
    float findMedian(int start, int end){
    	int tSize = end - start;
    	if((tSize+1)%2==1){
        	return arr[tSize/2+start];
        }
        else{
        	return (arr[tSize/2+start]+arr[tSize/2+start+1])/2.0f;
        }
    }
    
    void print(){
        System.out.println("\nMin\t : "+min);
        System.out.println("Q1\t : "+q1);
        System.out.println("Median\t : "+median);
        System.out.println("Q3\t : "+q3);
        System.out.println("Max\t : "+max+"\n");
    }
    
    public static void main(String []hk){
    	try{
		    FiveNumSummary obj = new FiveNumSummary();
		    obj.init();
		    obj.q1 = obj.findMedian(0,obj.size/2-1);
		    obj.median = obj.findMedian(0,obj.size-1);
		    int newStart = (obj.size%2==0)?obj.size/2-1:obj.size/2;
		    obj.q3 = obj.findMedian(newStart,obj.size);
		    obj.print();
        }catch(Exception e){
        	e.printStackTrace();
        }
    }
}

//9:	3 7 8 5 12 14 21 13 18
//10: 	3 7 8 5 12 14 21 15 18 14
