import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * callable 是创建线程的一种方式，它相比与Thread,Runnable而言，它最大的不同是 它可以获取执行完后的结果
 */

class callable implements Callable<Integer> {

    //重写里面的call方法
    @Override
    public Integer call() throws Exception {
        System.out.println("我是callable:" + Thread.currentThread().getName());
        return 1024;
    }
}

//runnable
class runnable implements Runnable {

    @Override
    public void run() {

        System.out.println("我是runnable" + Thread.currentThread().getName());
    }
}


public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Main main = new Main();
        main.testRunnable();
        main.testCallable();


        System.out.println("我是main线程");


    }

    //使用join方法，可以保持线程的执行顺序
    public void testRunnable() throws InterruptedException {
        //runnable
        runnable runnable = new runnable();
        Thread runnableThread = new Thread(runnable, "runnable");
        runnableThread.start();
        // runnableThread.join();
    }


    public void testCallable() throws InterruptedException, ExecutionException {
        callable callable = new callable();
        FutureTask<Integer> futureTask = new FutureTask<>(callable);

        //同一个futureTask ，如果有两个线程，只会执行一个。
        Thread callableThread = new Thread(futureTask, "A");
        callableThread.start();
        // callableThread.join();
        // new Thread(futureTask,"AB").start();


        //如果想执行两个线程，则创建不同的实例对象
        // FutureTask<Integer> futureTask1 = new FutureTask<>(callable);
        // new Thread(futureTask1,"B").start();
        //获取返回值
        // System.out.println(futureTask.get());

    }
}
