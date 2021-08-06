import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestStack {
    public static void main(String[] args){
        StackAccess stackAccess = new StackAccess();
        ReadStack read = new ReadStack(stackAccess);
        WriteStack write = new WriteStack(stackAccess);
        Peek peek = new Peek(stackAccess);
        Thread read1 = new Thread(read);
        Thread read2 = new Thread(read);
        Thread write1 = new Thread(read);
        Thread write2 = new Thread(read);
        Thread peek1 = new Thread(read);
        Thread peek2 = new Thread(read);

        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(new ReadStack(stackAccess));
        executor.execute(new WriteStack(stackAccess));
        executor.execute(new ReadStack(stackAccess));
        executor.execute(new WriteStack(stackAccess));
        executor.execute(new ReadStack(stackAccess));
        executor.shutdown();
    }



}