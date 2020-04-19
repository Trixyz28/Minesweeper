package owo.model;

import java.util.Observable;

public class Model extends Observable {

    private Board board;
    private int openTiles = 0;


    public void initializeBoard(int row, int col) {
        board = new Board(row,col);
    }

    public void initializeMines(int mineNumber) {
        board.setMineNumber(mineNumber);
        board.setMines();

        for(int i=0;i<board.getRow();i++) {
            for(int j=0;j<board.getColumn();j++) {
                board.setMineAround(i,j);
            }
        }


        setChanged();
        notifyObservers(board);
    }


    public boolean canOp(int row, int col){
        if(board.getTile(row,col).isOpened() || board.getTile(row,col).isSigned()) {
            return false;
        }
        return true;
    }

    public boolean canRemove(int row,int col) {
        if(!board.getTile(row,col).isSigned()) {
            return false;
        }
        return true;
    }


    public void openTile(int row,int col) {
        board.getTile(row,col).setOpened(true);
        openTiles++;

        setChanged();
        notifyObservers(board);
    }

    public void placeFlag(int row,int col) {
        board.getTile(row,col).setSigned(true);

        setChanged();
        notifyObservers(board);
    }

    public void removeFlag(int row,int col) {
        board.getTile(row,col).setSigned(false);

        setChanged();
        notifyObservers(board);
    }


    public boolean checkLose(int row,int col) {
        if(board.getTile(row,col).isOpened() && board.getTile(row,col).isMined()) {
            return true;
        }
        return false;
    }


    public boolean checkWin() {
        if(openTiles == board.getRow()*board.getColumn()-board.getMineNumber()) {
            return true;
        }
        return false;
    }


    public void recursiveOpen(int row,int col) {


    }





    public void openAll() {
        for(int i=0; i<board.getRow(); i++) {
            for(int j=0; j<board.getColumn(); j++) {
                board.getTile(i,j).setOpened(true);
            }
        }

        setChanged();
        notifyObservers(board);
    }


}
