import java.awt.*;
import javax.swing.*;

public class AlgoFrame extends JFrame{

    private int canvasWidth;
    private int canvasHeight;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight){

        super(title);

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();
        setContentPane(canvas);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setVisible(true);
    }

    public AlgoFrame(String title){

        this(title, 1024, 768);
    }

    public int getCanvasWidth(){return canvasWidth;}
    public int getCanvasHeight(){return canvasHeight;}

    // TODO: 设置自己的数据
    private MineSweeperData data;
    public void render(MineSweeperData data){
        this.data = data;
        repaint();
    }

    private class AlgoCanvas extends JPanel{

        public AlgoCanvas(){
            // 双缓存
            super(true);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D)g;

            // 抗锯齿
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.addRenderingHints(hints);

            // 具体绘制
            int w = canvasWidth / data.getM();
            int h = canvasHeight / data.getN();
            for(int i = 0; i < data.getN(); i++)
                for (int j = 0; j < data.getM(); j++) {

                    if (data.open[i][j]) {
                        if (data.isMine(i, j))
                            AlgoVisHelper.putImage(g2d, j * w, i * h, MineSweeperData.mineImageUrl);
                        else
                            AlgoVisHelper.putImage(g2d, j*w, i*h, MineSweeperData.numberImageUrl(data.getNumbers(i, j)));
                    } else {
                        if (data.flag[i][j])
                            AlgoVisHelper.putImage(g2d, j * w, i * h, MineSweeperData.flagImageUrl);
                        else
                            AlgoVisHelper.putImage(g2d, j*w, i*h, MineSweeperData.blockImageUrl);
                    }

                }
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}


