/*
 *
 * Coded By AJITH KP and Jhelai Sahadevan
 *
 * Greets to Amsteck Arts & Science College BCA & BSc Physics
 *
 * (c) AJITH KP [ https://www.facebook.com/ajithkp560 ] (c)
 *
 * http://www.TerminalCoders.BlogSpot.in
 * 
 */
import java.awt.*;

import javax.swing.*;
import javax.swing.text.*;

import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;
import java.util.regex.Pattern;

public class SudokuSolverUI extends JPanel
{
    public JTextField view[][];
    public int[][] a = new int[16][16];
    public int pG=0;
    public JPanel Xpanel,gridPanel, upView,superPanel, nPanel;
    private JButton button;
    public String str;
    private JLabel left;
    public JTextField[][] field = new JTextField[16][16];
    public SudokuSolverUI() throws IOException
    {
    	final BufferedWriter tulis = new BufferedWriter (new FileWriter("D:/input.in"));
    	superPanel=new JPanel(new FlowLayout());
        gridPanel=new JPanel(new GridLayout(16,16));
        gridPanel.setSize(400,400);
        upView=new JPanel(new GridLayout(16,16));
        upView.setSize(500,500);
        view=new JTextField[16][16];
        left=new JLabel();
        left.setText("<html>Sudoku Solver By AJITH KP & JHELAI <a href='http://TerminalCoders.BlogSpot.iN'>http://TerminalCoders.BlogSpot.iN</a>");
        add(left, BorderLayout.SOUTH);
        gridPanel.setBorder(BorderFactory.createMatteBorder(5,5,5,5, Color.BLACK));
        upView.setBorder(BorderFactory.createMatteBorder(5,5,5,5, Color.BLACK));
        gridPanel.setBackground(Color.BLACK);
        upView.setBackground(Color.BLACK);
        Xpanel=new JPanel(new GridLayout(1,1));
        Xpanel.setBorder(BorderFactory.createMatteBorder(5,5,5,5, Color.GREEN));
        button=new JButton("Random");
        Xpanel.add(button);
        for(int i=0;i<16;i++)
        {
            for(int j=0;j<16;j++)
            {
                a[i][j]=0;
                view[i][j]=new JTextField();
                field[i][j]=new JTextField();
                field[i][j].setColumns(2);
                view[i][j].setColumns(2);
                view[i][j].setEditable(false);
                if((i<4 && j<4) || (i>3 && i<8 && j>3 && j<8) || (i<4 && j>7 && j<12) || (j<4 && i>7 && i<12) || (i>7 && i<12 && j>7 && j<12) || (i>3 && i<8 && j>11 && j<16)|| (j>3 && j<8 && i>11 && i<16) || (i>11 && i<16 && j>11 && j<16))
                {
                    field[i][j].setBackground(Color.YELLOW);
                    view[i][j].setBackground(Color.YELLOW);
                }
                else
                {
                    field[i][j].setBackground(Color.PINK);
                    view[i][j].setBackground(Color.PINK);
                }
                //field[i][j].setDocument(new NumericalDocument());
                gridPanel.add(field[i][j]);
                upView.add(view[i][j]);
            }
        }
        superPanel.add(gridPanel,BorderLayout.WEST);
        superPanel.add(Xpanel);
        superPanel.add(upView,BorderLayout.EAST);
        nPanel=new JPanel();
        nPanel.setBorder(BorderFactory.createMatteBorder(5,0,5,0, Color.darkGray));
        add(superPanel);
        add(nPanel, BorderLayout.SOUTH);
           
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
            	Random random = new Random();
            	int i=random.nextInt(16)+1;
            	int j=random.nextInt(16)+1;
            	int k =random.nextInt(16)+1;
            	field[i][j].setText(""+k);
            	try {
					tulis.write("lala");
				} catch (IOException e) {
					
				}
            	
            	/*for (int b=0; b<16; b++){
            		for (int c=0; c<16; c++){
            			if (field[b][c].getText()!=null){
            				try {
                				tulis.write("lala" + field[b][c].getText());
                			}catch (IOException e) {
                			}
            			}
        			}
            	}*/
            }
           
        });
    }

    /*public static class NumericalDocument extends PlainDocument
    {
        String numbers = "1234567890";
       
        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException
        {
            if (getLength()<=1 && str.length()==1 && numbers.contains(str))
            {
                super.insertString(offs, str,(AttributeSet) a);
            }
            else
            {
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }*/
    /*protected boolean checkRow(int i,int num)
    {
        for(int j=0;j<16;j++)
        {
            if(a[i][j]==num)
            {
                return false;
            }
        }
        return true;
    }
    protected boolean checkCol(int j,int num)
    {
        for(int i=0;i<16;i++)
        {
            if(a[i][j]==num)
            {
                return false;
            }
        }
        return true;
    }
    protected boolean checkBox(int i, int j, int num)
    {
        i=(i/3)*3;
        j=(j/3)*3;
        for(int r=0;r<3;r++)
        {
            for(int c=0;c<3;c++)
            {
                if(a[i+r][j+c]==num)
                {
                    return false;
                }
            }
        }
        return true;
    }
    public void solve(int i, int j) throws Exception
    {
        if(i>8)
        {
            button.setEnabled(true);
            progressX.setValue(100);
            JOptionPane.showMessageDialog(null, "Execution Completed!!!\nSudoku Solved!!!");
        }
        if(a[i][j]!=0)
        {
            next(i,j);
        }
        else
        {
            for(int num=1;num<10;num++)
            {
                if(checkRow(i,num) && checkCol(j,num) && checkBox(i,j,num))
                {
                    a[i][j]=num;
                    XView();
                    next(i,j);
                }
            }
            a[i][j]=0;
            XView();
        }
    }
    public void next(int i, int j) throws Exception
    {
        if(j<8)
        {
            solve(i, j+1);
            progressX.setValue(pG+11);
        }
        else
        {
            solve(i+1,0);
        }
    }*/
    protected void XView()
    {
        for(int i=0;i<16;i++)
        {
            for(int j=0;j<16;j++)
            {
                if(a[i][j]!=0)
                {
                    view[i][j].setText(String.valueOf(a[i][j]));
                }
                else
                {
                    view[i][j].setText("");
                }
            }
        }
    }
}
