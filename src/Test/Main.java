package Test;

import sun.misc.Lock;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] strings = new String[n];
        for (int i = 0; i < n; i++) {
            strings[i] = scanner.next();
        }
        HashSet<StringBuilder> hashSet = new HashSet<>(n);
        for (int i = 0; i < n; i++) {
            HashSet<Integer> set = new HashSet<>(n);
            set.add(i);
            StringBuilder stringBuilder = new StringBuilder(strings[i]);
            List<StringBuilder> builders = find(strings,set);
            for (StringBuilder builder : builders) {
                stringBuilder.append(builder);
                if (!hashSet.contains(stringBuilder)){
                    hashSet.add(stringBuilder);
                    System.out.println(stringBuilder);
                }
            }
        }

    }

    private static List<StringBuilder> find(int index, String[] strings, HashSet<Integer> set) {
        while (index++<strings.length){
            for (int j = 0; j < ; j++) {

            }
        }

        /*HashSet<StringBuilder> hashSet = new HashSet<>(strings.length);
        List<StringBuilder> builderList = new ArrayList<>();
        if (set.size()==strings.length-1){
            for (int i = 0; i < strings.length; i++) {
                if (set.contains(i)) {
                    continue;
                }
                builderList.add(new StringBuilder(strings[i]));
                return builderList;
            }
        }
        for (int i = 0; i < strings.length; i++) {
            if (set.contains(i)){
                continue;
            }
            set.add(i);
            StringBuilder stringBuilder = new StringBuilder(strings[i]);
            List<StringBuilder> builders = find(strings,set);
            for (StringBuilder builder : builders) {
                stringBuilder.append(builder);
                if (!hashSet.contains(stringBuilder)){
                    hashSet.add(stringBuilder);
                    builderList.add(stringBuilder);
                }
            }
        }
        return builderList;*/
    }

    public static void main2(String[] args) {
        Vector<Object> objects = new Vector<>();
        objects.get()
        Lock lock = new Lock();
        try {
            lock.lock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

        ReentrantLock reentrantLock = new ReentrantLock();
        boolean b = reentrantLock.tryLock();
        if (b){
            try {

            }finally {
                reentrantLock.unlock();
            }
        }
    }
}
