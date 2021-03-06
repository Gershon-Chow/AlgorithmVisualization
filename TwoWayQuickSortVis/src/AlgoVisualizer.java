import java.awt.*;

public class AlgoVisualizer {

    // TODO: 创建自己的数据
    private TwoWayQuickSortData data;        // 数据
    private AlgoFrame frame;    // 视图

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N){

        // 初始化数据
        // TODO: 初始化数据
        data = new TwoWayQuickSortData(N, sceneHeight);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Quick-Sort-Visulization", sceneWidth, sceneHeight);
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
        setData(-1, -1, -1, -1, -1, -1);
        quickSort2Ways(0, data.N()-1);
        setData(-1, -1, -1, -1, -1, -1);
    }

    private void quickSort2Ways(int l, int r) {
        if (l > r)
            return;
        if (l == r) {
            setData(-1, -1, l, -1, -1, -1);
            return;
        }

        setData(l ,r, -1, -1, -1, -1);
        int p = partition(l, r);
        quickSort2Ways(l, p-1);
        quickSort2Ways(p+1, r);
    }

    private int partition(int l, int r) {
        //优化数组几乎有序的情况
        int p = (int)(Math.random()*(r-l+1)) + l;
        setData(l, r, -1, p, -1, -1);

        data.swap(l, p);
        int v = data.get(l);
        setData(l, r, -1, l, -1, -1);

        int i = l+1, j = r;
        setData(l, r, -1, l, i, j);

        while (true) {
            while (i <= r && data.get(i) < v) {
                i++;
                setData(l, r, -1, l, i, j);
            }
            while (j >= l+1 && data.get(j) > v) {
                j--;
                setData(l, r, -1, l, i, j);
            }
            if (i > j)
                break;

            data.swap(i, j);
            i++;
            j--;
            setData(l, r, -1, l, i, j);
        }
        data.swap(l, j);
        setData(l, r, j, -1, -1, -1);
        return j;
    }

    private void setData(int l, int r, int fixedPivot, int curPivot, int curL, int curR) {
        data.l = l;
        data.r = r;
        if (fixedPivot != -1)
            data.fixedPivot[fixedPivot] = true;
        data.currenPivot = curPivot;
        data.currentL = curL;
        data.currentR = curR;

        frame.render(data);
        AlgoVisHelper.pause(10);
    }


    public static void main(String[] args) {

        int sceneWidth = 700;
        int sceneHeight = 700;
        int N = 100;

        // TODO: 根据需要设置其他参数，初始化visualizer
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}
