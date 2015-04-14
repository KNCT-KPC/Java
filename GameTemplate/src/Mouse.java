import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class Mouse implements MouseListener, MouseMotionListener{
	//====================================//
	//�@�@�@�V���O���g���p�^�[���L�q�@�@�@//
	//====================================//
	private static Mouse instance = null;
	private Mouse(){
		//�e�ϐ��̏�����
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
	//�@�@�@�t�B�[���h��`�@�@�@//
	//==========================//
	private Point setPoint;		//�}�E�X���X�i�[����^����ꂽ���W
	private Point updatePoint;		//�^�C�}�[�Ăяo�����ɍX�V�������W
	private boolean leftPress;			//�^�C�}�[�Ăяo���܂łɍ��N���b�N���ꂽ���ǂ���
	private boolean rightPress;		//�^�C�}�[�Ăяo���܂łɉE�N���b�N���ꂽ���ǂ���
	private boolean moved;				//�^�C�}�[�Ăяo���܂łɃ}�E�X�����������ǂ���
	private int leftCount;				//����������Ă���t���[����
	private int rightCount;			//�E��������Ă���t���[����
	
	//============================//
	//�@�@�@�O�����\�b�h��`�@�@�@//
	//============================//
	/**
	* �^�C�}�[�ɌĂяo�����X�V���\�b�h
	**/
	public void update(){
		if(leftPress == true) leftCount++;	//���̃t���[���ŉ�����Ă�����A�J�E���g��i�߂�
		else leftCount = 0;				//������Ă��Ȃ��Ȃ�A�J�E���g�����Z�b�g
		
		if(rightPress == true) rightCount++;
		else rightCount = 0;

		updatePoint = setPoint;
		if(updatePoint.x > GameMain.SCREEN_WIDTH) updatePoint.x = GameMain.SCREEN_WIDTH-1;
		if(updatePoint.y > GameMain.SCREEN_HEIGHT) updatePoint.y = GameMain.SCREEN_HEIGHT-1;
		if(updatePoint.x < 0) updatePoint.x = 0;
		if(updatePoint.y < 0) updatePoint.y = 0;
	}
	
	/**
	 * �}�E�X�̍��{�^����������Ă��邩�ǂ����𔻒肷�郁�\�b�h
	 **/
	public boolean judgeLeftPress(){
		return leftCount > 0;
	}
	/**
	 * �}�E�X�̉E�{�^����������Ă��邩�ǂ����𔻒肷�郁�\�b�h
	 */
	public boolean judgeRightPress(){
		return rightCount > 0;
	}
	/**
	*�}�E�X�̍��{�^������������Ă���t���[������Ԃ�
	**/
	public int getLeftCount(){
		return leftCount;
	}
	/**
	*�}�E�X�̉E�{�^������������Ă���t���[������Ԃ�
	**/
	public int getRightCount(){
		return rightCount;
	}
	/**
	*�}�E�X�̌���x��Ԃ�
	**/
	public int getX(){
		return updatePoint.x;
	}
	/**
	*�}�E�X�̌���y��Ԃ�
	**/
	public int getY(){
		return updatePoint.y;
	}
	/**
	*�}�E�X�̌��݈ʒu��Ԃ�
	**/
	public Point getPoint(){
		return updatePoint;
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
		if(e.getButton() == MouseEvent.BUTTON1) leftPress = true;
		if(e.getButton() == MouseEvent.BUTTON3) rightPress = true;
	}	
	//�}�E�X�̃{�^���������ꂽ�Ƃ�
	public void mouseReleased(MouseEvent e){
		leftPress = false;
		rightPress = false;
	}
	//�}�E�X���������Ƃ�
	public void mouseMoved(MouseEvent e){
		setPoint = e.getPoint();	//���ɒl���Z�b�g�i�X�V�̓^�C�}�[�Ăяo�����j
		moved = true;
	}
	public void mouseClicked(MouseEvent e){}  // �}�E�X�{�^�����Z���Ԃŉ����ė����ꂽ�Ƃ�
	public void mouseEntered(MouseEvent e){}  // �}�E�X�J�[�\����GUI���i���ɓ������Ƃ�
	public void mouseExited(MouseEvent e){}   // �}�E�X�J�[�\����GUI���i�O�ɏo���Ƃ�
	public void mouseDragged(MouseEvent e){} // �}�E�X���h���b�O���ꂽ�Ƃ�
}