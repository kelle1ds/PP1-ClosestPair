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
		ArrayList<Point> P = new ArrayList<>();
		ArrayList<Point> Q = new ArrayList<>();
		int i = 0;
		while(scanner.hasNext()) {
			String token1 = scanner.next();
			String replaced = token1.replaceAll("[()]", "");
			String replaced2 = replaced.replaceAll("[,]", "");
			String replaced4 = replaced2.replaceAll("\\r\\n|\\r|\\n", " ");
			float num1 = Float.parseFloat(replaced4);
			String token2 = scanner.next();
			String replace1 = token2.replaceAll("[()]", "");
			String replace2 = replace1.replaceAll("[,]", "");
			String replace3 = replace2.replaceAll("\\r\\n|\\r|\\n", " ");
			float num2 = Float.parseFloat(replace3);
			Point point = new Point(num1,num2);
			P.add(point);
			Q.add(point);
			i++;  //count number of points
		}
		scanner.close();

		//out.writeFloat(floatarray[i]);
		/* use your sort method here */

		//MergeSortX sortX = new MergeSortX(P);
		MergeSort sortX = new MergeSort(P,0);
		//mergeSort.sort(array);
		sortX.sortGivenArray();

		P = sortX.getSortedArray();

		//MergeSortY sortY = new MergeSortY(Q);
		MergeSort sortY = new MergeSort(Q,1);
		sortY.sortGivenArray();
		Q = sortY.getSortedArray();
		printArray(P);

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

/* Java class for Merge Sort with arraylist*/

class MergeSort{
	private ArrayList<Point> inputArrayX;  //Array for holding values
	private int side = 0;


	public ArrayList<Point> getSortedArray() {

		return inputArrayX;
	}

	public MergeSort(ArrayList<Point> inputArray){

		this.inputArrayX = inputArray;
	}
	public MergeSort(ArrayList<Point> inputArray, int side){

		this.inputArrayX = inputArray;
		this.side = side;
	}

	public void sortGivenArray(){

		divide(0, this.inputArrayX.size()-1);

	}

	public void divide(int startIndex,int endIndex){

		//Divide till you break down your arraylist to single point nodes
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
		ArrayList<Point> mergedSortedArray = new ArrayList<>();

		int leftIndex = startIndex;
		int rightIndex = midIndex+1;

		if(side == 0) {
			//System.out.println("X value");
			while (leftIndex <= midIndex && rightIndex <= endIndex) {
				//if(inputArray.get(leftIndex).x)
				if (inputArrayX.get(leftIndex).x <= inputArrayX.get(rightIndex).x) {

					mergedSortedArray.add(inputArrayX.get(leftIndex));
					leftIndex++;

				} else {
					mergedSortedArray.add(inputArrayX.get(rightIndex));
					rightIndex++;
				}
			}
		}else{
			//System.out.println("Y value");
			while (leftIndex <= midIndex && rightIndex <= endIndex) {
				//if(inputArray.get(leftIndex).x)
				if (inputArrayX.get(leftIndex).y <= inputArrayX.get(rightIndex).y) {

					mergedSortedArray.add(inputArrayX.get(leftIndex));
					leftIndex++;

				} else {
					mergedSortedArray.add(inputArrayX.get(rightIndex));
					rightIndex++;
				}
			}
		}
		//Either of below while loop will execute
		while(leftIndex<=midIndex){
			mergedSortedArray.add(inputArrayX.get(leftIndex));
			leftIndex++;
		}

		while(rightIndex<=endIndex){
			mergedSortedArray.add(inputArrayX.get(rightIndex));
			rightIndex++;
		}

		int i = 0;
		int j = startIndex;
		//Setting sorted array to original one
		while(i<mergedSortedArray.size()){
			inputArrayX.set(j, mergedSortedArray.get(i++));
			j++;
		}
	}


}

/*
class MergeSortY{
	private ArrayList<Point> inputArray;

	public ArrayList<Point> getSortedArray() {
		return inputArray;
	}

	public MergeSortY(ArrayList<Point> inputArray){
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
		ArrayList<Point> mergedSortedArray = new ArrayList<>();

		int leftIndex = startIndex;
		int rightIndex = midIndex+1;

		while(leftIndex<=midIndex && rightIndex<=endIndex){
			//if(inputArray.get(leftIndex).x)
			if(inputArray.get(leftIndex).y <= inputArray.get(rightIndex).y) {

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

*/
