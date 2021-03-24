package app;

public class BlockControl extends Thread {

	BlockModel bModel;
	BlockView bView;
	BlockBean bean;

	/**
	 * 	コンストラクタ
	 * @param _bview
	 */
	public BlockControl(BlockView _bview) {
		this.bView = _bview; //	このクラスのbViewに新しい情報のbviewを代入
		this.bean = bView.getBean();
		this.bModel = new BlockModel();
	}

	/**
	 * 	スレッド処理開始
	 */
	public void run() {
		try {
			bView.repaintView(bean);
			Thread.sleep(100);

			while (true) {
				//	現在の画像の情報を取得
				bean = bView.getBean();
				//	次の情報を取得
				bean = bModel.getNextInfomation(bean);
				//	新しい情報を更新
				bView.setBean(bean);
				//	Viewクラスに描画命令
				bView.repaintView(bean);

				//	ブロックがなくなったら終わり
				if (bean.getTotalBlock() == 0) {
					bean.setMode(3);
					bView.end();
				}
				//	ボールがラケットより下に行ったら終わり
				if (bean.getMode() == 2) {
					bView.end();
				}
				Thread.sleep(bean.getTimeStep());

			}
		} catch (InterruptedException ex) {
		}
	}

}