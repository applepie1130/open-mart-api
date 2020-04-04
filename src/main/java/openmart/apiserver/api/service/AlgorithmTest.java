package openmart.apiserver.api.service;

import org.apache.commons.lang3.StringUtils;

import javax.sound.midi.Soundbank;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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
    public static void main(String[] args) {
        List<Integer> grades = Arrays.asList(73, 67, 38, 33);
        List<Integer> result = new ArrayList<>();

        grades.stream().forEach(t -> {
            if ( t.compareTo(40) < 0 ) {
                result.add(t);
            } else {
                Integer integer = Integer.valueOf(t % 10);
                // 5보다 작은경우
                if (integer.compareTo(5) < 0 ) {
                    String s = t.toString();
                    String substring = StringUtils.substring(s, 0, s.length()-1);
                    result.add(Integer.parseInt(substring.concat("5")));
                } else {
                    BigDecimal bigDecimal = new BigDecimal(t);
                    result.add(bigDecimal.setScale(-1, RoundingMode.CEILING).intValue());
                }
            }
        });

        System.out.println(result);

    }


}
