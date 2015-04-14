import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class Mouse implements MouseListener, MouseMotionListener{
	//====================================//
	//�@�@�@�V���O���g���p�^�[���L�q�@�@�@//
	//====================================//
	private static Mouse instance = null;
	private Mouse(int right, int top){
		//�e�ϐ��̏�����
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
	//�@�@�@�t�B�[���h��`�@�@�@//
	//==========================//
	private Point set_point;		//�}�E�X���X�i�[����^����ꂽ���W
	private Point update_point;		//�^�C�}�[�Ăяo�����ɍX�V�������W
	private boolean left_press;			//�^�C�}�[�Ăяo���܂łɍ��N���b�N���ꂽ���ǂ���
	private boolean right_press;		//�^�C�}�[�Ăяo���܂łɉE�N���b�N���ꂽ���ǂ���
	private boolean moved;				//�^�C�}�[�Ăяo���܂łɃ}�E�X�����������ǂ���
	private int left_count;				//����������Ă���t���[����
	private int right_count;			//�E��������Ă���t���[����
	private int right;
	private int top;
	
	//============================//
	//�@�@�@�O�����\�b�h��`�@�@�@//
	//============================//
	/**
	* �^�C�}�[�ɌĂяo�����X�V���\�b�h
	**/
	public void update(){
		if(left_press == true) left_count++;	//���̃t���[���ŉ�����Ă�����A�J�E���g��i�߂�
		else left_count = 0;				//������Ă��Ȃ��Ȃ�A�J�E���g�����Z�b�g
		
		if(right_press == true) right_count++;
		else right_count = 0;
		
		update_point.x = set_point.x - right;
		update_point.y = set_point.y - top;
	}
	
	/**
	 * �}�E�X�̍��{�^����������Ă��邩�ǂ����𔻒肷�郁�\�b�h
	 **/
	public boolean judgeLeftPress(){
		return left_count > 0;
	}
	/**
	 * �}�E�X�̉E�{�^����������Ă��邩�ǂ����𔻒肷�郁�\�b�h
	 */
	public boolean judgeRightPress(){
		return right_count > 0;
	}
	/**
	*�}�E�X�̍��{�^������������Ă���t���[������Ԃ�
	**/
	public int getLeftCount(){
		return left_count;
	}
	/**
	*�}�E�X�̉E�{�^������������Ă���t���[������Ԃ�
	**/
	public int getRightCount(){
		return right_count;
	}
	/**
	*�}�E�X�̌���x��Ԃ�
	**/
	public int getX(){
		return update_point.x;
	}
	/**
	*�}�E�X�̌���y��Ԃ�
	**/
	public int getY(){
		return update_point.y;
	}
	/**
	*�}�E�X�̌��݈ʒu��Ԃ�
	**/
	public Point getPoint(){
		return update_point;
	}	
	/**
	 * �}�E�X�����������ǂ����𔻒肷�郁�\�b�h
	 */
	public boolean judgeMoved(){
		return moved;
	}
	/**
	 * �}�E�X�̈ړ���Ԃ����Z�b�g���郁�\�b�h
	 */
	public void resetMoved(){
		moved = false;
	}	
	
	//=============================//
	//�@�@�@MouseListener��`�@�@�@//
	//=============================//	
	//�}�E�X�̃{�^���������ꂽ�Ƃ�
	public void mousePressed(MouseEvent e){
		if(e.getButton() == MouseEvent.BUTTON1) left_press = true;
		if(e.getButton() == MouseEvent.BUTTON3) right_press = true;
	}	
	//�}�E�X�̃{�^���������ꂽ�Ƃ�
	public void mouseReleased(MouseEvent e){
		left_press = false;
		right_press = false;
	}
	//�}�E�X���������Ƃ�
	public void mouseMoved(MouseEvent e){
		set_point = e.getPoint();	//���ɒl���Z�b�g�i�X�V�̓^�C�}�[�Ăяo�����j
		moved = true;
	}
	public void mouseClicked(MouseEvent e){}  // �}�E�X�{�^�����Z���Ԃŉ����ė����ꂽ�Ƃ�
	public void mouseEntered(MouseEvent e){}  // �}�E�X�J�[�\����GUI���i���ɓ������Ƃ�
	public void mouseExited(MouseEvent e){}   // �}�E�X�J�[�\����GUI���i�O�ɏo���Ƃ�
	public void mouseDragged(MouseEvent e){} // �}�E�X���h���b�O���ꂽ�Ƃ�
}