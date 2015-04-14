import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class Mouse implements MouseListener, MouseMotionListener{
	//====================================//
	//　　　シングルトンパターン記述　　　//
	//====================================//
	private static Mouse instance = null;
	private Mouse(int right, int top){
		//各変数の初期化
		set_point = new Point(0,0);
		update_point = new Point(0,0);
		left_press = false;
		right_press = false;
		left_count = 0;
		right_count = 0;
		moved = false;
		this.right = right;
		this.top = top;
	}	
	public static void initialize(int right, int top){
		instance = new Mouse(right, top);
	}
	public static Mouse getInstance(){
		return instance;
	}
	
	//==========================//
	//　　　フィールド定義　　　//
	//==========================//
	private Point set_point;		//マウスリスナーから与えられた座標
	private Point update_point;		//タイマー呼び出し時に更新した座標
	private boolean left_press;			//タイマー呼び出しまでに左クリックされたかどうか
	private boolean right_press;		//タイマー呼び出しまでに右クリックされたかどうか
	private boolean moved;				//タイマー呼び出しまでにマウスが動いたかどうか
	private int left_count;				//左が押されているフレーム数
	private int right_count;			//右が押されているフレーム数
	private int right;
	private int top;
	
	//============================//
	//　　　外部メソッド定義　　　//
	//============================//
	/**
	* タイマーに呼び出される更新メソッド
	**/
	public void update(){
		if(left_press == true) left_count++;	//そのフレームで押されていたら、カウントを進める
		else left_count = 0;				//押されていないなら、カウントをリセット
		
		if(right_press == true) right_count++;
		else right_count = 0;
		
		update_point.x = set_point.x - right;
		update_point.y = set_point.y - top;
	}
	
	/**
	 * マウスの左ボタンが押されているかどうかを判定するメソッド
	 **/
	public boolean judgeLeftPress(){
		return left_count > 0;
	}
	/**
	 * マウスの右ボタンが押されているかどうかを判定するメソッド
	 */
	public boolean judgeRightPress(){
		return right_count > 0;
	}
	/**
	*マウスの左ボタンが押入されているフレーム数を返す
	**/
	public int getLeftCount(){
		return left_count;
	}
	/**
	*マウスの右ボタンが押入されているフレーム数を返す
	**/
	public int getRightCount(){
		return right_count;
	}
	/**
	*マウスの現在xを返す
	**/
	public int getX(){
		return update_point.x;
	}
	/**
	*マウスの現在yを返す
	**/
	public int getY(){
		return update_point.y;
	}
	/**
	*マウスの現在位置を返す
	**/
	public Point getPoint(){
		return update_point;
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
		if(e.getButton() == MouseEvent.BUTTON1) left_press = true;
		if(e.getButton() == MouseEvent.BUTTON3) right_press = true;
	}	
	//マウスのボタンが離されたとき
	public void mouseReleased(MouseEvent e){
		left_press = false;
		right_press = false;
	}
	//マウスが動いたとき
	public void mouseMoved(MouseEvent e){
		set_point = e.getPoint();	//仮に値をセット（更新はタイマー呼び出し時）
		moved = true;
	}
	public void mouseClicked(MouseEvent e){}  // マウスボタンが短時間で押して離されたとき
	public void mouseEntered(MouseEvent e){}  // マウスカーソルがGUI部品内に入ったとき
	public void mouseExited(MouseEvent e){}   // マウスカーソルがGUI部品外に出たとき
	public void mouseDragged(MouseEvent e){} // マウスがドラッグされたとき
}