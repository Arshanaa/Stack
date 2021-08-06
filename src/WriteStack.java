public class WriteStack implements Runnable{
    private StackAccess stackAccess;
    private Integer value;

    public WriteStack(StackAccess stackAccess){
        StackAccess stackAccess1 = this.stackAccess;
    }

    @Override
    public void run(){
        try{
            for(int i=0; i<10; i++){
                Thread.sleep(20);
                value = stackAccess.pop();
            }
        }catch (InterruptedException e){

        }
    }

    public Integer getValue(){
        return value;
    }
}