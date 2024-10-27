package ru.ssau.tk.jabalab.lr2.concurrent;
import ru.ssau.tk.jabalab.lr2.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.jabalab.lr2.functions.SqrFunction;
import ru.ssau.tk.jabalab.lr2.functions.TabulatedFunction;
import ru.ssau.tk.jabalab.lr2.functions.ConstantFunction;
public class ReadWriteTaskExecutor {
    public static void main(String[] args) {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(new ConstantFunction(-1),1,1000,1000);
        Thread thrReadTask = new Thread(new ReadTask(function));
        Thread thrWriteTask = new Thread(new WriteTask(function,0.5));

        thrWriteTask.start();
        thrReadTask.start();
    }
}
