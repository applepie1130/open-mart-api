package openmart.apiserver.api.service;

import java.util.Scanner;

public class AlgorithmTest {
    /**
     *      #
     *     ##
     *    ###
     *   ####
     *  #####
     * ######
     * @param args
     */
//	public static void main(String[] args) {
//		int n = 5;
//		IntStream.range(0, n).forEach(t -> {
//			IntStream.range(0, n-t-1).forEach(u -> {
//				System.out.print(" ");
//			});
//			IntStream.range(n-t-1, n).forEach(u -> {
//				System.out.print("#");
//			});
//			System.out.println("");
//		});
//	}

    /**
     * 가장 큰 수 몇개인지
     * 4
     * 3 2 1 3
     * @param args
     */
//	public static void main(String[] args) {
//		int[] ar = {3, 2, 1, 3};
//		int max = Arrays.stream(ar).max().getAsInt();
//		AtomicInteger count = new AtomicInteger();
//		Arrays.stream(ar).forEach(t -> {
//			if (t == max) {
//				count.getAndIncrement();
//			}
//		});
//
//		System.out.println(count.get());
//	}

    /**
     * 미니, 맥시멈 합
     * 제약조건이 1<= arr[i] <= 10의 9승이므로 long형으로 헤야함
     * @param args
     */
//    public static void main(String[] args) {
//
//        int[] arr = {1,3,5,7,9};
//        int length = arr.length;
//        int[] sortedArr = Arrays.stream(arr).sorted().toArray();
//        long maxSum = 0;
//        long minSum = 0;
//
//        for (int i=0; i<4; i++) {
//            minSum+=sortedArr[i];
//        }
//
//        for (int i=length-1; i>=1; i--) {
//            maxSum+=sortedArr[i];
//        }
//
//        System.out.println(minSum + " " + maxSum );
//    }


    /*
     * 시간변경
     * 07:05:45PM -> 19:05:45
     * @param args
     */
//    public static void main(String[] args) {
//        String s = "12:00:00PM";
//        String result = null;
//
//        if (StringUtils.contains(s, "PM")) {
//            s = StringUtils.removeEndIgnoreCase(s, "PM");
//            String[] split = StringUtils.split(s, ":");
//            String hh = split[0];
//            String mi = split[1];
//            String ss = split[2];
//
//            if (StringUtils.equals(hh, "12")) {
//                result = hh+":"+mi+":"+ss;
//            } else {
//                result = String.valueOf(Integer.parseInt(hh) + 12)+":"+mi+":"+ss;
//            }
//        } else if (StringUtils.contains(s, "AM")) {
//            s = StringUtils.removeEndIgnoreCase(s, "AM");
//            String[] split = StringUtils.split(s, ":");
//            String hh = (StringUtils.equals(split[0], "12")) ? "00" : split[0];
//            String mi = split[1];
//            String ss = split[2];
//            result = hh+":"+mi+":"+ss;
//        }
//
//        System.out.println(result);
//    }

    /**
     * 73
     * 67
     * 38
     * 33
     *
     * 75
     * 67
     * 40
     * 33
     *
     * 40점 이하면서 5의 배수 중 3보다 클경우,  
     * 40점 이하면서 5의 배수 중 3보다 작을경우,
     *
     * 40점 초과에 대해 뒷 숫자가 5보다 작으면 5로 올려주고, 5보다 크면 반올림해주기
     * @param args
     */
//    public static void main(String[] args) {
//        List<Integer> grades = Arrays.asList(73, 67, 38, 33);
//        List<Integer> result = new ArrayList<>();
//
//        grades.stream().forEach(t -> {
//        	Integer integer = Integer.valueOf(t % 10);
//
//        	if ( integer > 5 ) {
//        		Integer value = 10-integer;
//        		if (value < 3) {
//        			//올림
//        			BigDecimal bigDecimal = new BigDecimal(t);
//
//        			int calcValue = bigDecimal.setScale(-1, RoundingMode.CEILING).intValue();
//
//        			if ( calcValue >= 40 ) {
//        				result.add(calcValue);
//        			} else {
//        				result.add(t);
//        			}
//
//        		} else {
//        			result.add(t);
//        		}
//
//        	} else {
//        		Integer value = 5-integer;
//        		if (value < 3) {
//        			//5로 올림
//        			String s = t.toString();
//        			String substring = StringUtils.substring(s, 0, s.length()-1);
//        			int calcValue = Integer.parseInt(substring.concat("5"));
//
//        			if ( calcValue >= 40 ) {
//        				result.add(calcValue);
//        			} else {
//        				result.add(t);
//        			}
//
//        		} else {
//        			result.add(t);
//        		}
//        	}
//        });
//        result.stream().forEach(System.out::println);
//    }
	
	/**
	 * 애플, 오랜지
	 * @param args
	 */
//	public static void main(String[] args) {
//		
//		int s = 7;
//		int t = 11;
//		int a = 5;
//		int b = 15;
//		int[] apples = {-2, 2, 1};
//		int[] oranges = {5, -6};
//		
//		List<BigDecimal> aa = new ArrayList<>();
//		List<BigDecimal> bb = new ArrayList<>();
//		
//		BigDecimal s1 = new BigDecimal(s);
//		BigDecimal t1 = new BigDecimal(t);
//		
//		// range s~t
//		IntStream stream1 = Arrays.stream(apples);
//		stream1.forEach(x -> {
//			
//			BigDecimal b1 = new BigDecimal(a);
//			BigDecimal b2 = new BigDecimal(x);
//			BigDecimal add = b1.add(b2);
//			
//			if (add.compareTo(s1) >= 0 && add.compareTo(t1) <= 0) {
//				aa.add(b2);
//			}
//		});
//		IntStream stream2 = Arrays.stream(oranges);
//		stream2.forEach(x -> {
//			BigDecimal b1 = new BigDecimal(b);
//			BigDecimal b2 = new BigDecimal(x);
//			BigDecimal add = b1.add(b2);
//			
//			if (add.compareTo(s1) >= 0 && add.compareTo(t1) <= 0) {
//				bb.add(b2);
//			}
//		});
//		
//		System.out.println(aa.size());
//		System.out.println(bb.size());
//	}
	
	
	/**
	 * 문자열 입력
	 * 
	 * @param args
	 */
//	public static void main(String[] args) {
//        /* Declare second integer, double, and String variables. */
//        int i = 4;
//        double d = 4.0;
//        String s = "HackerRank ";
//        
//        Scanner scan = new Scanner(System.in);
//        
//        int r1 = (int)(i + scan.nextInt());
//        scan.nextLine();
//        double r2 = d + scan.nextDouble();
//        scan.nextLine();
//        String r3 = scan.nextLine();
//        
//        System.out.println( r1 );
//        System.out.println( r2 );
//        System.out.println( s.concat(r3) );
//	}
}
