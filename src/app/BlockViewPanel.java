package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

public class BlockViewPanel extends Panel {

	//	画像情報を保存するための変数
	BlockBean bean;
	//	ダブルバッファリング用のオフスクリーングラフィックス
	private Graphics offGr;
	//	ダブルバッファ用のオフスクリーンイメージ領域
	private Image offIm;

	/**
	 * 	位置とサイズを指定するメソッドをオーバーライド
	 *  オフスクリーン用の描画領域を作成
	 */
	@Override
	public void setBounds(int x, int y, int w, int h) {

		//親クラスの境界セッター
		super.setBounds(x, y, w, h);
		//現在の画面幅取得
		int width = this.getWidth();
		//現在の画面高取得
		int height = this.getHeight();

		//	描画領域作成
		offIm = this.createImage(width, height);
		offGr = offIm.getGraphics();

	}

	/**
	 * 	画像情報を渡して書き換えを指示
	 * @param vi
	 */
	public void repaintView(BlockBean _bean) {
		//BlockBean入れ替え
		bean = _bean;
		//書き換え
		this.repaint();

	}

	/**
	 * 	ダブルバッファを行うため,updateメソッドをオーバーライド
	 */
	public void update(Graphics g) {
		//画面情報 Beanにしたがって描画メソッド呼び出し
		paint(g);
	}

	/**
	 * 	画面情報 Beanにしたがって描画
	 */
	public void paint(Graphics g) {
		//BlockBeanが存在する時
		if (bean != null) {
			try {
				//	オフスクリーン領域の描画をいったん消去する
				offGr.clearRect(0, 0, bean.getvWidth(), bean.getvHeight());

				//	ボールの描画
				offGr.drawImage(bean.getBallPic(), bean.getballX(), bean.getballY(),
						bean.getballRx() * 2, bean.getballRy() * 2, this);

				//	カーソルの描画
				offGr.drawImage(bean.getRacketPic(), bean.getcursorX(), bean.getcursorY(),
						bean.getcursorW(), bean.getcursorH(), this);

				//	ブロックの描画
				for (int i = 0; i < bean.getBlock().length; i++) {
					//	ブロックがあるならば、ブロックを描く
					if (bean.getBlock()[i] == 1) {
						offGr.drawImage(bean.getBlockPic(), bean.getBx()[i], bean.getBy()[i], 50, 20,
								this);

					}

				}
				//オフスクリーンイメージ領域描画
				g.drawImage(offIm, 0, 0, this);

				//	クリアー画面を描画
				if (bean.getTotalBlock() == 0) {
					Font fnt = new Font("Serif", Font.PLAIN, 50);
					g.setFont(fnt);
					g.setColor(Color.blue.darker());
					g.drawString("クリアー", 100, 200);
				}
				//	ゲームオーバー画面を描画
				if (bean.getMode() == 2) {
					Font fnt = new Font("Serif", Font.ITALIC, 50);
					g.setFont(fnt);
					g.setColor(Color.blue.darker());
					g.drawString("ゲームオーバー", 20, 200);
				}

			} catch (NullPointerException e) {
				System.out.println("画面描画失敗:" + e);
			}

		}

	}

}