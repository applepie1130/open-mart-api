package openmart.apiserver.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AlgorithmsTest2 {
	
	
	/*****************************************************************/
	/*  2020.04.08
		PracticeInterview > Preparation > KitDictionaries and Hashmaps
		Dictionaries and Hashmaps
	*/
	/*****************************************************************/
	/**
	 * TWO String
	 * 
	 * 2개의 문자열 중 각각 겹치는 캐릭터가 존재할 경우 YES
	 * 솔루션 : 중복문자를 제거한 후 비교를 때리면 한 껏 수월해짐
	 *  
	 * @param args
	 */
//	public static void main(String[] args) {
//		String s1 = "hello";
//		String s2 = "world";
//		
//		Set<Character> s1Set = s1.chars().mapToObj(i -> (char)i).collect(Collectors.toSet());
//		Set<Character> s2Set = s2.chars().mapToObj(i -> (char)i).collect(Collectors.toSet());
//		
//		Optional<Character> findFirst = s1Set.stream().filter(s->s2Set.contains(s)).findFirst();
//		
//		if ( findFirst.isPresent() ) {
//			System.out.println("YES");
//		} else {
//			System.out.println("NO");
//		}
//	}
	
	
	/**
	 * Ransome Note
	 * 
	 * 납치범이 노트를 쓸때 매거진에 있는 문자를 참조하려는 문제 
	 * 
	 * 두개의 문자열이 주어지며, 메거진에 있는 문자엷보다 노트의 문자열이 동일한게 더 많거나, 노트에 있는 문자열이 매거진에 없을경우 NO
	 * 
	 * 해쉬맵 : 문자열,중복숫자 
	 * 
	 * 중복된 숫자가 키가되어, 존재하더라도 중복숫자로 조건비교처리
	 *  
	 * @param args
	 */
//	public static void main(String[] args) {
////		String[] magazine = {"two", "times", "three", "is", "not", "four"};
////		String[] note = {"two", "times", "two", "is", "four"};
//		
//		String[] magazine = {"ive","got","a", "lovely","bunch","of","coconuts"};
//		String[] note = {"ive","got", "some", "coconuts"};
//		
//		Map<String, Long> mMap = Arrays.stream(magazine).
//										collect(Collectors.groupingBy(s->s, Collectors.counting()));
//		
//		Map<String, Long> nMap = Arrays.stream(note).
//				collect(Collectors.groupingBy(s->s, Collectors.counting()));
//		
//		System.out.println(mMap);
//		System.out.println(nMap);
//		
//		String result = "Yes";
//		int size = note.length;
//		for (int i=0; i<size; i++) {
//			Long nVal = nMap.get(note[i]);
//			Long mVal = mMap.get(note[i]);
//			
//			if ( mVal == null ) {
//				result = "No";
//				break;
//			} else if ( nVal != mVal && nVal > mVal) {
//				result = "No";
//				break;
//			}
//		}
//		System.out.println(result);
//	}
	
	/*****************************************************************/
	/*  2020.04.08
	 * Practice > Interview Preparation Kit > Sorting
	 * Sorting
	*/
	/*****************************************************************/
	
	/**
	 * 버블소팅
	 * @param args
	 */
//	public static void main(String[] args) {
//		
////		int[] a = {1,2,3};
//		int[] a = {3,2,1};
////		List<Integer> list = IntStream.of(a).boxed().sorted().collect(Collectors.toList());
////		
////		list.get(0);
////		list.get(list.size()-1);
//		
//		int n = a.length;
//		int swapCount = 0;
//		for (int i = 0; i < n; i++) {
//		    for (int j = 0; j < n - 1; j++) {
//		        // Swap adjacent elements if they are in decreasing order
//		        if (a[j] > a[j + 1]) {
//		        	int temp = a[j];
//		        	a[j] = a[j+1];
//		        	a[j+1] = temp;
//		        	swapCount++;
//		        }
//		    }
//		}
//		/*
//		Array is sorted in 0 swaps.
//		First Element: 1
//		Last Element: 3
//		*/
//		System.out.println("Array is sorted in " + swapCount + " swaps.");
//		System.out.println("First Element: " + a[0]);
//		System.out.println("Last Element: " + a[n-1]);
//	}
	
	
	/*
	 * Mark and Toys
	 * 
	 * 비정렬된 숫자중 특정 값 k 보다 커지지 않는 조건으로 가장 많은 요소의 갯수
	 *  
	 */
//	public static void main(String[] args) {
//		int[] prices = {1, 12, 5, 111, 200, 1000, 10};
//		int k = 50;
//		
//		// sort
//		List<Integer> list = IntStream.of(prices).boxed().sorted().collect(Collectors.toList());
//		int size = list.size();
//		int sum = 0;
//		int result = 0;
//		for (int i =0; i<size; i++) {
//			
//			sum += list.get(i);
//			result++;
//			if ( sum > k ) {
//				break;
//			}
//		}
//		
//		System.out.println(result - 1);
//	}
	
	
	/*****************************************************************/
	/*  2020.04.08
		PracticeInterview > Preparation Kit > String Manipulation
		String Manipulation
	*/
	/*****************************************************************/
	
	/**
	 * Strings: Making Anagrams
	 * 
	 * 두개의 문자열들 중 중복되지 않은 애들에서 삭제해야 할 캐릭터 수
	 * 주의할 사항으로는, 한 문자열에 중복된 여러 캐릭터의 경우도 삭제 대상에서 고려해야함
	 * 
	 * @param args
	 */
//	public static void main(String[] args) {
////		String a = "cde";
////		String b = "dcf";
//		
////		String a = "cdeccc";
////		String b = "abbc";
//		// abbcccde
//
//		String a = "fcrxzwscanmligyxyvym";
//		String b = "jxwtrhvujlmrpdoqbisbwhmgpmeoke";
//		
//		
//		 Set<Character> set = (a+b).chars().mapToObj(i -> (char)i).collect(Collectors.toSet());
//		 Map<Character, Long> aMap = a.chars().mapToObj(i -> (char)i).collect(Collectors.groupingBy(s->s, Collectors.counting()));
//		 Map<Character, Long> bMap = b.chars().mapToObj(i -> (char)i).collect(Collectors.groupingBy(s->s, Collectors.counting()));
//		 
////		 final List<String> list = Arrays.asList("a","b","c","d","e","f","g","h","i", "j", "k", "l", "m", "n", "o", "p",
////				 "q","r","s", "t","u","v","w","x","y","z");
//		 
////		 System.out.println(aMap);
////		 System.out.println(bMap);
//		 
//		 int rm = 0;
//		 for (Character c : set) {
//			 Long l1 = aMap.get(c);
//			 Long l2 = bMap.get(c);
//			 
//			 if ( l1 != null && l2 != null ) {
//				 if (l1 == l2) {
//					 continue;
//				 }
//				 long r = Math.abs(l1-l2);
//				 
//				 if (r > 0) {
////					 System.out.println(c + " " + r );
//					 rm+=r;
//				 }
//			 }
//			 
//			 if ( l1 == null ) {
//				 rm+=l2;
//			 }
//			 if (l2 == null) {
//				 rm+=l1;
//			 }
//		 }
//		 System.out.println(rm);
//	}
	
	
	/*****************************************************************/
	/*  2020.04.08
		PracticeInterview > Preparation Kit > Greedy Algorithms
	*/
	/*****************************************************************/
	
	/**
	 * Minimum Absolute Difference in an Array
	 * 
	 * 각 요소별 Math.abs(a - b) 한 값이 가장 작은것 고르기 
	 * 
	 * 타임아웃남..ㅠㅠ 
	 * 
	 * @param args
	 */
//	public static void main(String[] args) {
////		int[] arr = {1, -3, 17 , 68, 71};
//		int[] arr = {-59, -36, -13, 1, -53, -92, -2, -96, -54, 75};
//
//        int n = arr.length;
//        Integer min = null;
//        Integer op = null;
//        
//        for (int i=0; i<n; i++) {
//            for (int j=i+1; j<n; j++) {
//            	op = Math.abs(arr[i] - arr[j]);
//            	
//            	if (min == null) {
//            		min = op; 
//            	} else if (min > op) {
//            		min = op;
//            	}
//            }
//        }
//        
//        System.out.println(min);
//	}


	/*****************************************************************/
	/*  2020.04.08
		PracticeInterview > Preparation Kit > Arrays
	*/
	/*****************************************************************/
	/**
	 * Arrays : Left Rotation
	 * 왼쪽으로 배열 요소를 이동처리
	 * @param args
	 */
//	public static void main(String[] args) {
//
//		int[] a = {1, 2, 3, 4};
//		int d = 2;
//		List<Integer> list = IntStream.of(a).boxed().collect(Collectors.toList());
//
//		List<Integer> subList = list.subList(d, a.length);
//		subList.addAll(list.subList(0, d));
//
//		System.out.println(subList);
//		int[] result = subList.stream().mapToInt(i -> i).toArray();
//
//
//	}

	/*****************************************************************/
	/*  2020.04.08 (Medium)
		PracticeInterview > Preparation Kit > Search > IceCream Parlor
	*/
	/*****************************************************************/

	/**
	 * Alternating Characters
	 * 캐릭터 변경
	 * @param args
	 */
//	public static void main(String[] args) {
////		String s = "AAABBB";
////		String s = "ABABABAB";
////		String s = "BABABA";
//		String s = "AAAA";
//		List<Character> list = s.chars().mapToObj(i -> (char) i).collect(Collectors.toList());
//
//		int size = list.size();
//		List<Character> result = new ArrayList<>();
//
//		for (int i=0, j=1; j<size; j++) {
//
//			Character c1 = list.get(i);
//			Character c2 = list.get(j);
//
//			if ( c1 == c2 )  {
//				result.add(c2);
//			} else {
//				i=j;
//			}
//		}
//
//		System.out.println(result);
//		System.out.println(result.size());
//	}

}
