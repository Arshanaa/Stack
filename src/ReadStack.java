public class ReadStack implements Runnable{
    private StackAccess stackAccess;
    private Integer value;

    public ReadStack(StackAccess stackAccess){
        StackAccess stackAccess1 = this.stackAccess;
    }

    @Override
    public void run(){
        try{
            for(int i=0; i<5; i++){
                Thread.sleep(50);
                value = stackAccess.pop();
            }
        }catch (InterruptedException e){

        }
    }

    public Integer getValue(){
        return value;
    }
}