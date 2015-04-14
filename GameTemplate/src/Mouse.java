import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class Mouse implements MouseListener, MouseMotionListener{
	//====================================//
	//　　　シングルトンパターン記述　　　//
	//====================================//
	private static Mouse instance = null;
	private Mouse(){
		//各変数の初期化
		setPoint = new Point(0,0);
		updatePoint = new Point(0,0);
		leftPress = false;
		rightPress = false;
		leftCount = 0;
		rightCount = 0;
		moved = false;
	}	
	public static void initialize(){
		instance = new Mouse();
	}
	public static Mouse getInstance(){
		return instance;
	}
	
	//==========================//
	//　　　フィールド定義　　　//
	//==========================//
	private Point setPoint;		//マウスリスナーから与えられた座標
	private Point updatePoint;		//タイマー呼び出し時に更新した座標
	private boolean leftPress;			//タイマー呼び出しまでに左クリックされたかどうか
	private boolean rightPress;		//タイマー呼び出しまでに右クリックされたかどうか
	private boolean moved;				//タイマー呼び出しまでにマウスが動いたかどうか
	private int leftCount;				//左が押されているフレーム数
	private int rightCount;			//右が押されているフレーム数
	
	//============================//
	//　　　外部メソッド定義　　　//
	//============================//
	/**
	* タイマーに呼び出される更新メソッド
	**/
	public void update(){
		if(leftPress == true) leftCount++;	//そのフレームで押されていたら、カウントを進める
		else leftCount = 0;				//押されていないなら、カウントをリセット
		
		if(rightPress == true) rightCount++;
		else rightCount = 0;

		updatePoint = setPoint;
		if(updatePoint.x > GameMain.SCREEN_WIDTH) updatePoint.x = GameMain.SCREEN_WIDTH-1;
		if(updatePoint.y > GameMain.SCREEN_HEIGHT) updatePoint.y = GameMain.SCREEN_HEIGHT-1;
		if(updatePoint.x < 0) updatePoint.x = 0;
		if(updatePoint.y < 0) updatePoint.y = 0;
	}
	
	/**
	 * マウスの左ボタンが押されているかどうかを判定するメソッド
	 **/
	public boolean judgeLeftPress(){
		return leftCount > 0;
	}
	/**
	 * マウスの右ボタンが押されているかどうかを判定するメソッド
	 */
	public boolean judgeRightPress(){
		return rightCount > 0;
	}
	/**
	*マウスの左ボタンが押入されているフレーム数を返す
	**/
	public int getLeftCount(){
		return leftCount;
	}
	/**
	*マウスの右ボタンが押入されているフレーム数を返す
	**/
	public int getRightCount(){
		return rightCount;
	}
	/**
	*マウスの現在xを返す
	**/
	public int getX(){
		return updatePoint.x;
	}
	/**
	*マウスの現在yを返す
	**/
	public int getY(){
		return updatePoint.y;
	}
	/**
	*マウスの現在位置を返す
	**/
	public Point getPoint(){
		return updatePoint;
	}	
	/**
	 * マウスが動いたかどうかを判定するメソッド
	 */
	public boolean judgeMoved(){
		return moved;
	}
	/**
	 * マウスの移動状態をリセットするメソッド
	 */
	public void resetMoved(){
		moved = false;
	}	
	
	//=============================//
	//　　　MouseListener定義　　　//
	//=============================//	
	//マウスのボタンが押されたとき
	public void mousePressed(MouseEvent e){
		if(e.getButton() == MouseEvent.BUTTON1) leftPress = true;
		if(e.getButton() == MouseEvent.BUTTON3) rightPress = true;
	}	
	//マウスのボタンが離されたとき
	public void mouseReleased(MouseEvent e){
		leftPress = false;
		rightPress = false;
	}
	//マウスが動いたとき
	public void mouseMoved(MouseEvent e){
		setPoint = e.getPoint();	//仮に値をセット（更新はタイマー呼び出し時）
		moved = true;
	}
	public void mouseClicked(MouseEvent e){}  // マウスボタンが短時間で押して離されたとき
	public void mouseEntered(MouseEvent e){}  // マウスカーソルがGUI部品内に入ったとき
	public void mouseExited(MouseEvent e){}   // マウスカーソルがGUI部品外に出たとき
	public void mouseDragged(MouseEvent e){} // マウスがドラッグされたとき
}