package org.example.cas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class TestAtomicArray {


    public static void main(String[] args) {
        demo(
                ()->new int[10],
                (array)-> array.length,
                (array,index)-> array[index]++,
                (array)-> System.out.println(Arrays.toString(array))
        );

        demo(
                ()->new AtomicIntegerArray(10),
                (array)-> array.length(),
                (array,index)-> array.getAndIncrement(index),
                (array)-> System.out.println(array)
        );
    }

    /*
    * 函数式接口
    * 1.supplier 提供者 ()-> 结果
    * 2.function 函数  (参数)-> 结果 BiFunction (参数1,参数2)-> 结果
    * 3.consumer 消费者 (参数)-> void， BiConsumer (参数1,参数2)-> void
    */

    private static <T> void demo(Supplier<T> arraySupplier,
                                 Function<T, Integer> lengthFun,
                                 BiConsumer<T,Integer> putConsumer,
                                 Consumer<T> pointConsumer) {
        List<Thread> ts = new ArrayList<>();

        T array = arraySupplier.get();
        int length = lengthFun.apply(array);
        for (int i = 0; i < length; i++) {
            ts.add(new Thread(()-> {
                for (int j = 0; j < 10000; j++) {
                    putConsumer.accept(array, j%length);
                }
            }, "t" + i));
        }

        ts.forEach(Thread::start);
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
