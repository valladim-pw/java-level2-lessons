package Less08_BinaryTrees._1_BinarySearchTree_AVLTree._00Tests;
import java.util.ArrayList;
import java.util.List;

class Person {
    Long id;
    String name;

    public Person(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        System.out.print(1);
        if (obj == this) {
            return true;
        }
        if (obj instanceof Person) {
            return id.equals(this.id);
        }
        return false;
    }
}

public class MainClass {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person(1L, "Leo"));
        list.add(new Person(2L, "Raph"));
        list.add(new Person(3L, "Donnie"));
        list.add(new Person(4L, "Mikey"));

        list.contains(new Person(3L, "Shredder"));
    }
}
