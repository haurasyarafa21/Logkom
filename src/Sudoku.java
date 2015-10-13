import java.io.IOException;

import javax.swing.*;
public class Sudoku extends JFrame
{
    public static void main(String[] args) throws IOException
    {
        SudokuSolverUI ff=new SudokuSolverUI();
        JFrame frame = new JFrame("Sudoku Solver By Ajith Kp & Jhelai Sahadevan");
        frame.add(ff);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 500);
        frame.setVisible(true);
    }
}