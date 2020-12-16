package temp;

public class Test {
    public int[] test(int[] nums) {
        //index非0的下标
        int index = 0;

        for (int i = 0; i < nums.length; i++) {

            //非0移动到左
            if (0 != nums[i]) {
                nums[index] = nums[i];
                index++;
            }
        }

        //对于left之后的元素都为0
        while (index < nums.length) {
            nums[index] = 0;
            index++;
        }

        return nums;
    }
}