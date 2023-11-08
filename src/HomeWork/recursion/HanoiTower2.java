package HomeWork.recursion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class HanoiTower2 {
	int size;
	int pos;
	int fromRod;
	int toRod;
	boolean on;
	//int vCoord;
	String ring;
	String rod = "--I-- ";
	static LinkedList<Integer> sizes = new LinkedList<>();
	static List<HanoiTower2> tower = new ArrayList<>();
	public TreeSet<HanoiTower2> setTower = new TreeSet<>();
	
	public HanoiTower2(int size, int pos) {
		this.size = size;
		this.pos = pos;
		ring = "<" + String.valueOf((double)size / 1000).substring(2) + "> ";
		sizes.add(size);
		if(size > 0)
			tower.add(this);
	}
	
	public void move(int from, int to) {
		if(size == 0) {
			return;
		}
		int inter = sizes.getFirst() - from - to;
		new HanoiTower2(size - 1, inter).move(from, inter);
		
		if(sizes.getLast() == 0) {
			fromRod = from;
			toRod = to;
			//vCoord = getVCoord(vCoords, toRod);
			//vCoords.add(toRod);
			if(sizes.size() == sizes.getFirst() + 1)
				printBasicTower();
			on = true;
			setTrace(on);
		}
		new HanoiTower2(size - 1, to).move(inter, to);
	}
	
	public void print() {
		
		System.out.println("Переместить " + size + " из " + fromRod + " в " + toRod + "  sizes " + sizes);
		System.out.println("ring:" + ring);
	}
	public void setTrace(boolean on) {
		if(on)
			print();
	}
//	@Override
//	public String toString() {
//		ring = "<" + String.valueOf((double)size / 1000).substring(2) + "> ";
//		rod = "--I-- ";
//		return ring + rod + rod;
//	}
	
	public int strPos(int pos) {
		switch(pos) {
			case 1 :
				return ring.length();
			case 2 :
				return ring.length()*2;
			default:
				return 0;
		}
	}
	
	public String getRing(int size) {
		List<HanoiTower2> ring = setTower.stream()
						.filter(x -> x.size == size)
						.collect(Collectors.toList());
		return ring.get(0).ring;
	}
	
	public int emptySize(int idx) {
		List<HanoiTower2> filtered = setTower.stream()
						.filter(x -> x.toString().substring(strPos(idx), strPos(idx) + 1).equals(" "))
						.collect(Collectors.toList());
		return filtered.get(filtered.size() - 1).size;
	}
	
	public void setRod(String sizeStr, int pos) {
		StringBuilder sb = new StringBuilder();
		sb.append(sizeStr);
		sb.replace(strPos(pos), strPos(pos) + ring.length(), rod);
		sizeStr = sb.toString();
	}
	
	public void printBasicTower() {
		String base = ("").concat("=").repeat(17);
		setTower = new TreeSet<>((o1, o2) -> Integer.compare(o1.size, o2.size));
		setTower.addAll(tower);
		setTower.forEach(System.out::println);
		System.out.println(base);
	}
	
	public static void main(String[] args) {
		HanoiTower2 ring = new HanoiTower2(3, 0);
		ring.move(0, 1);
	}
}
//	Переместить 1 из 0 в 1
//	Переместить 2 из 0 в 2
//	Переместить 1 из 1 в 2
//	Переместить 3 из 0 в 1
//	Переместить 1 из 2 в 0
//	Переместить 2 из 2 в 1
//	Переместить 1 из 0 в 1
//	public int getVCoord(LinkedList<Integer> coords, int coord) {
//		if(coords.size() > 1 && coord == coords.getLast()) {
//			maxSize += 1;
//			return sizes.getFirst() - maxSize;
//		} else {
//			maxSize = 0;
//			return sizes.getFirst();
//		}
//	}