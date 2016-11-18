package a.demo;

import java.util.ArrayList;
import java.util.List;

public class Demo {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		List<Integer> list2 = new ArrayList<>();
		
		list.add(1);
		list.add(2);
		list.add(4);
		list.add(3);
		
		list2.add(1);
		list2.add(2);
		list2.add(5);
		
		list2.removeAll(list);
		list.addAll(list2);
		
		System.out.println(list2);
		System.out.println(list);
		
		java.util.Collections.sort(list);
		System.out.println(list);
		
		
		
		byte b = -127;
		byte c = (byte)129;
		
		
		
		System.out.println(b+" "+c );
		
	}

}
