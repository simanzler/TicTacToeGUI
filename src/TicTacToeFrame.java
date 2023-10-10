import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TicTacToeFrame extends JFrame {
    String player = "X";
    int moveCnt = 0;
    final int MOVES_FOR_WIN = 5;
    final int MOVES_FOR_TIE = 7;
    private static final int ROW = 3;
    private static final int COL = 3;
    JPanel mainPnl;
    JPanel topPnl;
    JPanel bottomPnl;
    private static final TicTacToeButton[][] board = new TicTacToeButton[3][3];
    JTextField out;
    JButton quit;

    public TicTacToeFrame(){
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        CreateTopPanel();
        mainPnl.add(topPnl, BorderLayout.NORTH);

        createBottomPanel();
        mainPnl.add(bottomPnl, BorderLayout.SOUTH);

        add(mainPnl);

        setTitle("Tic Tac Toe Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        pack();
    }
    private void CreateTopPanel(){
        topPnl = new JPanel(new GridLayout(3,3));
        for( int row = 0; row < 3; row++)
            for(int col= 0; col < 3; col++)
            {
                board[row][col] = new TicTacToeButton(row, col);
                board[row][col].setText(" ");
                topPnl.add(board[row][col]);
                int finalCol = col;
                int finalRow = row;
                board[row][col].addActionListener((ActionEvent ae) -> {
                    if (isValidMove(board[finalRow][finalCol].getRow(), board[finalRow][finalCol].getCol())){

                        out.setText(" ");
                        board[finalRow][finalCol].setText(player);
                        moveCnt++;

                        if(moveCnt >= MOVES_FOR_WIN)
                        {
                            if(isWin(player))
                            {
                                out.setText("Player " + player + " wins!");
                                Object[] options = {"Yes",
                                        "No"};
                                int n = JOptionPane.showOptionDialog(mainPnl,
                                        "Would you like to keep playing?",
                                        "Keep Playing?",
                                        JOptionPane.YES_NO_OPTION,
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        options,
                                        options[1]);
                                if (n==1)
                                    System.exit(0);
                                player = "O";
                                out.setText(" ");
                                for( int r = 0; r < 3; r++)
                                    for(int c= 0; c < 3; c++)
                                    {
                                        board[c][r].setText(" ");
                                    }
                            }
                        }
                        if(moveCnt >= MOVES_FOR_TIE)
                        {
                            if(isTie())
                            {
                                out.setText("It's a Tie!");
                                Object[] options = {"Yes",
                                        "No"};
                                int n = JOptionPane.showOptionDialog(mainPnl,
                                        "Would you like to keep playing?",
                                        "Keep Playing?",
                                        JOptionPane.YES_NO_OPTION,
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        options,
                                        options[1]);
                                if (n==1)
                                    System.exit(0);
                                player = "O";
                                out.setText(" ");
                                for( int r = 0; r < 3; r++)
                                    for(int c= 0; c < 3; c++)
                                    {
                                        board[c][r].setText(" ");
                                    }
                            }
                        }
                        if(player.equals("X"))
                        {
                            player = "O";
                        }
                        else
                        {
                            player = "X";
                        }
                    } else {
                        out.setText("Choose valid tile");
                    }


                });
            }
    }
    private void createBottomPanel(){
        bottomPnl = new JPanel();
        out = new JTextField(" ");
        quit = new JButton("Quit");
        quit.addActionListener((ActionEvent ae) -> System.exit(0));

        bottomPnl.add(out);
        bottomPnl.add(quit);
    }
    private static boolean isValidMove(int row, int col)
    {

        return board[row][col].getText().equals(" ");

    }
    private static boolean isWin(String player)
    {
        return isColWin(player) || isRowWin(player) || isDiagnalWin(player);
    }
    private static boolean isColWin(String player)
    {
        for(int col=0; col < COL; col++)
        {
            if(board[0][col].getText().equals(player) &&
                    board[1][col].getText().equals(player) &&
                    board[2][col].getText().equals(player))
            {
                return true;
            }
        }
        return false;
    }
    private static boolean isRowWin(String player)
    {
        for(int row=0; row < ROW; row++)
        {
            if(board[row][0].getText().equals(player) &&
                    board[row][1].getText().equals(player) &&
                    board[row][2].getText().equals(player))
            {
                return true;
            }
        }
        return false;
    }
    private static boolean isDiagnalWin(String player)
    {

        if(board[0][0].getText().equals(player) &&
                board[1][1].getText().equals(player) &&
                board[2][2].getText().equals(player) )
        {
            return true;
        }
        if(board[0][2].getText().equals(player) &&
                board[1][1].getText().equals(player) &&
                board[2][0].getText().equals(player) )
        {
            return true;
        }
        return false;
    }

    private static boolean isTie()
    {
        boolean xFlag = false;
        boolean oFlag = false;
        for(int row=0; row < ROW; row++)
        {
            if(board[row][0].getText().equals("X") ||
                    board[row][1].getText().equals("X") ||
                    board[row][2].getText().equals("X"))
            {
                xFlag = true;
            }
            if(board[row][0].getText().equals("O") ||
                    board[row][1].getText().equals("O") ||
                    board[row][2].getText().equals("O"))
            {
                oFlag = true;
            }

            if(! (xFlag && oFlag) )
            {
                return false;
            }

            xFlag = oFlag = false;

        }
        for(int col=0; col < COL; col++)
        {
            if(board[0][col].getText().equals("X") ||
                    board[1][col].getText().equals("X") ||
                    board[2][col].getText().equals("X"))
            {
                xFlag = true;
            }
            if(board[0][col].getText().equals("O") ||
                    board[1][col].getText().equals("O") ||
                    board[2][col].getText().equals("O"))
            {
                oFlag = true;
            }

            if(! (xFlag && oFlag) )
            {
                return false;
            }
        }
        xFlag = oFlag = false;

        if(board[0][0].getText().equals("X") ||
                board[1][1].getText().equals("X") ||
                board[2][2].getText().equals("X") )
        {
            xFlag = true;
        }
        if(board[0][0].getText().equals("O") ||
                board[1][1].getText().equals("O") ||
                board[2][2].getText().equals("O") )
        {
            oFlag = true;
        }
        if(! (xFlag && oFlag) )
        {
            return false;
        }
        xFlag = oFlag = false;

        if(board[0][2].getText().equals("X") ||
                board[1][1].getText().equals("X") ||
                board[2][0].getText().equals("X") )
        {
            xFlag =  true;
        }
        if(board[0][2].getText().equals("O") ||
                board[1][1].getText().equals("O") ||
                board[2][0].getText().equals("O") )
        {
            oFlag =  true;
        }
        if(! (xFlag && oFlag) )
        {
            return false;
        }

        return true;
    }

}
