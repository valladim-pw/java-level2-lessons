package HomeWork.gc;

import HomeWork.gc.HeapWithPrintedBlocks.HeapTest;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Test {
	public static void main(String[] args) {
		int pos = 0;
		int num = 20;
		int num2 = 15;
		int ptr = 5;
		int size = 3;
		
		byte[] bytes = new byte[num];
		byte[] bytes2 = new byte[num2];
		byte[] data = new byte[size];
		Arrays.fill(data, (byte)ptr);
		System.out.println("Data1: " + Arrays.toString(data));
		System.out.println("Bytes1: " + Arrays.toString(bytes));
		System.out.println("Bytes2-1: " + Arrays.toString(bytes2));
		
		System.arraycopy(data, 0, bytes, ptr, data.length);
		
		System.out.println("set Data2: " + Arrays.toString(data));
		System.out.println("set Bytes2: " + Arrays.toString(bytes));
		System.out.println("Bytes2-2: " + Arrays.toString(bytes2));
		
		System.arraycopy(bytes, 3, bytes2, 0, bytes2.length);
		
		System.out.println("get Data2: " + Arrays.toString(data));
		System.out.println("get Bytes2: " + Arrays.toString(bytes));
		System.out.println("get Bytes2-2: " + Arrays.toString(bytes2));
		
		
		//System.out.println(Arrays.toString(bytes));
//		for (int i = 0; i < bytes.length; i++) {
//			bytes[pos + i] = bytes[(byte)start + (byte)i];
//		}
//		ArrayDeque<Integer> blocks = new ArrayDeque<>();
//		blocks.offer(25);
//		blocks.offer(5);
//		blocks.offer(3);
//		blocks.offer(40);
//		blocks.offer(7);
//		System.out.println(blocks);
//		blocks.poll();
//		System.out.println(blocks);
//		for (int i = 0; i < 50; i++) {
//			System.out.print("%25: " + Math.abs(ThreadLocalRandom.current().nextInt() % 25));
//			System.out.println(", %10: " + Math.abs(ThreadLocalRandom.current().nextInt() % 10));
//		}
//		Bloks2:
// [ i=16, size=7:
// [ BB ][ BB ][ BB ][ BB ][ BB ][ BB ][ BB ]
//,  i=23, size=1:
// [ BB ]
//,  i=24, size=1:
// [ BB ]
//,  i=25, size=3:
// [ BB ][ BB ][ BB ]
//,  i=28, size=3:
// [ BB ][ BB ][ BB ]
//,  i=31, size=1:
// [ BB ]
//,  i=32, size=4:
// [ BB ][ BB ][ BB ][ BB ]
//,  i=36, size=1:
// [ BB ]

//		allocBloks2:
// [ i=0, size=2:
// [ AB ][ AB ]
//,  i=2, size=7:
// [ AB ][ AB ][ AB ][ AB ][ AB ][ AB ][ AB ]
//,  i=9, size=1:
// [ AB ]
//,  i=10, size=1:
// [ AB ]
//,  i=11, size=3:
// [ AB ][ AB ][ AB ]
//,  i=17, size=1:
// [ AB ]
//,  i=18, size=4:
// [ AB ][ AB ][ AB ][ AB ]
//,  i=22, size=1:
// [ AB ]

	}
}
