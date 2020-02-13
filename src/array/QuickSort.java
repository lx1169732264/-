package array;

import java.util.Arrays;

/**
 * 快速排序
 * @author boss
 */
public class QuickSort {

	public static void main(String[] args) {
		int[] arr = new int[] {3,4,6,7,2,7,2,8,0,9,1};
		quickSort(arr,0,arr.length-1);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void quickSort(int[] arr,int start,int end) {
		if(start<end) {
			//把数组中的第0个数字做为标准数
			int stard=arr[start];
			//记录需要排序的下标
			int low=start;
			int high=end;
			//循环找比标准数大的数和比标准数小的数
			while(low<high) {
				//高位>低位 && 高位>标准数	不用动,只移下标
				while(low<high&&stard<=arr[high]) {
					high--;
				}
				//小于标准数的被移到左边
				arr[low]=arr[high];
				//如果低位比标准数小 && 低位<标准数	不用动,只移下标
				while(low<high&&arr[low]<=stard) {
					low++;
				}
				//大于标准数的移动到右边
				arr[high]=arr[low];
			}
			//第一轮排序结束,高低位下标重叠
			//把标准数赋给当前下标
			arr[low]=stard;
			//处理所有的小的数字
			quickSort(arr, start, low);
			//处理所有的大的数字
			quickSort(arr, low+1, end);
		}
	}

}
