package lc_0100;

public class Lc_0007_reverse {
    /**
     * 方法1  字符串反转
     * @param x
     * @return
     */
    public static int reverse(int x) {
        StringBuffer s = new StringBuffer(x+"");
        String res;
        if (s.charAt(0)=='-'){
            //删除符号,方便进行reverse反转StringBuffer
            s.deleteCharAt(0);
            res = "-"+s.reverse();
        }else {
            res = ""+s.reverse();
        }
        //string转换int
        // 题目要求的存储范围和int是32位,取值范围: -2^31——2^31-1 大概21e
        //19 9999 9999转换后变成99e,超过int表示范围,要用Long来判断是否超过存储范围
        long i = Long.parseLong(res);
        if (i>Integer.MAX_VALUE||i<Integer.MIN_VALUE){
            return 0;
        }
        return (int)i;
    }

    /**
     * 方法2  按位取数
     * @param x
     */
    public static int reverse1(int x){
        int res=0;
        while (x!=0){
            //pop为x的余数
            int pop = x%10;
            x/=10;
            //存储位数为32位,取值范围: -2^31——2^31-1,正数时,尾数不能>7
            // 负数时,尾数<-8,就会进位
            if (res > Integer.MIN_VALUE/10 || (res == Integer.MIN_VALUE / 10 && pop > 7)) { return 0;}
            if (res < Integer.MIN_VALUE/10 || ( res== Integer.MIN_VALUE / 10 && pop < -8)) {return 0;}
            res = pop +res*10;
        }
        return res;
    }

    public static void main(String[] args) {
        int result = reverse(199999999);
        System.out.println(result);
    }
}
