package Less04_HashTable_List_Deque_Stack._2_HashTables._2ChainHashTable;

import java.util.Arrays;

public class TestCHashTable {
	public static void main(String[] args) {
		CHashTable<String> table = new CHashTable<>(5);
		for(int i = 0; i < 20; i++) {
			table.add(i, "i=" + i);
		}
		for(int i = 0; i < 20; i++)
			table.get(i);
	}
}
