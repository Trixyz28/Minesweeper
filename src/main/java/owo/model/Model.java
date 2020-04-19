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

        endOperation();
    }


    public boolean canOp(int row, int col){
        return (!board.getTile(row,col).isOpened() && !board.getTile(row,col).isSigned());
    }

    public boolean canRemove(int row,int col) {
        return board.getTile(row,col).isSigned();
    }


    public void openTile(int row,int col) {
        board.getTile(row,col).setOpened(true);
        openTiles++;
    }

    public void placeFlag(int row,int col) {
        board.getTile(row,col).setSigned(true);

        endOperation();
    }

    public void removeFlag(int row,int col) {
        board.getTile(row,col).setSigned(false);

        endOperation();
    }


    public boolean checkLose(int row,int col) {
        return (board.getTile(row,col).isOpened() && board.getTile(row,col).isMined());
    }


    public boolean checkWin() {
        return (openTiles == board.getRow() * board.getColumn() - board.getMineNumber());
    }


    public void recursiveOpen(int row,int col) {

        if(board.getTile(row,col).getMineAround() == 0) {

            for(int i=-1;i<2 && i+row<board.getRow();i++) {
                for(int j=-1;j<2 && j+col<board.getColumn();j++) {

                    if(i+row>=0 && j+col>=0 && (i!=0 || j!=0)) {

                        if(!board.getTile(i+row,j+col).isOpened() && !board.getTile(i+row,j+col).isSigned()) {
                            openTile(i+row,j+col);
                            recursiveOpen(i+row,j+col);
                        }
                    }
                }
            }
        }
    }




    public void openAll() {
        for(int i=0; i<board.getRow(); i++) {
            for(int j=0; j<board.getColumn(); j++) {
                board.getTile(i,j).setOpened(true);
            }
        }
        endOperation();
    }


    public void endOperation() {
        setChanged();
        notifyObservers(board);
    }

}
