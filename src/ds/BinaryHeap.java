package ds;

import java.util.Arrays;

/**
 * 二叉堆的特性：
 *      1.本质上是一种完全二叉树
 *      2.分最大堆和最小堆
 *      3.最大堆的任一父节点的值都大于或等于子节点的值
 *      4.最小堆的父节点的值都小于或等于子节点的值
 *      5.二叉堆的根节点叫堆顶，堆顶整个堆的最大元素或最小元素
 *
 * @author ZQ
 * @Date 2020/6/27
 */
public class BinaryHeap {
    /**
     * 上浮调整
     */
    public static void upAdjust(int[] array){
        int childIndex = array.length-1;
        int parentIndex = (childIndex - 1) / 2;
        //temp保存上浮节点的值，并用于最后的赋值
        int temp = array[childIndex];
        while (childIndex>0 && temp<array[parentIndex]){
            //单向赋值即可，上浮节点已赋值给temp
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (parentIndex-1)/2;
        }
        array[childIndex] = temp;
    }

    /**
     * 下沉调整
     */
    public static void downAdjust(int[] array, int parentIndex, int length){
        int childIndex = 2*parentIndex+1;
        //temp保存上浮节点的值，并用于最后的赋值
        int temp = array[parentIndex];
        while (childIndex<length){
            if (childIndex+1<length && array[childIndex+1]<array[childIndex]){
                childIndex++;
            }
            if (temp<=array[childIndex]){
                break;
            }
            //单向赋值即可，上浮节点已赋值给temp
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2*parentIndex+1;
        }
        array[parentIndex] = temp;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1,2,0,9,7,8,4,6};
        for (int i = 0; i <=(array.length)/2 ; i++) {
            downAdjust(array, i, array.length);
        }
        System.out.println(Arrays.toString(array));
    }
}
