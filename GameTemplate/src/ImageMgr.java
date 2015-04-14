

import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.util.HashMap;

import javax.swing.JFrame;

/*
	�C���[�W�f�[�^���Ǘ�����}�l�[�W���N���X
*/
public class ImageMgr {	
	//=========================//
	//�@�@�@ImageData�z��@�@�@//
	//=========================//
	private final ImageData[] imgDataAry = new ImageData[]{
		//new ImageData(�p�X, �o�^��),
	};		
	
	//==========================//
	//�@�@�@�����N���X��`�@�@�@//
	//==========================//
	//�摜����\���N���X
	private class ImageData{
		public final String pass;	//�摜�ւ̃p�X
		public final String name;	//�摜�̖��O
		
		//�R���X�g���N�^
		public ImageData(String pass, String name){
			this.name = name;
			this.pass = pass;
		}
	}
	
	//====================================//
	//�@�@�@�V���O���g���p�^�[���L�q�@�@�@//
	//====================================//
	private static ImageMgr instance = null;	//���g�̃C���X�^���X(null������

	//�����R���X�g���N�^
	private ImageMgr(GameMain jap){
		this.jap = jap;
		
		MediaTracker tracker = new MediaTracker(jap);	//���f�B�A�g���b�J�[
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
	
	//�C���X�^���X�쐬���\�b�h
	public static void loadImage(GameMain jap){
		if(instance == null){
			instance = new ImageMgr(jap);
		}
	}
	
	//�C���X�^���X�擾���\�b�h
	public static ImageMgr getInstance(){
		return instance;	//�����o�ł��鎩�g�̃C���X�^���X��Ԃ�
	}
	
	
	//==========================//
	//�@�@�@�t�B�[���h��`�@�@�@//
	//==========================//
	private GameMain jap;
	private HashMap<String, Image> imgMap = new HashMap<String, Image>();
	
	//============================//
	//�@�@�@�O�����\�b�h��`�@�@�@//
	//============================//
	//name�Ƀ}�b�`����t�H���g�I�u�W�F�N�g��Ԃ������\�b�h
	public Image getImage(String name){
		return imgMap.get(name);
	}
	
	/**
	* �摜�𕪊����Ĕz��փZ�b�g���郁�\�b�h
	* @param img Image�I�u�W�F�N�g
	* @param x_num x������
	* @param y_num y������
	* @param width �����C���[�W�̉���
	* @param height �����C���[�W�̏c��
	**/
	public Image[] getDivImage(Image img, int x_num, int y_num, int width, int height){
		Image[] divImg = new Image[x_num*y_num];
			//�C���[�W�z��𐶐�

		for(int x = 0; x < x_num; x++){
			for(int y = 0; y < y_num; y++){
				CropImageFilter cfilter = new CropImageFilter(x*width, y*height, width, height);
					//Crop�t�B���^�[�𐶐�
				FilteredImageSource producer = new FilteredImageSource(img.getSource(),cfilter);
					//�t�B���^�C���[�W�̃v���f���[�T�[�𐶐�
				divImg[y + y_num*x] = jap.createImage(producer);
					//�v���f���[�T�[�ɏ]���ĐV�����C���[�W�𐶐��A�z��փZ�b�g
			}
		}
		
		return divImg;
	}
}
