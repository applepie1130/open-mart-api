package openmart.apiserver.api.service;

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
	 * 문자열 입력 (30 days of code)
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

	/**
	 * 캥거루텟트
	 * @param args
	 */
//	public static void main(String[] args) {
//
//		int x1 = 0, v1 = 2, x2= 5, v2 =3 ;
//		int sx = x1 - x2;
//		int sv = v2 - v1;
//
//		if ( sx != 0 && sv == 0 ) {
//			System.out.println("NO");
//		} else if (sx % sv == 0 && sx / sv > 0) {
//			System.out.println("YES");
//		} else {
//			System.out.println("NO");
//		}
//	}

	/**
	 * Breaking the Records
	 *
	 * 리스트에있는 요소들의 스코어 기준으로 가장 낮은 기록, 높은기록이 갱신된 결과를 리턴
	 *
	 * @param args
	 */
//	public static void main(String[] args) {
//
//		int[] scores = {10, 5, 20, 20, 4, 5, 2, 25, 1};
//
//		List<Integer> max = new ArrayList<>();
//		List<Integer> min = new ArrayList<>();
//		
//		Integer maxUpdate = 0;
//		Integer minUpdate = 0;
//
//		int length = scores.length;
//		for (int i = 0; i<length; i++) {
//
//			int score = scores[i];
//
//			if ( i == 0 ) {
//				max.add(score);
//				min.add(score);
//			} else {
//				Integer maxVal = max.get(i-1);
//				Integer minVal = min.get(i-1);
//
//				if ( maxVal <= score ) {
//					max.add(score);
//					
//					if (maxVal != score) {
//						// 갱신
//						maxUpdate++;
//					}
//				} else {
//					max.add(maxVal);
//				}
//				
//				if (minVal >= score) {
//					min.add(score);
//					
//					if (minVal != score) {
//						// 갱신
//						minUpdate++;
//					}
//					
//				} else {
//					min.add(minVal);
//				}
//				
//			}
//		}
//
//		System.out.println(max);
//		System.out.println(min);
//		System.out.println(maxUpdate + " " + minUpdate);
//	}
	
	
	/**
	 * Birthday Chocolate
	 * 
	 * 생일날짜는 초콜릿 각 조각의합한 값 
	 * 월은 초콜릿의 연속된 길이 값 
	 * 
	 * 예를들어, 생일이 11월 30일일이면, 초콜릿 조각의 합은 30이 되면서 그 조각들의 길이는 11개여야함
	 * 
	 * @param args
	 */
//	public static void main(String[] args) {
//		/*
//		m=1   5 size + 1 - 1  
//		m=2   4 size + 1 - 2
//		m=3   3
//         s[i+0] + s[i+1] + s[i+2]  
//		 s[0] + s[1] + s[2]
//		 s[1] + s[2] + s[3]
//		 s[2] + s[3] + s[4]
//		*/
//		
//		
////		List<Integer> s = Arrays.asList(2,2,1,3,2);
////		int d=4;
////		int m=2;
//		
//		List<Integer> s = Arrays.asList(4);
//		int d=4;
//		int m=1;
//		
//		
//		int size = s.size() + 1 - m;
//		int result = 0;
//		
//		int sum = 0;
//		for (int i=0; i<size; i++) {
//			for (int j=0; j<m; j++) {
//				sum += s.get(i+j); 
//			}
//			
//			if (sum == d) {
//				result++;
//			}
//			sum = 0;
//		}
//		
//		System.out.println(result);
//	}
	

	
	/**
	 * 숫자계산 (30 days of code)
	 * @param args
	 */
//	public static void main(String[] args) {
//		double meal_cost = 10.25;
//		int tip_percent = 17;
//		int tax_percent = 5;
//		
//		// 13나와야함
//		
//		BigDecimal tip = new BigDecimal(meal_cost)
//								.multiply( new BigDecimal(tip_percent).divide(new BigDecimal(100)));
//								
//		BigDecimal tax = new BigDecimal(meal_cost)
//				.multiply( new BigDecimal(tax_percent).divide(new BigDecimal(100)));
//		
//		BigDecimal totalCost = new BigDecimal(meal_cost).add(tip).add(tax);
//		
//		System.out.println(totalCost.setScale(0, BigDecimal.ROUND_HALF_UP).intValue());
//	}
	
	/**
	 * 1~n까지 숫자가 중복없이 있어야함
	 * 
	 * 예)
	 * [4, 1, 3, 2] -> 배열크기 4, 결과 true
	 * [4, 3, 2] -> 배열크기 3, 결과 false : 배열의 크기가 3이면 3이내의 배열이 중복없이 있어야함
	 * 
	 * @param args
	 */
//	public static void main(String[] args) {
//		
////		int[] arr = {4, 1, 3, 2};
//		int[] arr = {4, 3, 1};
//		int size = arr.length;
//		int limit = size+1;
//		Boolean result = null;
//		
//		AtomicInteger count = new AtomicInteger(1);
//		AtomicInteger falseCount = new AtomicInteger(0); 
//		Stream<Integer> sorted = Arrays.stream(arr).boxed().sorted();
//		
//		// 반대정렬 정렬됨
////		Stream<Integer> sorted = Arrays.stream(arr).boxed().sorted(Collections.reverseOrder());
//		
//		sorted.forEach(t -> {
//			if (count.get() == t) {
//				falseCount.getAndIncrement();
//			}
//			count.getAndIncrement();
//		});
//		
//		if (falseCount.get() > 0) {
//			System.out.println(false);
//		} else {
//			System.out.println(true);
//		}
//		
//		/*
//		for (int i=0, j=1; i<size-1; i++, j++) {
//			
//			int iVal = arr[i];
//			int jVal = arr[j];
//			
//			if ( iVal == limit || jVal == limit ) {
//				result = false;
//				break;
//			}
//			
//			if (iVal == jVal) {
//				result = false;
//				break;
//			} else {
//				result = true;
//			}
//		}
//		
//		System.out.println(result);
//		*/
//	}
	
	/**
	 * 자연수 N이주어지면 각 자리수들의 숫자의 총 합을 구하기
	 * 
	 * 예) 123 = 6 / 987 = 24
	 * 
	 *  
	 */
//	public static void main(String[] args) {
//		
//		int n = 987;
//		int sum = 0;
//		
//		for (;;) {
//			sum += n%10;
//			n = n/10;
//			
//			if ( n == 0) {
//				break;
//			}
//		}
//		System.out.println(sum);
//	}
	
	/**
	 * 양말 짝 고르기 
	 * 같은색상의 양말이 몇개 존재하는지  
	 * 
	 * @param args
	 */
//	public static void main(String[] args) {
//		
//		int[] ar = {10, 20, 20, 10, 10, 30, 50, 10, 20};
//		
//		// 10 10 10 10 20 20 20 30 50
//		List<Integer> sortedList = Arrays.stream(ar).boxed().sorted().collect(Collectors.toList());
//		
//		// START
//		Integer result = 0;
//		
//		Map<Integer, Long> map = Arrays.stream(ar)
//			.boxed()
//			.sorted()
//			.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//
//		for (Long l : map.values() ) {
//			BigDecimal val = new BigDecimal(l);
//			result += val.divide(new BigDecimal(2))
//						.setScale(0, BigDecimal.ROUND_DOWN)
//						.intValue();
//			
//		}
//		// return result;
//		System.out.println(result);
//	}
	
	/**
	 * Counting Valleys
	 * 
	 * 협곡 넘나들기 (심해로 들어갔을때 계곡수 리턴)
	 * 솔루션 : 계속 더한 결과가 0일때 이전에 더해진 값이 -였으면 반등한거이므로, 0이 되었을때 직전이 -였는지 체크하여 카운트 
	 * 
	 * @param args
	 */
//	public static void main(String[] args) {
//		String s = "DDUUDDUDUUUD";
//		
//		int length = s.length();
//		int sum = 0;
//		int result =0;
//		List<Integer> pool = new ArrayList<>();
//		
//		for (int i=0; i<length; i++) {
//			char charAt = s.charAt(i);
//			if (charAt == 'U') {
//				sum++;
//			} else {
//				sum--;
//			}
//			
//			pool.add(sum);
//			
//			if (sum == 0 && i !=0 && pool.get(i-1) == -1) {
//				result++;
//			}
//		}
//		// 0 이전에 -가 있었는지
//		System.out.println(result);
//	}


	/**
	 * 구름 점프 (최대 1,2만큼만 뛸수 있고, 1은 패스해야함. 최단경로)
	 * @param args
	 */
//	public static void main(String[] args) {
//		int[] c = {0, 0, 1, 0, 0, 1, 0}; // 4
////		int[] c = {0, 0, 0, 0, 1, 0}; // 3
////		int[] c = {0,1,0,0,0,1,0}; // 3
////		int[] c = {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0,1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0}; // 28
//		
//		int n = c.length-1;
//		int i = 0;
//		List<Integer> list = new ArrayList<>();
//		
//		while ( i < n ) {
//			if (c[i] == 1) {
//				i++;
//				continue;
//			}
//			
//			if ( i == n-1 ) {
//				if ( c[i+1] == 0 ) {
//					list.add(i+1);
//					i = i+1;
//				}
//				break;
//			}
//			
//			if ( c[i+2] == 0 ) {
//				list.add(i+2);
//				i = i+2;
//			} else if ( c[i+1] == 0 ) {
//				list.add(i+1);
//				i = i+1;
//			}
//		}
//		
//		System.out.println(list);
//		System.out.println(list.size());
//	}

	/**
	 * 전체 사이즈에 문자열을 채워넣고 그 중 a가 몇개인지 알아보기 (인터뷰용 몸풀기문제)
	 * @param args
	 */
//	public static void main(String[] args) {
//		String s = "a";
////		long n = 10;
//		long n = 1000000000000L;
//
//		long size = s.length();
//		long aSize = s.chars().filter(c->c=='a').count();
//		long aCount = ( n/size ) * aSize;
//
//		for (int i =0; i<n%size; i++) {
//			if ( s.charAt(i) == 'a') {
//				aCount++;
//			}
//		}
//
//		System.out.println(aCount);
//	}
	
}
