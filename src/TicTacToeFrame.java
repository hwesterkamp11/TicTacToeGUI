import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame {
    private static final int SIZE = 3;
    private TicTacToeButton[][] buttons = new TicTacToeButton[SIZE][SIZE];
    private String currentPlayer = "X";
    private int moves = 0;

    public TicTacToeFrame() {
        System.out.println("TicTacToeFrame called!");
        setTitle("Tic Tac Toe");
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE + 1, SIZE));

        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < 3; j++) {
                buttons[i][j] = new TicTacToeButton(i,j);
                buttons[i][j].addActionListener(new ButtonClickListener());
                add(buttons[i][j]);
            }
        }

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));
        add(quitButton);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            TicTacToeButton button = (TicTacToeButton) e.getSource();
            if(!button.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Invalid move! Try again.");
                return;
            }

            button.setText(currentPlayer);
            moves++;

            if(moves >= 5 && checkWin(currentPlayer)) {
                JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
                resetGame();
                return;
            }

            if(moves == SIZE*SIZE) {
                JOptionPane.showMessageDialog(null, "It's a tie!");
                resetGame();
                return;
            }
            currentPlayer = currentPlayer.equals("X") ? "O" : "X";
        }
    }

    private boolean checkWin(String player) {
        for(int i = 0; i < SIZE; i++) {
            if(buttons[i][0].getText().equals(player) && buttons[i][1].getText().equals(player) && buttons[i][2].getText().equals(player))
                return true;
            if(buttons[0][i].getText().equals(player) && buttons[1][i].getText().equals(player) && buttons[2][i].getText().equals(player))
                return true;
        }
        if(buttons[0][0].getText().equals(player) && buttons[1][1].getText().equals(player) && buttons[2][2].getText().equals(player))
            return true;
        if(buttons[0][2].getText().equals(player) && buttons[1][1].getText().equals(player) && buttons[2][0].getText().equals(player))
            return true;
        return false;
    }

    private void resetGame() {
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                buttons[i][j].setText("");
            }
        }
        currentPlayer = "X";
        moves = 0;
    }

    class TicTacToeButton extends JButton {
        private int row, col;

        public TicTacToeButton(int row, int col) {
            super("");
            this.row = row;
            this.col = col;
            setFont(new Font("Arial", Font.PLAIN, 40));
        }
    }
}