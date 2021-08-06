import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class StackAccess {
    Stack<Integer> stack = new Stack<>();
    private int size = 6;
    ReentrantLock lock = new ReentrantLock();
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    Condition stackEmptyCondition = lock.newCondition();
    Condition stackFullCondition = lock.newCondition();

    public void push(Integer item){
        try{
            lock.lock();
            int CAPACITY = 0;
            while (stack.size() == CAPACITY) {
                System.out.format("Stack is full: %d. Waiting to push %d",stack.size(),item);
                stackFullCondition.await();
            }
            stack.push(item);
            System.out.println("Thread " + Thread.currentThread().getId() + "push" +item);
            stackEmptyCondition.signalAll();
        }catch (InterruptedException e){

        }finally{
            lock.unlock();
        }
    }
    public Integer pop(){
        try{
            lock.lock();
            while (stack.size() == 0){
                System.out.format("Stack is empty: %d. Waiting to pop %d", stack.size());
                stackEmptyCondition.await();
            }
            Integer value = stack.pop();
            stackFullCondition.signalAll();
            System.out.println("Thread " + Thread.currentThread().getId() + "pop" + value);
            return value;
        }catch (InterruptedException e){

        }finally {
            stackFullCondition.signalAll();
            lock.unlock();
        }
        return null;

    }
}