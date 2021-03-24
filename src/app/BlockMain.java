package app;

public class BlockMain {
	public static void main(String args[]) {
		//	ブロック崩しの画面クラス（ビュー）の作成
		BlockView bView = new BlockView();

		//	画面サイズの設定
		bView.setSize(400, 400);

		//	画面を表示
		bView.setVisible(true);

		//	初期表示設定
		bView.Layout();
	}
}