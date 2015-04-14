import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferStrategy;
import java.util.Timer;
import java.util.TimerTask;

public class GameMain extends Canvas {
	private static final long serialVersionUID = 1L;	
	public BufferStrategy bstrategy;
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;

	public GameMain(){
		setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
	}

	public void start(){
		ImageMgr.loadImage(this);
		FontMgr.loadFont();
		
		createBufferStrategy(2);
		bstrategy = getBufferStrategy();
		this.setIgnoreRepaint(true);		
		
		Mouse.initialize();
		DrawLibrary.initialize(this);
		
		addMouseListener(Mouse.getInstance());
		addMouseMotionListener(Mouse.getInstance());			
		
		Timer t = new Timer();
		t.schedule(new GameTask(), 10, 16);
	}

	class GameTask extends TimerTask{
		public void Update(){
			
		}
		
		int rotate = 0;
		public void Draw(){
			DrawLibrary dLib = DrawLibrary.getInstance();
			FontMgr fMgr = FontMgr.getInstance();
			Mouse m = Mouse.getInstance();
			
			dLib.drawCircle(100+m.getLeftCount(), 100, 20, new Color(255,255,255), false);
			dLib.drawString(10,  5, "X:"+m.getX(), new Color(255,255,255), fMgr.getFont("SYSTEM"), true);
			dLib.drawString(100, 5, "Y:"+m.getY(), new Color(255,255,255), fMgr.getFont("SYSTEM"), true);
			dLib.drawString(10,  30, "L:"+m.getLeftCount(), new Color(255,255,255), fMgr.getFont("SYSTEM"), true);
			dLib.drawString(100, 30, "R:"+m.getRightCount(), new Color(255,255,255), fMgr.getFont("SYSTEM"), true);
			dLib.drawImageRotate(200, 200, ImageMgr.getInstance().getImage("TEST"), (float)((++rotate)/360.0*Math.PI), true);
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