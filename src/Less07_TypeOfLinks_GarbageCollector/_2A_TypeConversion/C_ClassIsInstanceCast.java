package Less07_TypeOfLinks_GarbageCollector._2A_TypeConversion;

import java.util.List;


public class C_ClassIsInstanceCast {
	static class Animal {
		public boolean isAnimal() {
			return true;
		}
	}
	static class Fox extends Animal {
		public boolean isFox() {
			return true;
		}
	}
	static class FennecFox extends Fox {
		public boolean isAnimal() {
			return true;
		}
	}
	
	public static void main(String[] args) {
		List<Animal> animals = List.of(new Fox(), new Animal(), new FennecFox());
		
		for (Animal animal : animals) {
			if (Fox.class.isInstance(animal)) {
				Fox fox = Fox.class.cast(animal);
				System.out.println("Это лиса? " + fox.isFox());
			}
		}
	}
}