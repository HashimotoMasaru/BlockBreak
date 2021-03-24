package app;

class BlockModel {

	/**
	 * 判定処理
	 */
	public BlockBean getNextInfomation(BlockBean bean) {
		int bx = bean.getballX();
		int by = bean.getballY();
		int bW = bean.getballRx();
		int bH = bean.getballRy();
		int rx = bean.getcursorX();
		int ry = bean.getcursorY();
		int rW = bean.getcursorW();
		int rH = bean.getcursorH();
		int viewW = bean.getvWidth();
		int viewH = bean.getvHeight();
		int dx = bean.getDX();
		int dy = bean.getDY();
		//	行、列 の配列のサイズ
		int Bsize = bean.getRow() * bean.getCol();

		//	ラケットに当たったときの処理
		if (by + bH >= ry && by + bH <= ry + rH && bx + bW >= rx && bx <= rx + rW) {
			//	ラケットに当たったら上へ返す
			dy = -2;
			if (bx < rx || bx + bW > rx + rW) {
				//	ラケットの端に当たった時
				if (dx == 0) {
					//	垂直に来たボール
					if (bx < rx) {
						//	左端に当たったら左斜め上に返す*/
						dx = -2; //	 左斜め上に返す　
					}
					if (bx + bW > rx + rW) {
						//	右端に当たったら右斜め上に返す*/
						dx = +2;
					}
				} else {
					//	斜めに来たボールは垂直に返す
					dx = 0;
				}
			}
		}

		//	左端、右端、上端に来たときの処理
		if (bx < 0) {
			//	左端に来たら反転
			dx = 2;
		}
		if (bx + bW > viewW) {
			//	右端に来たら反転
			dx = -2;
		}

		if (by < 0) {
			//	上端に来たら反転
			dy = 2;
		}

		//	ブロックに当たったときの処理
		for (int i = 0; i < Bsize; i++) {
			//ブロックが存在する時
			if (bean.getBlock()[i] == 1) {
				//	ブロックに当たった時
				if (by + bH >= bean.getBy()[i] && by <= bean.getBy()[i] + bean.getBH()
						&& bx + bW >= bean.getBx()[i] && bx <= bean.getBx()[i] + bean.getBW()) {
					//	反転
					dy = -dy;
					//	 ブロックを消す
					bean.setBlockState(i);
					//	ブロックの数をセット
					bean.setSubTotalBlock(-1);
				}
			}
		}

		//	ラケットの下へ行ったときの処理
		if (by + bH > viewH + 100) {
			//	下端に来たらモードを終了状態に
			bean.setMode(2);
		}

		//ボール位置に増量分追加した座標
		bx = bx + dx;
		by = by + dy;

		//	次のボールの位置のセット
		bean.setballX(bx);
		bean.setballY(by);

		//	次のボールの増分量をセット
		bean.setDX(dx);
		bean.setDY(dy);

		return bean;

	}

}