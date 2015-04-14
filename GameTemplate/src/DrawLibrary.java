

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;

/*
	�`�擮���񋟂��郉�C�u����
 */
public class DrawLibrary{
	//�V���O���g���ŋL�q
	private static DrawLibrary instance = null;	//�C���X�^���X
	private DrawLibrary(GameTemplate jf){
		this.jf = jf;
	}
	public static DrawLibrary getInstance(){
		return instance;
	}
	public static void initialize(GameTemplate jf){
		instance = new DrawLibrary(jf);
	}
	
	private GameTemplate jf;	//�r���[_
	private Graphics2D g2d;	//Graphics2D�I�u�W�F�N�g
	
	public boolean startDraw(BufferStrategy bstrategy){
		if(bstrategy.contentsLost() == true){
			return false;
		}
		this.g2d = (Graphics2D)bstrategy.getDrawGraphics();
		g2d.setColor(Color.DARK_GRAY);
		g2d.translate(jf.INSETS_RIGHT, jf.INSETS_TOP);		
		g2d.fillRect(0, 0, GameTemplate.SIZE_X, GameTemplate.SIZE_Y);
		return true;
	}
	public void endDraw(BufferStrategy bstrategy){
		bstrategy.show();
		g2d.dispose();
	}
	
	/**
	* �摜��`�悷�郁�\�b�h
	* @param img Image�I�u�W�F�N�g
	* @param x �`��x�ʒu
	* @param y �`��y�ʒu
	**/
	public void drawLine(int x1, int y1, int x2, int y2, Color c){
		g2d.setColor(c);
		g2d.drawLine(x1, y1, x2, y2);
	}
	/**
	* �摜��`�悷�郁�\�b�h
	* @param img Image�I�u�W�F�N�g
	* @param x �`��x�ʒu
	* @param y �`��y�ʒu
	**/
	public void drawImage(int x, int y, Image img){
		g2d.drawImage(img, x, y, jf);
	}
	/**
	* �A���t�@�u�����h�ŉ摜��`�� 
	* @param x �`��x�ʒu
	* @param y �`��y�ʒu
	* @param img Image�I�u�W�F�N�g
	* @param param �A���t�@�p�����[�^(0.0~1.0)
	**/
	public void drawImageAlphaBlend(int x, int y, Image img, float param){
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, param));	//�A���t�@�`�����l���̒l���Z�b�g
		g2d.drawImage(img, x, y, jf);		//�摜��`��
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));		//�A���t�@�`�����l���̒l�����Z�b�g
	}

	/**
	* �~���A���t�@�u�����h�ŕ`�� 
	* @param x �`�撆�Sx�ʒu
	* @param y �`�撆�Sy�ʒu
	* @param radius ���a
	* @param color Color�I�u�W�F�N�g
	* @param fill �h��Ԃ����ǂ���
	**/
	public void drawCircle(int x, int y, int radius, Color color, boolean fill){
		int rx = x - radius;
		int ry = y - radius;
		
		Color temp = g2d.getColor();	//�F���L��
		g2d.setColor(color);	//�����̐F���Z�b�g
		
		if(fill) g2d.fillOval(rx, ry, radius*2, radius*2);	//�ʂ�Ԃ��Ȃ�fillRect���\�b�h���A
		else g2d.drawOval(rx, ry, radius*2, radius*2);		//�ʂ�Ԃ��Ȃ��Ȃ�drawRect���\�b�h���Ăяo��

		g2d.setColor(temp);		//�F�����Z�b�g
	}
	
	/**
	* �~���A���t�@�u�����h�ŕ`�� 
	* @param x �`�撆�Sx�ʒu
	* @param y �`�撆�Sy�ʒu
	* @param radius ���a
	* @param color Color�I�u�W�F�N�g
	* @param fill �h��Ԃ����ǂ���
	* @param param �A���t�@�p�����[�^(0.0~1.0)
	**/
	public void drawCircleAlphaBlend(int x, int y, int radius, Color color, boolean fill, float param){
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, param));	//�A���t�@�`�����l���̒l���Z�b�g

		int rx = x - radius;
		int ry = y - radius;                                                                                                                                                                                                                    
		
		Color temp = g2d.getColor();	//�F���L��
		g2d.setColor(color);	//�����̐F���Z�b�g
		
		if(fill) g2d.fillOval(rx, ry, radius*2, radius*2);	//�ʂ�Ԃ��Ȃ�fillRect���\�b�h���A
		else g2d.drawOval(rx, ry, radius*2, radius*2);		//�ʂ�Ԃ��Ȃ��Ȃ�drawRect���\�b�h���Ăяo��

		g2d.setColor(temp);		//�F�����Z�b�g
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));		//�A���t�@�`�����l���̒l�����Z�b�g
	}
	
	/**
	* �l�p�`��`�� 
	* @param x �`��x�ʒu
	* @param y �`��y�ʒu
	* @param width �l�p�`�̉���
	* @param height �l�p�`�̏c��
	* @param color Color�I�u�W�F�N�g
	* @param fill �h��Ԃ����ǂ���
	**/
	public void drawRect(int x, int y, int width, int height, Color color, boolean fill){
		Color temp = g2d.getColor();	//�F���L��
		g2d.setColor(color);	//�����̐F���Z�b�g
		
		if(fill) g2d.fillRect(x, y, width, height);	//�ʂ�Ԃ��Ȃ�fillRect���\�b�h���A
		else g2d.drawRect(x, y, width, height);		//�ʂ�Ԃ��Ȃ��Ȃ�drawRect���\�b�h���Ăяo��

		g2d.setColor(temp);		//�F�����Z�b�g
	}
	
	/**
	* �A���t�@�u�����h�Ŏl�p�`��`�� 
	* @param x �`��x�ʒu
	* @param y �`��y�ʒu
	* @param width �l�p�`�̉���
	* @param height �l�p�`�̏c��
	* @param fill �h��Ԃ����ǂ���
	* @param param �A���t�@�p�����[�^(0.0~1.0)
	**/
	public void drawRectAlphaBlend(int x, int y, int width, int height, Color color, boolean fill, float param){
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, param));	//�A���t�@�`�����l���̒l���Z�b�g

		Color temp = g2d.getColor();	//�F���L��
		g2d.setColor(color);	//�����̐F���Z�b�g
		
		if(fill) g2d.fillRect(x, y, width, height);	//�ʂ�Ԃ��Ȃ�fillRect���\�b�h���A
		else g2d.drawRect(x, y, width, height);		//�ʂ�Ԃ��Ȃ��Ȃ�drawRect���\�b�h���Ăяo��

		g2d.setColor(temp);		//�F�����Z�b�g
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));		//�A���t�@�`�����l���̒l�����Z�b�g
	}
	
	/**
	* �摜����]���ĕ`�� �i�摜�̒��S���W���w��j
	* @param x �`�撆�Sx�ʒu�i��]�̎x�_�j
	* @param y �`�撆�Sy�ʒu�i��]�̎x�_�j
	* @param img Image�I�u�W�F�N�g
	* @param angle ��]���W�A���p
	**/
	public void drawImageRotate(int x, int y, Image img, float angle, boolean inter){
		if(inter) g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

		g2d.rotate(angle, x, y);	//���W����]
		g2d.drawImage(img, x-img.getWidth(jf)/2, y-img.getHeight(jf)/2, jf);	//�摜��`��
		g2d.setTransform(new AffineTransform());
		
		if(inter) g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
	}
	
	/**
	* �摜���g�k���ĕ`�� �i�摜�̒��S���W���w��j
	* @param x �`�撆�Sx�ʒu�i�g�k�̎x�_�j
	* @param y �`�撆�Sy�ʒu�i�g�k�̎x�_�j
	* @param img Image�I�u�W�F�N�g
	* @param rate �g�k�{��
	* @param inter ��Ԃ��s�����ǂ���
	**/
	public void drawImageExtend(int x, int y, Image img, float rate, boolean inter){
		int ex_w = (int)(img.getWidth(jf)*rate);
		int ex_h = (int)(img.getHeight(jf)*rate);
		
		if(inter) g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			//�o�C���j�A��Ԃŕ`��
		g2d.drawImage(img, x-ex_w/2, y-ex_h/2, ex_w, ex_h, jf);	//�摜���g��`��
		if(inter) g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
			//�j�A���X�g��Ԃɖ߂�
		
	}

	/**
	* �摜���g�k�E��]���ĕ`�� �i�摜�̒��S���W���w��j
	* @param x �`�撆�Sx�ʒu�i�g�k�E��]�̎x�_�j
	* @param y �`�撆�Sy�ʒu�i�g�k�E��]�̎x�_�j
	* @param img Image�I�u�W�F�N�g
	* @param angle ��]���W�A���p
	* @param rate �g�k�{��
	* @param inter ��Ԃ��s�����ǂ���
	**/
	public void drawImageRotateExtend(int x, int y, Image img, float angle, float rate, boolean inter){
		int ex_w = (int)(img.getWidth(jf)*rate);
		int ex_h = (int)(img.getHeight(jf)*rate);
		
		if(inter) g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			//�o�C���j�A��Ԃŕ`��
		g2d.rotate(angle, x, y);	//���W����]
		g2d.drawImage(img, x-ex_w/2, y-ex_h/2, ex_w, ex_h, jf);	//�摜���g��`��
		g2d.setTransform(new AffineTransform());

		if(inter) g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
			//�j�A���X�g��Ԃɖ߂�		
	}
	
	public void drawImageRotateExtend(int x, int y, Image img, float angle, float rate, boolean inter, float alpha){
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));	//�A���t�@�`�����l���̒l���Z�b�g

		int ex_w = (int)(img.getWidth(jf)*rate);
		int ex_h = (int)(img.getHeight(jf)*rate);
		
		if(inter) g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			//�o�C���j�A��Ԃŕ`��
		g2d.rotate(angle, x, y);	//���W����]
		g2d.drawImage(img, x-ex_w/2, y-ex_h/2, ex_w, ex_h, jf);	//�摜���g��`��
		g2d.setTransform(new AffineTransform());

		if(inter) g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
			//�j�A���X�g��Ԃɖ߂�		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));		//�A���t�@�`�����l���̒l�����Z�b�g
	}	
	
	/**
	* �������`��
	* @param x �`��x�ʒu
	* @param y �`��y�ʒu
	* @param String ������
	* @param Color �����F
	* @param Font �t�H���g�f�[�^
	**/	
	public void drawString(int x, int y, String str, Color color, Font font, boolean antialias){
		g2d.setColor(color);	//�F���Z�b�g
		g2d.setFont(font);		//�t�H���g���Z�b�g
		FontMetrics fm = g2d.getFontMetrics();	//�t�H���g���g���N�X���󂯎��
		y+=fm.getAscent();	//�A�Z���g�T�C�Y������
		
		if(antialias) g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.drawString(str, x, y);
		if(antialias) g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
	}
	
	/**
	* �t�H���g�̃��g���N�X��Ԃ����\�b�h
	* @param font Font�I�u�W�F�N�g
	**/	
	public FontMetrics getFontMetrics(Font font){
		return g2d.getFontMetrics(font);
	}
	
	public void SetPosition(int x, int y){
		g2d.translate(x , y);
	}
}