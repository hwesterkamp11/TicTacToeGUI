import javax.swing.SwingUtilities;

public class TicTacToeRunner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToeFrame().setVisible(true));
        System.out.println("Program started!");
    }
}
