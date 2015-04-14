

import java.awt.Font;
import java.util.HashMap;

/*
	�t�H���g�f�[�^���Ǘ�����}�l�[�W���N���X
 */
public class FontMgr {	
	//========================//
	//�@�@�@FontData�z��@�@�@//
	//========================//	
	private final FontData[] fontDataAry = new FontData[]{
			new FontData("�l�r �S�V�b�N", "SYSTEM", Font.PLAIN, 20),
	};	
	
	//==========================//
	//�@�@�@�����N���X��`�@�@�@//
	//==========================//	
	private class FontData{
		//�t�H���g�f�[�^
		public final String name;		//�t�H���gID	
		public final String fontName;	//�t�H���gID	
		public final int type;		//�t�H���gID	
		public final int size;		//�t�H���gID	
		
		
		public FontData(String fontName, String name, int type, int size){
			this.fontName = fontName;
			this.name = name;
			this.type = type;
			this.size = size;
		}
	}
		
	//====================================//
	//�@�@�@�V���O���g���p�^�[���L�q�@�@�@//
	//====================================//	
	private static FontMgr instance = null;	//���g�̃C���X�^���X(null������
	
	//�����R���X�g���N�^
	private FontMgr(){
		for(FontData data : fontDataAry){
			fontMap.put(data.name, new Font(data.fontName, data.type, data.size));
		}
	}
	
	//�C���X�^���X�쐬���\�b�h
	public static void loadFont(){
		if(instance == null){
			instance = new FontMgr();
		}
	}

	//�C���X�^���X�擾���\�b�h
	public static FontMgr getInstance(){
		return instance;
	}
	
	//==========================//
	//�@�@�@�t�B�[���h��`�@�@�@//
	//==========================//
	private HashMap<String, Font> fontMap = new HashMap<String, Font>();	

	//============================//
	//�@�@�@�O�����\�b�h��`�@�@�@//
	//============================//	
	//name�Ƀ}�b�`����t�H���g�I�u�W�F�N�g��Ԃ������\�b�h
	public Font getFont(String name){
		return fontMap.get(name);
	}
	
	public static String integerToZenkakuString(int integer) {
		String str = Integer.toString(integer);
		StringBuffer sb = new StringBuffer(str);
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c >= '0' && c <= '9') {
				sb.setCharAt(i, (char) (c - '0' + '�O'));
			}
		}
		return sb.toString();
	}
	
	public static String floatToZenkakuString(float integer) {
		String str = Float.toString(integer);
		StringBuffer sb = new StringBuffer(str);
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c >= '0' && c <= '9') {
				sb.setCharAt(i, (char) (c - '0' + '�O'));
			}
		}
		return sb.toString();
	}	
}