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
		File file = new File("points3.txt"); //file to read
		FileReader inputFil = new FileReader(file);  //reader
		Scanner scanner = new Scanner(inputFil);  //scanner
		ArrayList<Point> P = new ArrayList<>();  //P array for holding sorted X values
		ArrayList<Point> Q = new ArrayList<>();  //Q array for holding sorted Y values

		int k = 0; //Used to count number of points

		while (scanner.hasNext()) {
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
			Point point = new Point(num1, num2);
			P.add(point);
			Q.add(point);
			k++;  //count number of points
		}
		scanner.close();  //Close that there scanner thingy

		//out.writeFloat(floatarray[k]);
		/* use your sort method here */

		MergeSort sortX = new MergeSort(P, 0);  //Side 0 is for point.x values
		//mergeSort.sort(array);
		sortX.sortGivenArray();  //Sort it

		P = sortX.getSortedArray();  //Return sorted array back to P

		//MergeSortY sortY = new MergeSortY(Q);
		MergeSort sortY = new MergeSort(Q, 1); //Any side other than 1 is point.y
		sortY.sortGivenArray();  //sort it
		Q = sortY.getSortedArray();  //Return to Q array


		PointPair pointV = efficientClosestPair(P,Q);

		//PointPair pointR = efficientClosestPair(Pr,Qr);

	}


	public static PointPair efficientClosestPair(ArrayList<Point> pointsXOrdered, ArrayList<Point> pointsYOrdered) {
		//System.out.println("Number of points: " + pointsXOrdered.size());
		//printArray(pointsXOrdered);
		int sizeV = Math.max(pointsXOrdered.size(), pointsXOrdered.size());
		//if(pointsXOrdered.size() == 0) return null;
		if (sizeV <= 3){  //for 2 or 3 points
			ArrayList<Point> brute = new ArrayList<>();  //bruteforce takes a single arraylist
			for(int i = 0; i < sizeV;i++){
				Point point = new Point(pointsXOrdered.get(i).x, pointsXOrdered.get(i).y);
				brute.add(point);
			}

			return bruteClosestPair(brute);


		} else {

			System.out.println("efficientClosestPair Round ");
			//printArray(pointsXOrdered);
			//printArray(pointsYOrdered);


			int sizeX = pointsXOrdered.size();
			System.out.println("Size of X " + sizeX);
			int sizeY = pointsYOrdered.size();
			int halfSizeX = Math.floorDiv(sizeX,2);
			int halfSizeY = Math.floorDiv(sizeX,2);

			////////////////////////
			ArrayList<Point> Pl = new ArrayList<>();
			for(int i = 0; i < halfSizeX;i++){
				Pl.add(pointsXOrdered.get(i));
			}
			System.out.println("Points of Pl");
			printArray(Pl);

			ArrayList<Point> Ql = new ArrayList<>();
			for(int i = 0; i < halfSizeY;i++){
				Ql.add(pointsYOrdered.get(i));
			}
			System.out.println("Points of Ql");
			printArray(Ql);

			ArrayList<Point> Pr = new ArrayList<>();
			for(int i = halfSizeX; i < sizeX;i++){
				Pr.add(pointsYOrdered.get(i));
			}
			System.out.println("Points of Pr");
			printArray(Pr);

			ArrayList<Point> Qr = new ArrayList<>();
			for(int i = halfSizeY; i < sizeY;i++){
				Qr.add(pointsXOrdered.get(i));
			}
			System.out.println("Points of Qr");
			printArray(Qr);

			/////////////////////////////
			System.out.println("EffiicentClosestPair");
			PointPair dist = efficientClosestPair(Pl,Ql);


			PointPair right = efficientClosestPair(Pr,Qr);
			//System.out.println("Size of Pr is " + right);


			return null;
		}

		//return null;

	}

	public static PointPair bruteClosestPair(ArrayList<Point> points) {
		System.out.println("Brute Closest Pair Size = " + points.size());
		//PointPair pointPair = new PointPair(points.get(0),points.get(1));
		double d = 0;
		int indexI = 0;
		int indexJ = 0;
		for(int i = 0; i < points.size(); i++){
			for(int j = i +1; j < points.size(); j++){

				double dist = Math.sqrt(Math.pow((points.get(i).x - points.get(j).x), 2) +
						Math.pow((points.get(i).y - points.get(j).y), 2));


				if(d <= dist){
					indexI = i;
					indexJ = j;
					d = dist;
				}
			}//index = i;
		}

		System.out.println("Closest distance is: " + d);

		System.out.println("points in bruteforce:");
		printArray(points);
		//Point point1 = new Point()
		PointPair pointPair = new PointPair(points.get(indexI),points.get(indexJ));
		System.out.println("returned point pair from brute " + pointPair);
		return pointPair;
	}


	public static ArrayList<Point> sort(ArrayList<Point> points) {

		/* No call to sort method here.  Implement something that is divide-
		 *  -and-conquer and O(n log n) */

		return null;
	}

	////////////////////////////////////////////////////////////////////
	/* A utility function to print array of size n */
	public static void printArray(ArrayList<Point> arr)
	{
		ListIterator<Point> iterator = arr.listIterator(0);
		while(iterator.hasNext()) {
			Point point = iterator.next();
			System.out.println(point);
		}

		System.out.println();
	}
}

/* Java class for Merge Sort with arraylist*/
//Changes are comming
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

		ArrayList<Point> mergedSortedArray = new ArrayList<>();

		int leftIndex = startIndex;
		int rightIndex = midIndex+1;

		if(side == 0) {
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
		//Sorted array copied to original one
		while(i<mergedSortedArray.size()){
			inputArrayX.set(j, mergedSortedArray.get(i++));
			j++;
		}
	}


}
