package array;

/**
 * 斐波那切数列
 * @author boss
 */
public class FbnqList {

    public int Fbnq(int n){
        int res = 0;
        if(n==0||n==1) { res=1; }

        if (n>1){ res=Fbnq(n-1)+Fbnq(n-2); }
        return res;
    }

    public static void main(String[] args) {
        FbnqList fbnqList = new FbnqList();
        System.out.println(fbnqList.Fbnq(5));
    }
}
