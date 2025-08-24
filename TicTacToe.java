import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener {
    JButton[] buttons = new JButton[9];
    boolean playerX = true; // true = X's turn, false = O's turn
    JLabel statusLabel;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Status label
        statusLabel = new JLabel("Player X's Turn", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(statusLabel, BorderLayout.NORTH);

        // Grid for buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        Font font = new Font("Arial", Font.BOLD, 60);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(font);
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (!clicked.getText().equals("")) {
            return; // already taken
        }

        if (playerX) {
            clicked.setText("X");
            statusLabel.setText("Player O's Turn");
        } else {
            clicked.setText("O");
            statusLabel.setText("Player X's Turn");
        }

        playerX = !playerX; // switch turn

        checkWinner();
    }

    private void checkWinner() {
        String[][] winCombos = {
                {buttons[0].getText(), buttons[1].getText(), buttons[2].getText()},
                {buttons[3].getText(), buttons[4].getText(), buttons[5].getText()},
                {buttons[6].getText(), buttons[7].getText(), buttons[8].getText()},
                {buttons[0].getText(), buttons[3].getText(), buttons[6].getText()},
                {buttons[1].getText(), buttons[4].getText(), buttons[7].getText()},
                {buttons[2].getText(), buttons[5].getText(), buttons[8].getText()},
                {buttons[0].getText(), buttons[4].getText(), buttons[8].getText()},
                {buttons[2].getText(), buttons[4].getText(), buttons[6].getText()},
        };

        for (String[] line : winCombos) {
            if (line[0].equals("X") && line[1].equals("X") && line[2].equals("X")) {
                showWinner("Player X Wins!");
                return;
            } else if (line[0].equals("O") && line[1].equals("O") && line[2].equals("O")) {
                showWinner("Player O Wins!");
                return;
            }
        }

        boolean draw = true;
        for (JButton b : buttons) {
            if (b.getText().equals("")) {
                draw = false;
                break;
            }
        }

        if (draw) {
            showWinner("It's a Draw!");
        }
    }

    private void showWinner(String message) {
        JOptionPane.showMessageDialog(this, message);
        resetGame();
    }

    private void resetGame() {
        for (JButton b : buttons) {
            b.setText("");
        }
        playerX = true;
        statusLabel.setText("Player X's Turn");
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
