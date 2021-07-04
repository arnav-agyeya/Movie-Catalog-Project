public class PowerFunction {


    public static void main(String[] args) {
        int a = 2;
        int b = 5;
        int res = getAToPowerB(a,b);
        System.out.println(res);
    }

    private static int getAToPowerB(int a, int b) {

        if(b==0)
            return 1;

        int res = 1;
        if(b%2 == 0){
            res = getAToPowerB(a, b/2);
            res = res*res;
        }
        else{
            res = getAToPowerB(a, b-1);
            res = res*a;
        }

        return res;
    }
}
