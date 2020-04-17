package openmart.apiserver.api.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;


/**
 * Codility
 * 
 * @author sungjun.kim
 *
 */
public class AlgorithmsTest3 {
	
	/**
	 * 이진간격 제일 큰 수 구하기
	 *
	 * https://app.codility.com/c/run/training56ZDCF-56R/ 
	 * @param args
	 */
	public static void main(String[] args) {
		int n = 32;
		String binaryString = Integer.toBinaryString(n);
		List<Character> list = binaryString.chars().mapToObj(i -> (char)i).collect(Collectors.toList());
		System.out.println(list);
		
		int size = list.size();
		Boolean isPoint = false;
		List<Integer> result = new ArrayList<>();
		int count = 0;
		for (int i=0; i<size; i++) {
			Character character = list.get(i);
			
			if ( character.equals('1') && isPoint ) {
				result.add(count);
				count = 0;
			} else if (character.equals('1')) {
				isPoint = true;
			}
			
			if ( character == '0' && isPoint ) {
				count++;
			}
		}
		
		if ( CollectionUtils.isNotEmpty(result) ) {
			// return
			result.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()).get(0);
		} else {
			// return 0
		}
	}
	
}
