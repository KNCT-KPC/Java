import java.awt.*;
import java.awt.image.BufferStrategy;

import javax.swing.*;

import java.util.Timer;
import java.util.TimerTask;

public class GameTemplate extends JFrame {
	private static final long serialVersionUID = 1L;
	public static final int SIZE_X = 800;	//アプレットのxサイズ
	public static final int SIZE_Y = 600;	//アプレットのyサイズ	
	public final int INSETS_RIGHT;
	public final int INSETS_TOP;
	
	private BufferStrategy bstrategy;
	
	public GameTemplate()
	{
		ImageMgr.loadImage(this);	//画像ファイルをロード
		FontMgr.loadFont();;
		
		//サイズをセット
		this.setVisible(true); //可視化
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//ウィンドウが閉じられたら、プロセス終了
		this.setBackground(Color.BLACK); //バックグラウンドの色設定
		this.setResizable(false); //サイズ変更禁止
		
		Insets insets = this.getInsets(); //利用可能領域の計算に使う
		this.setSize(SIZE_X+insets.right+insets.left,SIZE_Y+insets.top+insets.bottom);
		this.setLocationRelativeTo(null);
		INSETS_RIGHT = insets.left;
		INSETS_TOP = insets.top;
		
		this.setIgnoreRepaint(true);
		this.createBufferStrategy(2);
		bstrategy = this.getBufferStrategy();
		
		DrawLibrary.initialize(this);
		Mouse.initialize(INSETS_RIGHT, INSETS_TOP);
		addMouseListener(Mouse.getInstance());
		addMouseMotionListener(Mouse.getInstance());			
		
		Timer t = new Timer();
		t.schedule(new GameMain(), 10, 16);
	}
	
	public static void main(String[] args){
		new GameTemplate();
	}
	
	class GameMain extends TimerTask{
		public void Update(){
			
		}
		public void Draw(){
			DrawLibrary dLib = DrawLibrary.getInstance();
			FontMgr fMgr = FontMgr.getInstance();
			Mouse m = Mouse.getInstance();
			
			dLib.drawCircle(100+m.getLeftCount(), 100, 20, new Color(255,255,255), false);
			dLib.drawString(10,  5, "X:"+m.getX(), new Color(255,255,255), fMgr.getFont("SYSTEM"), true);
			dLib.drawString(100, 5, "Y:"+m.getY(), new Color(255,255,255), fMgr.getFont("SYSTEM"), true);
			dLib.drawString(10,  30, "L:"+m.getLeftCount(), new Color(255,255,255), fMgr.getFont("SYSTEM"), true);
			dLib.drawString(100, 30, "R:"+m.getRightCount(), new Color(255,255,255), fMgr.getFont("SYSTEM"), true);
		}
		
		public void run(){
			FPS.getInstance().update();
			Mouse.getInstance().update();
			
			Update();
			if(DrawLibrary.getInstance().startDraw(bstrategy)){
				Draw();
				FPS.getInstance().draw();
				DrawLibrary.getInstance().endDraw(bstrategy);
			}			
			
			FPS.getInstance().waitF();				
		}
	}
}