public class Main {
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		MainFrame frame = new MainFrame();
		ThreadController controller = new ThreadController();
		controller.run();
	}
}
