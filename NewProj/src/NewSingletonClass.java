public class NewSingletonClass {

    private static NewSingletonClass instance;

    private NewSingletonClass() {
    }

    public static NewSingletonClass getInstance(){

        if(instance == null){
            synchronized (NewSingletonClass.class){
                if(instance == null){
                    instance = new NewSingletonClass();
                }
            }
        }

        return instance;
    }
}
