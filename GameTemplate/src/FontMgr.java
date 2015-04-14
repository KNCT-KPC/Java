

import java.awt.Font;
import java.util.HashMap;

/*
	フォントデータを管理するマネージャクラス
 */
public class FontMgr {	
	//========================//
	//　　　FontData配列　　　//
	//========================//	
	private final FontData[] fontDataAry = new FontData[]{
			new FontData("ＭＳ ゴシック", "SYSTEM", Font.PLAIN, 20),
	};	
	
	//==========================//
	//　　　内部クラス定義　　　//
	//==========================//	
	private class FontData{
		//フォントデータ
		public final String name;		//フォントID	
		public final String fontName;	//フォントID	
		public final int type;		//フォントID	
		public final int size;		//フォントID	
		
		
		public FontData(String fontName, String name, int type, int size){
			this.fontName = fontName;
			this.name = name;
			this.type = type;
			this.size = size;
		}
	}
		
	//====================================//
	//　　　シングルトンパターン記述　　　//
	//====================================//	
	private static FontMgr instance = null;	//自身のインスタンス(null初期化
	
	//内部コンストラクタ
	private FontMgr(){
		for(FontData data : fontDataAry){
			fontMap.put(data.name, new Font(data.fontName, data.type, data.size));
		}
	}
	
	//インスタンス作成メソッド
	public static void loadFont(){
		if(instance == null){
			instance = new FontMgr();
		}
	}

	//インスタンス取得メソッド
	public static FontMgr getInstance(){
		return instance;
	}
	
	//==========================//
	//　　　フィールド定義　　　//
	//==========================//
	private HashMap<String, Font> fontMap = new HashMap<String, Font>();	

	//============================//
	//　　　外部メソッド定義　　　//
	//============================//	
	//nameにマッチするフォントオブジェクトを返すをメソッド
	public Font getFont(String name){
		return fontMap.get(name);
	}
	
	public static String integerToZenkakuString(int integer) {
		String str = Integer.toString(integer);
		StringBuffer sb = new StringBuffer(str);
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c >= '0' && c <= '9') {
				sb.setCharAt(i, (char) (c - '0' + '０'));
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
				sb.setCharAt(i, (char) (c - '0' + '０'));
			}
		}
		return sb.toString();
	}	
}