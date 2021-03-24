package app;

import java.awt.Image;

public class BlockBean {

	//	ボールの画像イメージ
	private Image ballImage;
	//	ラケットの画像イメージ
	private Image cursorImage;
	//	ブロックの画像イメージ
	private Image blockImage;

	//	実行時間間隔、書き換え時間（速度）
	private int timeStep;

	//	ボールの横幅
	private int ballRx;
	//	ボールの縦幅
	private int ballRy;
	//	ボール中心のｘ座標
	private int ballX;
	//	ボール中心のｙ座標
	private int ballY;
	//	次のボールの増分量(x座標、ｙ座標）
	private int dx;
	private int dy;

	//	ブロック崩しの描画範囲の横幅
	private int vWidth;
	//	ブロック崩しの描画範囲の縦幅
	private int vHeight;

	//	ラケットの左上点のｘ座標
	private int cursorX;
	//	ラケットの左上点のｙ座標
	private int cursorY;
	//	ラケットの横幅
	private int cursorW;
	//	ラケットの縦幅
	private int cursorH;

	//	 ブロックがあるかどうかの配列
	private int Block[];
	//	 ブロックのｘ座標
	private int Bx[];
	//	 ブロックのｙ座標
	private int By[];

	//	 ブロックの幅,高さ,マージン
	private int BW;
	private int BH;
	private int margin;

	//	ブロックの行、列
	private int row;
	private int col;
	//	ブロックの個数
	private int totalBlock;
	//	状態
	private int mode;

	/**
	 * 	ボール画像の取得メソッド
	 * @return
	 */
	public Image getBallPic() {
		return ballImage;
	}

	/**
	 * 	ラケット画像取得メソッド
	 * @return
	 */
	public Image getRacketPic() {
		return cursorImage;
	}

	/**
	 * 	ブロック画像の取得メソッド
	 * @return
	 */
	public Image getBlockPic() {
		return blockImage;
	}

	/**
	 * 	実行時間間隔、書き換え時間の取得メソッド（速度）
	 * @return
	 */
	public int getTimeStep() {
		return timeStep;
	}

	/**
	 * 	ボールの横幅の取得メソッド
	 * @return
	 */
	public int getballRx() {
		return ballRx;
	}

	/**
	 * 	ボールの縦幅の取得メソッド
	 * @return
	 */
	public int getballRy() {
		return ballRy;
	}

	/**
	 * 	ボールのｘ座標の取得メソッド
	 * @return
	 */
	public int getballX() {
		return ballX;
	}

	/**
	 * 	ボールのｙ座標の取得メソッド
	 * @return
	 */
	public int getballY() {
		return ballY;
	}

	/**
	 * 	ラケットの左上点のx座標の取得メソッド
	 * @return
	 */
	public int getcursorX() {
		return cursorX;
	}

	/**
	 * 	ラケットの左上点Yの取得メソッド
	 * @return
	 */
	public int getcursorY() {
		return cursorY;
	}

	/**
	 * 	ラケットの横幅の取得メソッド
	 * @return
	 */
	public int getcursorW() {
		return cursorW;
	}

	/**
	 * 	ラケットの縦幅の取得メソッド
	 * @return
	 */
	public int getcursorH() {
		return cursorH;
	}

	/**
	 * 	ブロック崩しの描画範囲の横幅,取得メソッド
	 * @return
	 */
	public int getvWidth() {
		return vWidth;
	}

	/**
	 * 	ブロック崩しの描画範囲の縦幅,取得メソッド
	 * @return
	 */
	public int getvHeight() {
		return vHeight;
	}

	/**
	 * 	次のボールの増分量,ｘ増分の取得メソッド
	 * @return
	 */
	public int getDX() {
		return dx;
	}

	/**
	 * 	次のボールの増分量(x座標、ｙ座標）ｙ増分の取得メソッド
	 * @return
	 */
	public int getDY() {
		return dy;
	}

	/**
	 * 	 ブロックがあるかどうかの配列
	 * @return
	 */
	public int[] getBlock() {
		return Block;
	}

	/**
	 * 	 ブロックのｘ座標   配列を返す
	 * @return
	 */
	public int[] getBx() {
		return Bx;
	}

	/**
	 * 	 ブロックのｙ座標   配列を返す
	 * @return
	 */
	public int[] getBy() {
		return By;
	}

	/**
	 * 	 ブロックの幅をget
	 * @return
	 */
	public int getBW() {
		return BW;
	}

	/**
	 * 	 ブロックの高さをget
	 * @return
	 */
	public int getBH() {
		return BH;
	}

	/**
	 * 	 marginをget
	 * @return
	 */
	public int getmargin() {
		return margin;
	}

	/**
	 * 	ブロックの行、get
	 * @return
	 */
	public int getRow() {
		return row;
	}

	/**
	 * 	ブロックの列 get
	 * @return
	 */
	public int getCol() {
		return col;
	}

	/**
	 * 	ブロックの個数get
	 * @return
	 */
	public int getTotalBlock() {
		return totalBlock;
	}

	/**
	 * 	状態のget
	 * @return
	 */
	public int getMode() {
		return mode;
	}

	/**
	 * 	ボール画像のsetメソッド
	 * @param _ballImage
	 */
	public void setBallPic(Image _ballImage) {
		this.ballImage = _ballImage;
	}

	/**
	 * 	ラケット画像setメソッド
	 * @param _cursorImage
	 */
	public void setRacketPic(Image _cursorImage) {
		this.cursorImage = _cursorImage;
	}

	/**
	 * 	ブロック画像の取得メソッド
	 * @param _blockImage
	 */
	public void setBlockPic(Image _blockImage) {
		this.blockImage = _blockImage;
	}

	/**
	 * 	実行時間間隔、書き換え時間のset（速度）
	 * @param _timeStep
	 */
	public void setTimeStep(int _timeStep) {
		this.timeStep = _timeStep;
	}

	/**
	 * 	ボールの横幅のsetメソッド
	 * @param _Rx
	 */
	public void setballRx(int _Rx) {
		this.ballRx = _Rx;
	}

	/**
	 * 	ボールの縦幅のsetメソッド
	 * @param _Ry
	 */
	public void setballRy(int _Ry) {
		this.ballRy = _Ry;
	}

	/**
	 * 	ボール中心のｘ座標のsetメソッド
	 * @param _x
	 */
	public void setballX(int _x) {
		this.ballX = _x;
	}

	/**
	 * 	ボール中心のｙ座標のsetメソッド
	 * @param _y
	 */
	public void setballY(int _y) {
		this.ballY = _y;
	}

	/**
	 * 	ラケットの左上点のｙ座標のsetメソッド
	 */
	public void setcursorX(int _x) {
		this.cursorX = _x;
	}

	/**
	 * 	ラケットの左上点Yのsetメソッド
	 * @param _y
	 */
	public void setcursorY(int _y) {
		this.cursorY = _y;
	}

	/**
	 * 	ラケットの横幅のsetメソッド
	 * @param _w
	 */
	public void setcursorW(int _w) {
		this.cursorW = _w;
	}

	/**	ラケットの縦幅のsetメソッド
	 *
	 * @param _h
	 */
	public void setcursorH(int _h) {
		this.cursorH = _h;
	}

	/**
	 * 	ブロック崩しの描画範囲の横幅setメソッド
	 * @param _w
	 */
	public void setvWidth(int _w) {
		this.vWidth = _w;
	}

	/**
	 * 	ブロック崩しの描画範囲の縦幅setメソッド
	 * @param _h
	 */
	public void setvHeight(int _h) {
		this.vHeight = _h;
	}

	/**
	 * 	 ボールの進む量,ｘ増分のsetメソッド
	 * @param _dx
	 */
	public void setDX(int _dx) {
		this.dx = _dx;
	}

	/**
	 * 	 ボールの進む量,ｙ増分のsetメソッド
	 * @param _dy
	 */
	public void setDY(int _dy) {
		this.dy = _dy;
	}

	/**
	 * 	 ブロックがあるかどうかの配列
	 * @param _i
	 */
	public void setBlockState(int _i) {
		this.Block[_i] = 0;
	}

	/**
	 * 	 ブロックの幅をset
	 * @param _BW
	 */
	public void setBW(int _BW) {
		this.BW = _BW;
	}

	/**
	 * 	 ブロックの幅をset
	 * @param _BH
	 */
	public void setBH(int _BH) {
		this.BH = _BH;
	}

	/**
	 * 	ブロックの行、列
	 * @param _row
	 * @param _col
	 */
	public void setRC(int _row, int _col) {
		this.row = _row;
		this.col = _col;
	}

	/**
	 * 	marginのset
	 * @param _margin
	 */
	public void setmargin(int _margin) {
		this.margin = _margin;
	}

	/**
	 * 	ブロックの個数
	 * @param _subNum
	 */
	public void setSubTotalBlock(int _subNum) {
		this.totalBlock = totalBlock + _subNum;
	}

	/**
	 * 	状態のset
	 * @param _mode
	 */
	public void setMode(int _mode) {
		this.mode = _mode;
	}

	/**
	 * 	ブロックの初期状態セットメソッド
	 */
	public void initblock() {

		//	 ブロックがあるかどうかの配列
		Block = new int[row * col];
		//	 ブロックのｘ座標
		Bx = new int[row * col];
		//	 ブロックのｙ座標
		By = new int[row * col];
		//	ブロックの個数set
		totalBlock = row * col;

		//	 カウンター
		int i, j;
		//	ブロック番号
		int k;
		//	 段毎のｙ座標
		int yy;

		//	ブロックの位置（ｘ、ｙ座標）の設定
		k = 0;
		for (i = 0; i < col; i++) {
			yy = i * (BH + 3) + margin;
			for (j = 0; j < row; j++) {
				this.Bx[k] = j * (BW + 30) + margin;
				this.By[k] = yy;
				this.Block[k] = 1; //	 ブロックがある
				k = k + 1;
			}
		}
	}
}