import util.ThreadController;

public class Main {
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		ThreadController controller = new ThreadController();
		controller.run();
	}
}
