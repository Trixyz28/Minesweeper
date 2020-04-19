package owo.model;

import java.util.Random;


public class Board {

    private Tile[][] board;

    private int row;
    public int getRow() {
        return row;
    }

    private int column;
    public int getColumn() {
        return column;
    }

    private int mineNumber;
    public int getMineNumber() {
        return mineNumber;
    }

    public void setMineNumber(int mineNumber) {
        this.mineNumber = mineNumber;
    }



    public Board(int row,int column) {
        board = new Tile[row][column];
        this.row = row;
        this.column = column;

        initialize();
    }


    private void initialize() {
        for(int i=0;i<row;i++) {
            for(int j=0;j<column;j++) {
                board[i][j] = new Tile();

                board[i][j].setOpened(false);
                board[i][j].setMined(false);
                board[i][j].setMineAround(0);
                board[i][j].setSigned(false);
            }
        }
    }


    public void setMines() {
        Random r = new Random();
        for(int i=0;i<mineNumber;i++) {
            int ran = r.nextInt(row*column);

            while(board[ran/column][ran%column].isMined()) {
                ran = r.nextInt(row*column);
            }
            board[ran/column][ran%column].setMined(true);
        }
    }


    public void setMineAround(int i,int j) {

        int counter = 0;

        for(int a=-1; a<2 && i+a<row;a++) {
            for(int b=-1; b<2 && j+b<column; b++) {

                if(i+a>=0 && j+b>=0 && (a!=0 || b!=0) ) {
                    if(board[i+a][j+b].isMined()) {
                        counter++;
                    }
                }
            }
        }

        board[i][j].setMineAround(counter);
    }


    public Tile getTile(int row,int column) {
        return board[row][column];
    }



}
