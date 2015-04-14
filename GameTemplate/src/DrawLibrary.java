import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;

/*
	描画動作を提供するライブラリ
 */
public class DrawLibrary{
	//シングルトンで記述
	private static DrawLibrary instance = null;	//インスタンス
	private DrawLibrary(GameMain jf){
		this.gm = jf;
	}
	public static DrawLibrary getInstance(){
		return instance;
	}
	public static void initialize(GameMain jf){
		instance = new DrawLibrary(jf);
	}
	
	private GameMain gm;	//ビュー_
	private Graphics2D g2d;	//Graphics2Dオブジェクト
	
	public boolean startDraw(BufferStrategy bstrategy){
		if(bstrategy.contentsLost() == true){
			return false;
		}
		this.g2d = (Graphics2D)bstrategy.getDrawGraphics();
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect(0, 0, GameMain.SCREEN_WIDTH, GameMain.SCREEN_HEIGHT);
		return true;
	}
	public void endDraw(BufferStrategy bstrategy){
		bstrategy.show();
		g2d.dispose();
	}
	
	/**
	* 画像を描画するメソッド
	* @param img Imageオブジェクト
	* @param x 描画x位置
	* @param y 描画y位置
	**/
	public void drawLine(int x1, int y1, int x2, int y2, Color c){
		g2d.setColor(c);
		g2d.drawLine(x1, y1, x2, y2);
	}
	/**
	* 画像を描画するメソッド
	* @param img Imageオブジェクト
	* @param x 描画x位置
	* @param y 描画y位置
	**/
	public void drawImage(int x, int y, Image img){
		g2d.drawImage(img, x, y, gm);
	}
	/**
	* アルファブレンドで画像を描画 
	* @param x 描画x位置
	* @param y 描画y位置
	* @param img Imageオブジェクト
	* @param param アルファパラメータ(0.0~1.0)
	**/
	public void drawImageAlphaBlend(int x, int y, Image img, float param){
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, param));	//アルファチャンネルの値をセット
		g2d.drawImage(img, x, y, gm);		//画像を描画
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));		//アルファチャンネルの値をリセット
	}

	/**
	* 円をアルファブレンドで描画 
	* @param x 描画中心x位置
	* @param y 描画中心y位置
	* @param radius 半径
	* @param color Colorオブジェクト
	* @param fill 塗りつぶすかどうか
	**/
	public void drawCircle(int x, int y, int radius, Color color, boolean fill){
		int rx = x - radius;
		int ry = y - radius;
		
		Color temp = g2d.getColor();	//色を記憶
		g2d.setColor(color);	//引数の色をセット
		
		if(fill) g2d.fillOval(rx, ry, radius*2, radius*2);	//ぬりつぶすならfillRectメソッドを、
		else g2d.drawOval(rx, ry, radius*2, radius*2);		//ぬりつぶさないならdrawRectメソッドを呼び出す

		g2d.setColor(temp);		//色をリセット
	}
	
	/**
	* 円をアルファブレンドで描画 
	* @param x 描画中心x位置
	* @param y 描画中心y位置
	* @param radius 半径
	* @param color Colorオブジェクト
	* @param fill 塗りつぶすかどうか
	* @param param アルファパラメータ(0.0~1.0)
	**/
	public void drawCircleAlphaBlend(int x, int y, int radius, Color color, boolean fill, float param){
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, param));	//アルファチャンネルの値をセット

		int rx = x - radius;
		int ry = y - radius;                                                                                                                                                                                                                    
		
		Color temp = g2d.getColor();	//色を記憶
		g2d.setColor(color);	//引数の色をセット
		
		if(fill) g2d.fillOval(rx, ry, radius*2, radius*2);	//ぬりつぶすならfillRectメソッドを、
		else g2d.drawOval(rx, ry, radius*2, radius*2);		//ぬりつぶさないならdrawRectメソッドを呼び出す

		g2d.setColor(temp);		//色をリセット
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));		//アルファチャンネルの値をリセット
	}
	
	/**
	* 四角形を描画 
	* @param x 描画x位置
	* @param y 描画y位置
	* @param width 四角形の横幅
	* @param height 四角形の縦幅
	* @param color Colorオブジェクト
	* @param fill 塗りつぶすかどうか
	**/
	public void drawRect(int x, int y, int width, int height, Color color, boolean fill){
		Color temp = g2d.getColor();	//色を記憶
		g2d.setColor(color);	//引数の色をセット
		
		if(fill) g2d.fillRect(x, y, width, height);	//ぬりつぶすならfillRectメソッドを、
		else g2d.drawRect(x, y, width, height);		//ぬりつぶさないならdrawRectメソッドを呼び出す

		g2d.setColor(temp);		//色をリセット
	}
	
	/**
	* アルファブレンドで四角形を描画 
	* @param x 描画x位置
	* @param y 描画y位置
	* @param width 四角形の横幅
	* @param height 四角形の縦幅
	* @param fill 塗りつぶすかどうか
	* @param param アルファパラメータ(0.0~1.0)
	**/
	public void drawRectAlphaBlend(int x, int y, int width, int height, Color color, boolean fill, float param){
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, param));	//アルファチャンネルの値をセット

		Color temp = g2d.getColor();	//色を記憶
		g2d.setColor(color);	//引数の色をセット
		
		if(fill) g2d.fillRect(x, y, width, height);	//ぬりつぶすならfillRectメソッドを、
		else g2d.drawRect(x, y, width, height);		//ぬりつぶさないならdrawRectメソッドを呼び出す

		g2d.setColor(temp);		//色をリセット
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));		//アルファチャンネルの値をリセット
	}
	
	/**
	* 画像を回転して描画 （画像の中心座標を指定）
	* @param x 描画中心x位置（回転の支点）
	* @param y 描画中心y位置（回転の支点）
	* @param img Imageオブジェクト
	* @param angle 回転ラジアン角
	**/
	public void drawImageRotate(int x, int y, Image img, float angle, boolean inter){
		if(inter) g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		g2d.rotate(angle, x, y);	//座標を回転
		g2d.drawImage(img, x-img.getWidth(gm)/2, y-img.getHeight(gm)/2, gm);	//画像を描画
		g2d.setTransform(new AffineTransform());
		
		if(inter) g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
	}
	
	/**
	* 画像を拡縮して描画 （画像の中心座標を指定）
	* @param x 描画中心x位置（拡縮の支点）
	* @param y 描画中心y位置（拡縮の支点）
	* @param img Imageオブジェクト
	* @param rate 拡縮倍率
	* @param inter 補間を行うかどうか
	**/
	public void drawImageExtend(int x, int y, Image img, float rate, boolean inter){
		int ex_w = (int)(img.getWidth(gm)*rate);
		int ex_h = (int)(img.getHeight(gm)*rate);
		
		if(inter) g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			//バイリニア補間で描画
		g2d.drawImage(img, x-ex_w/2, y-ex_h/2, ex_w, ex_h, gm);	//画像を拡大描画
		if(inter) g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
			//ニアレスト補間に戻す
		
	}

	/**
	* 画像を拡縮・回転して描画 （画像の中心座標を指定）
	* @param x 描画中心x位置（拡縮・回転の支点）
	* @param y 描画中心y位置（拡縮・回転の支点）
	* @param img Imageオブジェクト
	* @param angle 回転ラジアン角
	* @param rate 拡縮倍率
	* @param inter 補間を行うかどうか
	**/
	public void drawImageRotateExtend(int x, int y, Image img, float angle, float rate, boolean inter){
		int ex_w = (int)(img.getWidth(gm)*rate);
		int ex_h = (int)(img.getHeight(gm)*rate);
		
		if(inter) g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			//バイリニア補間で描画
		g2d.rotate(angle, x, y);	//座標を回転
		g2d.drawImage(img, x-ex_w/2, y-ex_h/2, ex_w, ex_h, gm);	//画像を拡大描画
		g2d.setTransform(new AffineTransform());

		if(inter) g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
			//ニアレスト補間に戻す		
	}
	
	public void drawImageRotateExtend(int x, int y, Image img, float angle, float rate, boolean inter, float alpha){
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));	//アルファチャンネルの値をセット

		int ex_w = (int)(img.getWidth(gm)*rate);
		int ex_h = (int)(img.getHeight(gm)*rate);
		
		if(inter) g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			//バイリニア補間で描画
		g2d.rotate(angle, x, y);	//座標を回転
		g2d.drawImage(img, x-ex_w/2, y-ex_h/2, ex_w, ex_h, gm);	//画像を拡大描画
		g2d.setTransform(new AffineTransform());

		if(inter) g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
			//ニアレスト補間に戻す		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));		//アルファチャンネルの値をリセット
	}	
	
	/**
	* 文字列を描画
	* @param x 描画x位置
	* @param y 描画y位置
	* @param String 文字列
	* @param Color 文字色
	* @param Font フォントデータ
	**/	
	public void drawString(int x, int y, String str, Color color, Font font, boolean antialias){
		g2d.setColor(color);	//色をセット
		g2d.setFont(font);		//フォントをセット
		FontMetrics fm = g2d.getFontMetrics();	//フォントメトリクスを受け取る
		y+=fm.getAscent();	//アセントサイズ分下へ
		
		if(antialias) g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.drawString(str, x, y);
		if(antialias) g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
	}
	
	/**
	* フォントのメトリクスを返すメソッド
	* @param font Fontオブジェクト
	**/	
	public FontMetrics getFontMetrics(Font font){
		return g2d.getFontMetrics(font);
	}
	
	public void SetPosition(int x, int y){
		g2d.translate(x , y);
	}
}