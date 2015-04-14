import javax.swing.JApplet;
import javax.swing.JFrame;
 
public class Program extends JApplet {
	private static final long serialVersionUID = 1L;
    public void init(){
        GameMain gameMain = new GameMain();
        this.add(gameMain);
        gameMain.start();
    }
 
    public static void main(String[] args){
        JFrame mainFrame = new JFrame();
        GameMain mainCanvas = new GameMain();
        mainFrame.setTitle("kanzume project");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
 
        mainFrame.getContentPane().add(mainCanvas);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainCanvas.start();        
    }
 
}