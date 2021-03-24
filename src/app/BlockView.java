package app;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class BlockView extends Frame implements ActionListener, MouseMotionListener {
	//	ブロック崩しの描画領域
	BlockViewPanel viewPanel;
	//	ブロック崩し制御用スレッド
	BlockControl bThread;
	//	開始ボタン
	Button btnStart;
	//	終了ボタン
	Button btnEnd;
	//	//	リプレイボタン
	Button btnReplay;
	//	画像情報クラス
	BlockBean bean = new BlockBean();

	/**
	 * 	コンストラクタ
	 */
	public BlockView() {
		//	描画エリア、パネル
		viewPanel = new BlockViewPanel();
		viewPanel.setBackground(Color.black);
		viewPanel.setVisible(true);
		this.add(viewPanel);

		//	ボタン
		btnStart = new Button("開始");
		btnEnd = new Button("終了");
		btnReplay = new Button("再開");
		this.add(btnStart);
		this.add(btnEnd);
		this.add(btnReplay);

		//	作成したBlockViewActionListenerクラスをアクションリスナーとして登録
		btnStart.addActionListener(this);
		btnEnd.addActionListener(this);
		btnReplay.addActionListener(this);

		//	BlockViewMouseMotionListenerを登録
		viewPanel.addMouseMotionListener(this);

		//	初期情報セット
		initInfomationset();
		//	コンストラクタ終了

	}

	/**
	 * 初期設定
	 */
	public void initInfomationset() {
		//	画像情報のセット
		bean.setBallPic(Toolkit.getDefaultToolkit().getImage("src/ball.jpg"));
		bean.setRacketPic(Toolkit.getDefaultToolkit().getImage("src/racket.jpg"));
		bean.setBlockPic(Toolkit.getDefaultToolkit().getImage("src/block.jpg"));

		//	ボールの初期位置、ｘ、ｙ座標
		bean.setballX(10);
		bean.setballY(100);
		//	ボールの横幅、縦幅をセット
		bean.setballRx(5);
		bean.setballRy(5);

		//	実行時間間隔セット
		bean.setTimeStep(5);
		//	次のボールの増分量をセット(x座標、ｙ座標）
		bean.setDX(2);
		bean.setDY(2);

		bean.setBW(50);
		bean.setBH(20);
		bean.setmargin(10);
		//	ブロックの行列セット
		bean.setRC(5, 3);
		bean.initblock();
		//	モード０が初期状態　１が実行中　２がボールが下に行った時　３がブロックがなくなったとき
		bean.setMode(0);

	}

	/**
	 * 表示情報設定
	 */
	public void Layout() {
		//	ボタンの高さ
		int btnHeight = 30;
		//	見える領域の（インセット）の情報を取得
		Insets ins = this.getInsets();
		int viewWidth = this.getWidth() - ins.left - ins.right;
		int viewHeight = this.getHeight() - ins.top - ins.bottom - btnHeight;

		//	インセットにあわせて描画エリアを配置
		viewPanel.setBounds(ins.left, ins.top, viewWidth, viewHeight);
		//	ボタンの幅、全体の１／3
		int btnWidth = viewWidth / 3;

		//	ボタン配置の位置を計算
		int btnX0 = ins.left;
		int btnY0 = this.getHeight() - ins.bottom - btnHeight;
		//	ボタンの配置
		btnStart.setBounds(btnX0, btnY0, btnWidth, btnHeight);
		btnEnd.setBounds(btnX0 + btnWidth, btnY0, btnWidth, btnHeight);
		btnReplay.setBounds(btnX0 + btnWidth * 2, btnY0, btnWidth, btnHeight);

		//	ラケットの幅と高さをセット
		bean.setcursorW(50);
		bean.setcursorH(10);
		//	ラケットの位置情報をセット
		bean.setcursorX((viewWidth - bean.getcursorW()) / 2);
		//	描画領域ｙ座標から―ラケットの高さ
		bean.setcursorY(viewHeight - bean.getcursorH());
		//	描画領域の幅と高さの情報をセット
		bean.setvWidth(viewPanel.getWidth());
		bean.setvHeight(viewHeight);

	}

	/**
	 * BlockBeanゲッター
	 * @param _infomation
	 */
	public BlockBean getBean() {
		return bean;
	}

	/**
	 * BlockBeanセッター
	 * @param _bean
	 */
	public void setBean(BlockBean _bean) {
		this.bean = _bean;
	}

	/**
	 * 	ブロックがなくなったらゲーム終了
	 */
	public void end() {
		bThread.interrupt();
		//	初期設定
	}

	/**
	 * 	描画処理
	 * @param vi
	 */
	public void repaintView(BlockBean _bean) {
		viewPanel.repaintView(_bean);
	}

	/**
	 * 	開始ボタンが押された時
	 */
	public void GameStart() {
		//	モード０ならスタート
		if (bean.getMode() == 0) {
			bean.setMode(1);
			bThread = new BlockControl(this);
			bThread.start();
		}
	}

	/**
	 * 	終了ボタンが押された時
	 */
	public void endGame() {
		System.exit(0);
	}

	/**
	 * 	再開ボタンが押された時
	 */
	public void ReplayGame() {
		//	クリアした時か、ゲームオーバーの時だけ
		if (bean.getMode() == 2 | bean.getMode() == 3) {
			initInfomationset();
			GameStart();
		}
	}

	/*　動的動作処理　*/
	//ActionListener処理
	/**
	 * 配置ボタンの実行処理
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnStart)) {
			//	開始ボタンが押された
			GameStart();
		} else if (e.getSource().equals(btnEnd)) {
			//	停止ボタンが押された
			endGame();
		} else if (e.getSource().equals(btnReplay)) {
			//	Replayボタンが押された
			ReplayGame();
		}
	}

	//MouseMotionListener
	/**
	 * マウスがドラッグされた
	 * MouseMotionListener使用時必須実装のため空実装
	 */
	public void mouseDragged(MouseEvent e) {

	}

	/**
	 *	マウスが移動した
	 */
	public void mouseMoved(MouseEvent e) {
		//マウスの位置保持
		int point;

		//マウス移動が描画領域内か判定
		if (e.getX() + bean.getcursorW() > bean.getvWidth()) {
			point = bean.getvWidth() - bean.getcursorW();
		} else {
			point = e.getX();
		}

		//ラケットの座標set
		bean.setcursorX(point);

		//描画処理
		repaintView(bean);
	}

}