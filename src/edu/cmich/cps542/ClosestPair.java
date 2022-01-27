package edu.cmich.cps542;

/**
 * <h1>Team Programming Project One</h1
 * CPS 542
 * @author kelle1ds
 * @version 1.0
 * @since 2022-1-26
 *
 */

//package edu.cmich.cps542;

import java.util.ArrayList;
import java.io.*;
//import java.util.List;
import java.util.ListIterator;
//import java.util.Random;
import java.util.Scanner;
//import java.util.stream.Collectors;

public class ClosestPair {

	public static void main(String[] args) throws IOException {
	
		/* load data from points.txt here */
		File file = new File("points.txt");

		FileReader inputFil = new FileReader(file);
		Scanner scanner = new Scanner(inputFil);
		
		ArrayList<Point> array = new ArrayList<Point>();
		
		int i = 0;
		while(scanner.hasNext()) {
			
			String token1 = scanner.next();
			String replaced = token1.replaceAll("[()]", "");
			String replaced2 = replaced.replaceAll("[,]", "");
			String replaced4 = replaced2.replaceAll("\\r\\n|\\r|\\n", " ");
			float num1 = Float.parseFloat(replaced4);
			//float num2 = 0.0f;
			
			String token2 = scanner.next();
			String replace1 = token2.replaceAll("[()]", "");
			String replace2 = replace1.replaceAll("[,]", "");
			String replace3 = replace2.replaceAll("\\r\\n|\\r|\\n", " ");
			float num2 = Float.parseFloat(replace3);
			
			Point point = new Point(num1,num2);
			array.add(point);
			
		    i++;  //count number of points
		}
		
		scanner.close();
		
		//System.out.println(array);
		
		//printArray(array);


		    //out.writeFloat(floatarray[i]);
            

		/* use your sort method here */
		
		MergeSort mergeSortX = new MergeSort(array);
		//mergeSort.sort(array);
		mergeSortX.sortGivenArray();
		
		//ArrayList<Point> arrayX = new ArrayList<Point>();
		//arrayX = mergeSortX.getSortedArray();
		for(int k = 0; k < array.size()-1;k++) {
			System.out.println(array.get(k));
		}
		
		
		
		//printArray(array);
		
		/* call efficientClosestPair here */
		
	}
	
	/* A utility function to print array of size n */
	static void printArray(ArrayList<Point> arr)
	{
		ListIterator<Point> iterator = arr.listIterator(2);  
		while(iterator.hasNext()) {
			Point point = iterator.next();
			System.out.println(point);
		}
		
		System.out.println();
	}

	public static PointPair efficientClosestPair(ArrayList<Point> pointsXOrdered, ArrayList<Point> pointsYOrdered) {
		
		
			
		return null;
			
	}
	
	public static PointPair bruteClosestPair(ArrayList<Point> points) {
		

		return null;

	}
	
	
	public static ArrayList<Point> sort(ArrayList<Point> points) {
		
		/* No call to sort method here.  Implement something that is divide-
		 *  -and-conquer and O(n log n) */
		
		return null;
	}
	
	
	
}

/* Java program for Merge Sort */

class MergeSort {
    private ArrayList<Point> inputArray;
    
    public ArrayList<Point> getSortedArray() {
        return inputArray;
    }
 
    public MergeSort(ArrayList<Point> inputArray){
        this.inputArray = inputArray;
    }
    
    public void sortGivenArray(){       
        divide(0, this.inputArray.size()-1);
    }
    
    public void divide(int startIndex,int endIndex){
        
        //Divide till you breakdown your list to single element
        if(startIndex<endIndex && (endIndex-startIndex)>=1){
            int mid = (endIndex + startIndex)/2;
            divide(startIndex, mid);
            divide(mid+1, endIndex);        
            
            //merging Sorted array produce above into one sorted array
            merge(startIndex,mid,endIndex);   
        }       
    }   
    
    
    public void merge(int startIndex,int midIndex,int endIndex){
        
        //Below is the mergedarray that will be sorted array Array[i-midIndex] , Array[(midIndex+1)-endIndex]
        ArrayList<Point> mergedSortedArray = new ArrayList<Point>();
        
        int leftIndex = startIndex;
        int rightIndex = midIndex+1;
        
        while(leftIndex<=midIndex && rightIndex<=endIndex){
           //if(inputArray.get(leftIndex).x)
        	if(inputArray.get(leftIndex).x == inputArray.get(rightIndex).x) {
        		if(inputArray.get(leftIndex).y <=inputArray.get(rightIndex).y){        
            		mergedSortedArray.add(inputArray.get(leftIndex));
                    leftIndex++;
        		}
                else{
                    mergedSortedArray.add(inputArray.get(rightIndex));
                    rightIndex++;
                }
        	}
            else if(inputArray.get(leftIndex).x <=inputArray.get(rightIndex).x){
                
        		mergedSortedArray.add(inputArray.get(leftIndex));
                leftIndex++;
            }else{
                mergedSortedArray.add(inputArray.get(rightIndex));
                rightIndex++;
            }
        }       
        
        //Either of below while loop will execute
        while(leftIndex<=midIndex){
            mergedSortedArray.add(inputArray.get(leftIndex));
            leftIndex++;
        }
        
        while(rightIndex<=endIndex){
            mergedSortedArray.add(inputArray.get(rightIndex));
            rightIndex++;
        }
        
        int i = 0;
        int j = startIndex;
        //Setting sorted array to original one
        while(i<mergedSortedArray.size()){
            inputArray.set(j, mergedSortedArray.get(i++));
            j++;
        }
    }

    
}

