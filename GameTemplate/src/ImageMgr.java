

import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.util.HashMap;

import javax.swing.JFrame;

/*
	イメージデータを管理するマネージャクラス
*/
public class ImageMgr {	
	//=========================//
	//　　　ImageData配列　　　//
	//=========================//
	private final ImageData[] imgDataAry = new ImageData[]{
		//new ImageData(パス, 登録名),
	};		
	
	//==========================//
	//　　　内部クラス定義　　　//
	//==========================//
	//画像情報を表すクラス
	private class ImageData{
		public final String pass;	//画像へのパス
		public final String name;	//画像の名前
		
		//コンストラクタ
		public ImageData(String pass, String name){
			this.name = name;
			this.pass = pass;
		}
	}
	
	//====================================//
	//　　　シングルトンパターン記述　　　//
	//====================================//
	private static ImageMgr instance = null;	//自身のインスタンス(null初期化

	//内部コンストラクタ
	private ImageMgr(GameMain jap){
		this.jap = jap;
		
		MediaTracker tracker = new MediaTracker(jap);	//メディアトラッカー
		Toolkit tk = Toolkit.getDefaultToolkit();
		int i = 1;
		for(ImageData data : imgDataAry){
			Image img = tk.createImage(data.pass);
			tracker.addImage(img, i);
			
			imgMap.put(data.name, img);
			i++;
		}
		
	    try {
	    	tracker.waitForAll();
	    } catch (InterruptedException e) {}
	}
	
	//インスタンス作成メソッド
	public static void loadImage(GameMain jap){
		if(instance == null){
			instance = new ImageMgr(jap);
		}
	}
	
	//インスタンス取得メソッド
	public static ImageMgr getInstance(){
		return instance;	//メンバである自身のインスタンスを返す
	}
	
	
	//==========================//
	//　　　フィールド定義　　　//
	//==========================//
	private GameMain jap;
	private HashMap<String, Image> imgMap = new HashMap<String, Image>();
	
	//============================//
	//　　　外部メソッド定義　　　//
	//============================//
	//nameにマッチするフォントオブジェクトを返すをメソッド
	public Image getImage(String name){
		return imgMap.get(name);
	}
	
	/**
	* 画像を分割して配列へセットするメソッド
	* @param img Imageオブジェクト
	* @param x_num x分割数
	* @param y_num y分割数
	* @param width 分割イメージの横幅
	* @param height 分割イメージの縦幅
	**/
	public Image[] getDivImage(Image img, int x_num, int y_num, int width, int height){
		Image[] divImg = new Image[x_num*y_num];
			//イメージ配列を生成

		for(int x = 0; x < x_num; x++){
			for(int y = 0; y < y_num; y++){
				CropImageFilter cfilter = new CropImageFilter(x*width, y*height, width, height);
					//Cropフィルターを生成
				FilteredImageSource producer = new FilteredImageSource(img.getSource(),cfilter);
					//フィルタイメージのプロデューサーを生成
				divImg[y + y_num*x] = jap.createImage(producer);
					//プロデューサーに従って新しいイメージを生成、配列へセット
			}
		}
		
		return divImg;
	}
}
