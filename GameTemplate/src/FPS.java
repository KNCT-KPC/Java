import java.awt.Color;
import java.text.DecimalFormat;


public class FPS {
	//====================================//
	//�@�@�@�V���O���g���p�^�[���L�q�@�@�@//
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
	//�@�@�@�t�B�[���h��`�@�@�@//
	//==========================//	
	private long mStartTime = 0;
	private long mCount = 0;
	private double mFps = 0;	
	private final int N = 60;
	private final int FPS = 60;

	//============================//
	//�@�@�@�O�����\�b�h��`�@�@�@//
	//============================//	
	public boolean update(){
		if( mCount == 0 ){ //1�t���[���ڂȂ玞�����L��
			mStartTime = System.currentTimeMillis();
		}
		if( mCount == N ){ //60�t���[���ڂȂ畽�ς��v�Z����
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
		long tookTime = System.currentTimeMillis() - mStartTime;	//������������
		long waitTime = mCount*1000/FPS - tookTime;	//�҂ׂ�����

		if( waitTime > 0 ){
			try {
				Thread.sleep((int)waitTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
}
