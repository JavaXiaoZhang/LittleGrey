package sort;

import ds.BinaryHeap;

import java.lang.ref.WeakReference;
import java.util.*;

/**
 * @author ZQ
 * @Date 2020/4/10
 */
public class Sort {

    /**
     * 冒泡排序 稳定
     * 平均时间复杂度：O(n²) 最差也是O(n²)  最好O(n)
     * 空间复杂度：O(1)
     * 外层i 控制比较的轮数
     * 里层j 进行比较  每比较完一轮，最后i个数是排序好的
     *
     * 优化：
     *      1；其实可能比较完一轮，最后n个数都是排序好的，设置一个排序的最右边界，最右边界=最后交换的元素位置
     *      2：可能不需要比较就已经是排序好的，比如排序完几轮后数组已经是有序状态，这时设置一个有序标志即可
     *
     *
     * @param array
     */
    public static void bubbleSort(int[] array){
        int lastChangeIndex = 0;
        int sortBorder = array.length-1;
        int temp;
        for (int i = 0; i < array.length-1; i++) {
            //每一轮初始值都是true
            boolean isSorted = true;
            for (int j = 0; j < sortBorder; j++) {
                if (array[j]>array[j+1]){
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    //有进行过元素交换，所以不是有序的
                    isSorted = false;
                    lastChangeIndex = j;
                }
            }
            if (isSorted){
                break;
            }
            sortBorder = lastChangeIndex;
        }
    }

    /**
     * 鸡尾酒排序
     * 
     * @param array
     */
    public static void cocktailSort(int[] array){
        int temp;
        //奇数轮 （第n次而不是i的值）
        for (int i = 0; i < (array.length-1)/2; i++) {
            //每一轮初始值都是true
            boolean isSorted = true;
            for (int j = i; j < array.length-1-i; j++) {
                if (array[j]>array[j+1]){
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    isSorted = false;
                }
            }
            if (isSorted){
                break;
            }

            //偶数轮之前 将isSorted置为true
            isSorted = true;
            for (int j = array.length-1-i; j >i ; j--) {
                if (array[j]<array[j-1]){
                    temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }

    /**
     * 快排--递归方式  不稳定
     * 平均时间复杂度：O(nlogn) 最坏O(n²)
     * 空间复杂度 O(logn)
     */
    public static void quickSort(int[] array, int startIndex, int endIndex){
        //终止条件：startIndex=endIndex
        if (startIndex == endIndex){
            return;
        }
        int pivot = partitionSingle(array, startIndex, endIndex);
        //以基准元素为中心，分两个部分进行排序
        if (pivot>startIndex) {
            quickSort(array, startIndex, pivot - 1);
        }
        if (pivot<endIndex) {
            quickSort(array, pivot + 1, endIndex);
        }
    }

    /**
     * 快排--非递归方式
     */
    public static void quickSortByStack(int[] array, int startIndex, int endIndex){
        //用栈来代替递归的函数栈
        Stack<Map<String, Integer>> quickSortStack = new Stack<>();
        //初始下标入栈
        Map<String, Integer> rootParams = new HashMap<>();
        rootParams.put("startIndex", startIndex);
        rootParams.put("endIndex", endIndex);
        quickSortStack.push(rootParams);

        while (!quickSortStack.isEmpty()){
            Map<String, Integer> params = quickSortStack.pop();
            Integer startIndexParam = params.get("startIndex");
            Integer endIndexParam = params.get("endIndex");
            if (startIndex==endIndex){
                break;
            }
            int pivot = partitionDouble(array, startIndexParam, endIndexParam);
            if (pivot>startIndexParam) {
                Map<String, Integer> leftParams = new HashMap<>();
                leftParams.put("startIndex", startIndex);
                leftParams.put("endIndex", pivot - 1);
                quickSortStack.push(leftParams);
            }
            if (pivot<endIndexParam) {
                Map<String, Integer> rightParams = new HashMap<>();
                rightParams.put("startIndex", pivot + 1);
                rightParams.put("endIndex", endIndex);
                quickSortStack.push(rightParams);
            }

        }

    }


    /**
     * 快排--双边循环
     */
    private static int partitionDouble(int[] array, int startIndex, int endIndex) {
        int left = startIndex;
        int right = endIndex;
        int pivot = array[startIndex];

        while (left!=right){
            while ( left<right && array[right]> pivot ){
                right--;
            }
            while ( left<right && array[left]<= pivot){
                left++;
            }
            //交换元素
            if (left<right){
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
            }
        }
        //pivot和指针重合点交换
        array[startIndex] = array[left];
        array[left] = pivot;
        return left;
    }

    /**
     * 快排--单边循环
     */
    private static int partitionSingle(int[] array, int startIndex, int endIndex) {
        //取基准元素
        int pivot = array[startIndex];
        int mark = startIndex;
        for (int i = startIndex+1; i <= endIndex; i++) {
            if (array[i]<pivot){
                mark++;
                int temp = array[mark];
                array[mark] = array[i];
                array[i] = temp;
            }
        }
        array[startIndex] = array[mark];
        array[mark] = pivot;
        return mark;
    }

    /**
     * 堆排序    不稳定
     *      降序构建最大堆，升序构建最小堆
     *
     *      * 平均时间复杂度：O(nlogn) 最坏O(nlogn)
     *      * 空间复杂度 O(1)
     * @param array
     */
    public static void heapSort(int[] array){
        //构成最小堆
        for (int i = 0; i <=(array.length)/2 ; i++) {
            BinaryHeap.downAdjust(array, i, array.length);
        }
        //循环移动堆顶元素到末尾，并调整二叉堆
        for (int i=array.length-1; i>0; i--){
            //元素互换
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            //调整二叉堆
            BinaryHeap.downAdjust(array, 0, i);
        }
    }

    /**
     * 计数排序  优化后是稳定的
     * 平均时间复杂度：O(n+m)
     * 空间复杂度：O(n)
     *
     * 局限性：1。最大值和最小值不能差距太大  2.只能用于整数排序
     * @param array
     * @return
     */
    public static int[] countSort(int[] array){
        //得到最大值和最小值
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i]>max){
                max = array[i];
            }
            if (array[i]<min){
                min = array[i];
            }
        }
        //统计数组长度
        int length = max - min + 1;
        int[] countArray = new int[length];
        //遍历数组，填充统计数组
        for (int i = 0; i < array.length; i++) {
            countArray[array[i]-min]++;
        }
        //统计数组变形
        for (int i = 1; i < length; i++) {
            countArray[i] += countArray[i-1];
        }
        //输出结果
        int index = 0;
        int[] sortedArray = new int[array.length];
        for (int i = array.length-1; i >= 0; i--) {
            sortedArray[countArray[array[i]-min]-1] = array[i];
            countArray[array[i]-min]--;
        }
        return sortedArray;
    }

    /**
     * 桶排序  用于区间排序  稳定
     * bucketNum=array.length;
     * 区间跨度=(max-min)/(bucketNum-1)
     * 平均时间复杂度：O(n)  最差O(nlogn),还浪费空桶
     * 空间复杂度：O(n)
     * @param array
     * @return
     */
    public static double[] bucketSort(double[] array){
        //得到最大值和最小值
        double max = array[0];
        double min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i]>max){
                max = array[i];
            }
            if (array[i]<min){
                min = array[i];
            }
        }
        double d = max - min;
        //初始化桶
        int bucketNum = array.length;
        ArrayList<LinkedList<Double>> bucketList = new ArrayList<>(bucketNum);
        for (int i = 0; i < bucketNum; i++) {
            bucketList.add(new LinkedList<Double>());
        }

        //遍历原数组，将每个元素放入桶中
        for (int i = 0; i < array.length; i++) {
            int num = (int)((array[i]-min) * (bucketNum-1)/d);
            bucketList.get(num).add(array[i]);
        }

        //堆每个桶内部排序
        for (int i = 0; i < bucketList.size(); i++) {
            Collections.sort(bucketList.get(i));
        }
        double[] sortedArray = new double[array.length];
        int index = 0;
        for (LinkedList<Double> list : bucketList) {
            for (Double element : list) {
                sortedArray[index] = element;
                index++;
            }
        }
        return sortedArray;
    }

    /**
     * 归并排序  稳定
     * 速度仅次于快排
     * timeSort是目前最优版本（对已有序的片段进行判断）
     * @param nums
     * @param l 开始
     * @param h 结束
     * @return
     */
    public static int[] mergeSort(int[] nums, int l, int h) {
        if (l == h) {
            return new int[]{nums[l]};
        }
        int mid = l + (h - l) / 2;
        //左有序数组
        int[] leftArr = mergeSort(nums, l, mid);
        //右有序数组
        int[] rightArr = mergeSort(nums, mid + 1, h);
        //新有序数组
        int[] newNum = new int[leftArr.length + rightArr.length];

        int m = 0, i = 0, j = 0;
        while (i < leftArr.length && j < rightArr.length) {
            newNum[m++] = leftArr[i] < rightArr[j] ? leftArr[i++] : rightArr[j++];
        }
        while (i < leftArr.length) {
            newNum[m++] = leftArr[i++];
        }
        while (j < rightArr.length) {
            newNum[m++] = rightArr[j++];
        }
        return newNum;
    }


    /*public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String target = scanner.next();
        String source = scanner.next();
        int targetLength = target.length();
        int result = source.length();
        if (targetLength==0||result==0){
            System.out.println(-1);
            return;
        }
        for (int i = targetLength-1; i >=0 ; i--) {
            if (result<=0||result<i){
                System.out.println(-1);
                return;
            }
            result = findStr(target.charAt(i), source.substring(0, result));
        }
        System.out.println(result);
    }

    public static int findStr(char a ,String source){
        if (source.length()==0){
            return -1;
        }
        for (int i = source.length()-1; i >= 0; i--) {
            if (a == source.charAt(i)){
                return i;
            }
        }
        return -1;
    }*/

    /*static int lastX;
    static int lastY;
    static int currentX;
    static int currentY;
    static int result;
    static int maxX;
    static int maxY;
    static int max;
    static int c;
    static int r;
    static int[][] ints;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        r = scanner.nextInt();
        c = scanner.nextInt();
        ints = new int[c][r];
        for (int i=0; i<r; i++){
            for (int j=0; j<c; j++){
                ints[j][i] = scanner.nextInt();
            }
        }
        result = ints[0][0];
        while (currentX!=c-1&&currentY!=r-1){
            max = 0;
            find();
        }
        System.out.println(result);
    }

    public static void find(){
        if (currentX+1<c&&currentX+1!=lastX){
            maxX = currentX+1;
            maxY = currentY;
            max = ints[maxX][currentY];
        }
        if (currentX-1>=0&&currentX-1!=lastX){
            if (ints[currentX-1][currentY]>max){
                max = ints[currentX-1][currentY];
                maxX = currentX-1;
                maxY = currentY;
            }
        }
        if (currentY - 1 >= 0 && currentY - 1 != lastY) {
            if (ints[currentX][currentY-1]>max){
                max = ints[currentX][currentY-1];
                maxX = currentX;
                maxY = currentY-1;
            }
        }
        if (currentY + 1 < r && currentY + 1 != lastY) {
            if (ints[currentX][currentY+1]>max){
                max = ints[currentX][currentY+1];
                maxX = currentX;
                maxY = currentY+1;
            }
        }
        lastX = currentX;
        lastY = currentY;
        currentX = maxX;
        currentY = maxY;
        result = max<result?max:result;
    }*/
}


