import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {

    // TODO: 创建自己的数据
    private SelectionSortData  data;        // 数据
    private AlgoFrame frame;    // 视图

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N){

        // 初始化数据
        // TODO: 初始化数据
        data = new SelectionSortData(N, sceneHeight);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Selection-Sort-Visulization", sceneWidth, sceneHeight);
            // TODO: 根据情况决定是否加入键盘鼠标事件监听器
//            frame.addKeyListener(new AlgoKeyListener());
//            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run(){

        // TODO: 编写自己的动画逻辑

        setData(0, -1, -1);

        for (int i = 0; i < data.N(); i++) {
            int minIndex = i;
            setData(i, minIndex, -1);
            for (int j = i+1; j < data.N(); j++) {
                setData(i, minIndex, j);
                if (data.get(j) < data.get(minIndex)) {
                    minIndex = j;
                    setData(i, minIndex, j);
                }
            }
            data.swap(i, minIndex);
            setData(i+1, -1, -1);
        }

       setData(data.N(), -1, -1);
    }

    private void setData(int orderedIndex, int currentMinIndex, int currentCompareIndex) {
        data.orderedIndex = orderedIndex;
        data.currentMinIndex = currentMinIndex;
        data.currentCompareIndex = currentCompareIndex;

        frame.render(data);
        AlgoVisHelper.pause(10);
    }
    // TODO: 根据情况决定是否实现键盘鼠标等交互事件监听器类
    private class AlgoKeyListener extends KeyAdapter{ }
    private class AlgoMouseListener extends MouseAdapter{ }

    public static void main(String[] args) {

        int sceneWidth = 700;
        int sceneHeight = 700;
        int N = 100;

        // TODO: 根据需要设置其他参数，初始化visualizer
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}
