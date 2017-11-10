package chapter01;

import net.jcip.annotations.NotThreadSafe;

/**
 * 演示了什么是线程不安全的
 * 
 * 这个程序中value++不是一个原子操作，再执行过程中，可能产生相同的值。
 * 
 * 在10万个数据的测试中，已经发现了这个问题，重复次数大约是10个左右。 
 * 
 * @author martin.wang
 *
 */
@NotThreadSafe
class UnsafeSequence{
    private int value;
    
    // 如果要变成线程安全，只要添加synchronized关键字，变成：
    // public synchronized int getNext() {
    public int getNext() {
        return value++;
    }
}

public class UnsafeSequenceDemo {
    public static void main(String[] args) {
        UnsafeSequence us = new UnsafeSequence();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(
                        Thread.currentThread().getName() 
                        + "\t"
                        + us.getNext());;
            }
        };
        for (int i = 0; i < 100000; i++) {
            Thread t = new Thread(runnable);
            t.setName("T" + i);
            t.start();
        }
    }
}
