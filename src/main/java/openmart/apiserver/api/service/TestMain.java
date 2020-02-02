package openmart.apiserver.api.service;

import java.util.Arrays;

public class TestMain {

	public static void main(String[] args) {
		
		// 삽입정렬
//		insertionSort();
		
		// 선택정렬
		selectionSort();
		
		// 버블정렬
//		bubbleSort();
		
		// 퀵정렬
//		quickSort();
	}
	
	/**
	 * 삽입정렬
	 */
	public static void insertionSort() {
		int arry[] = {5, 1, 10, 9};
		int n = arry.length;
		int i, j, k;
		
		for (i = 1; i < n; i++) {
			k = arry[i];
			j = i-1;
			
			while ( j >= 0 && arry[j] > k ) {
				arry[j+1] = arry[j];
				j--;
			}
			
			arry[j+1] = k;
		}
		
		Arrays.stream(arry)
			  .forEach(System.out::println);
	}
	

	/**
	 * 선택정렬
	 */
	public static void selectionSort() {

		int arry[] = {5, 1, 2, 10, 9};
		int n = arry.length;
		int i, j, mi;
		
		for (i=0; i<n-1; i++) {
			
			mi = i;
			
			for (j=i+1; j<n; j++) {
				
				if (arry[j] < arry[mi]) {
					mi = j;
				}
			}
			
			int temp = arry[mi];
			arry[mi] = arry[i];
			arry[i] = temp;
		}

		Arrays.stream(arry)
			  .forEach(System.out::println);
	}
	
	
	/**
	 * 버블정렬
	 */
	public static void bubbleSort() {
		
		int arry[] = {5, 1, 10, 9};
		int n = arry.length;
		int i, j;
		
		for (i = 0; i < n-1; i++) {
			for (j = 0; j < n-i-1; j++) {
				if (arry[j] > arry[j+1] ) {
					int temp = arry[j];
					arry[j] = arry[j+1];
					arry[j+1] = temp;
				}
			}
		}
		
		Arrays.stream(arry).forEach(System.out::println);
	}

	/*
	public static void mergeSortMain() {
		int arry[] = {5, 1, 10, 9};
		int n = arry.n;
		
		mergeSort(arry, 0, n-1);
		
		
		Arrays.stream(arry).forEach(System.out::println);
		
	}
	
	public static void mergeSort(int[] arry, int left, int right) {
		
		if (left < right) {
			
			int midium = left + (right - left) / 2;
			
			// 분할 좌/우
			mergeSort(arry, left, midium);
			mergeSort(arry, midium+1, right);
			
			// 정복
			merge(arry, left, midium, right);
		} else {
			return;
		}
	}
	
	public static void merge(int[] arry, int left, int midium, int right) {
		int temp[] = new int[9999];
		int i = left;
		int j = midium + 1;
		int k = 0;
		
		while (i < midium && j < right) {
			if (arry[i] <= arry[j]) {
				temp[k] = arry[i];
				k++;
				i++;
			} else {
				temp[k] = arry[j];
			}
		}
	}
	*/
	
}
