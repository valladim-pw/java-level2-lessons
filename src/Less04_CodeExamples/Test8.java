package Less04_CodeExamples;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Test8 {
    static final int MILLION = 1000000;
    static final int ITERATIONS = MILLION*10;
    static final int SHIFT = MILLION*100;
    static final int PRIORITY = 5;

    static class QElement {
        static int number;
        public Integer priority;
        public Integer num;

        QElement(int priority, int num) {
            this.priority = Integer.valueOf(priority*SHIFT+number++);
            this.num = num;
        }
    }

    public static final int randomInterval(int min, int max) {
        return (int)((Math.random() * (max - min)) + min);
    }

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        Comparator<Test8.QElement> comparator = new Comparator<Test8.QElement>() {
	        @Override
            public int compare(Test8.QElement o1, Test8.QElement o2) {
                //return compare(o1.priority, o2.priority);
                return o1.priority.compareTo(o2.priority);
            }
        };
        PriorityQueue<QElement> q1 = new PriorityQueue<>(comparator);
        for(int i=0; i<ITERATIONS; i++) {
            q1.offer(new QElement(randomInterval(0,PRIORITY-1), randomInterval(0, MILLION)));
        }
        long t2 = System.currentTimeMillis();
        PQueue<Integer> q2 = new PQueue<>(PRIORITY);
        for(int i=0; i<ITERATIONS; i++) {
            q2.offer(randomInterval(0,PRIORITY-1), randomInterval(0, MILLION));
        }
        long t3 = System.currentTimeMillis();

        System.out.println("offer q1 = "+(t2-t1));
        System.out.println("offer q2  = "+(t3-t2));

        t1 = System.currentTimeMillis();
        while(q1.size() > 0)
            q1.poll();
        t2 = System.currentTimeMillis();
        while(q2.size() > 0)
            q2.poll();
        t3 = System.currentTimeMillis();

        System.out.println("poll q1 = "+(t2-t1));
        System.out.println("poll q2  = "+(t3-t2));
    }
}
