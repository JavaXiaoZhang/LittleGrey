package mynode;

import java.util.*;

/**
 * @author ZQ
 * @Date 2020/4/10
 */
public class CircleNode {
    public static void main(String[] args) {
        MyNode node1 = new MyNode(1,2);
        MyNode node2 = new MyNode(2,3);
        MyNode node3 = new MyNode(3,4);
        MyNode node4 = new MyNode(4,2);
        MyNode node5 = new MyNode(5,5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node3;

        MyNode meetPoint = isCircle(node1);
        System.out.println("meetPoint:"+meetPoint);

        int circleLength = getCircleLength(node1);
        System.out.println("circleLength:"+circleLength);

        MyNode entryPoint = getCirclePoint(node1);
        System.out.println("entryPoint:"+entryPoint);

    }

    public static MyNode isCircle(MyNode head){
        MyNode p1 = head;
        MyNode p2 = head;
        while (p2 != null && p2.next!=null){
            p1 = p1.next;
            p2 = p2.next.next;
            if (Objects.equals(p1, p2)){
                return p2;
            }
        }
        return null;
    }

    //求环长
    public static int getCircleLength(MyNode head){
        MyNode meetPoint = isCircle(head);
        if (meetPoint == null){
            return 0;
        }
        MyNode p1 = meetPoint;
        MyNode p2 = meetPoint;
        int circleLength = 0;

        while (true){
            p1 = p1.next;
            p2 = p2.next.next;
            circleLength++;
            if (Objects.equals(p1, p2)){
                break;
            }
        }
        return circleLength;
    }


    //求入环点
    public static MyNode getCirclePoint(MyNode head){
        MyNode meetPoint = isCircle(head);
        if (meetPoint == null){
            return null;
        }
        MyNode p1 = head;
        MyNode p2 = meetPoint;
        while (true){
            if (Objects.equals(p1, p2)){
                return p1;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
    }

    public static class MyNode<K, V> implements Map.Entry<K, V> {
        private int hash;
        private K key;
        private V value;
        private MyNode next;

        public void setNext(MyNode next) {
            this.next = next;
        }

        public MyNode(K key, V value) {
            this.hash = hash(key);
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return new StringBuilder("MyNode{").append(
                    "hash=").append(hash).append(
                    ", key=").append(key).append(
                    ", value=").append(value).append(
                    ", next=").append(next.getKey()).append(
                    '}').toString();
        }

        @Override
        public final K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V newValue) {
            V oldValue = this.value;
            this.value = newValue;
            return oldValue;
        }

        @Override
        public final boolean equals(Object o) {

            if (this == o) {
                //地址相同，是同一个对象
                return true;
            }
            if (o instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) o;
                if (Objects.equals(key, entry.getKey()) && Objects.equals(value, entry.getValue())) {
                    return true;
                }
            }
            return false;
        }

        public final int hash(Object key) {
            int h;
            return key == null ? 0 : ((h = key.hashCode()) ^ (h >> 16));
        }
    }
}
