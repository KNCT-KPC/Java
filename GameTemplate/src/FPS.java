import java.awt.Color;
import java.text.DecimalFormat;


public class FPS {
	//====================================//
	//　　　シングルトンパターン記述　　　//
	//====================================//
	private static FPS instance = null;
	private FPS(){};
	
	public static FPS getInstance(){
		if(instance == null){
			instance = new FPS();
		}
		
		return instance;
	}
	
	//==========================//
	//　　　フィールド定義　　　//
	//==========================//	
	private long mStartTime = 0;
	private long mCount = 0;
	private double mFps = 0;	
	private final int N = 60;
	private final int FPS = 60;

	//============================//
	//　　　外部メソッド定義　　　//
	//============================//	
	public boolean update(){
		if( mCount == 0 ){ //1フレーム目なら時刻を記憶
			mStartTime = System.currentTimeMillis();
		}
		if( mCount == N ){ //60フレーム目なら平均を計算する
			long t = System.currentTimeMillis();
			mFps = 1000.f/((t-mStartTime)/(float)N);
			mCount = 0;
			mStartTime = t;
		}
		mCount++;
		return true;
	}

	public void draw(){
		DrawLibrary dLib = DrawLibrary.getInstance();
		FontMgr fMgr = FontMgr.getInstance();
		DecimalFormat df1 = new DecimalFormat("0.00");	
		
		dLib.drawString(740, 0, df1.format(mFps)+"", new Color(255,255,255), fMgr.getFont("SYSTEM"), true);
	}
	
	public void waitF(){
		long tookTime = System.currentTimeMillis() - mStartTime;	//かかった時間
		long waitTime = mCount*1000/FPS - tookTime;	//待つべき時間

		if( waitTime > 0 ){
			try {
				Thread.sleep((int)waitTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
}
