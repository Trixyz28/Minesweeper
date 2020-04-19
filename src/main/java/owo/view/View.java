package owo.view;

import owo.model.*;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;


public class View extends Observable implements Runnable, Observer {

    private Scanner scanner;
    private boolean endGame;

    private int row;
    private int column;
    private int mineNumber;


    public View() {
        scanner = new Scanner(System.in);
        endGame = false;
    }



    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getMine() {
        return mineNumber;
    }


    public void setGame() {
        do {
            System.out.println("Set the number of rows: (5-16)");
            row = scanner.nextInt();
        } while (row<5 || row>16);

        do {
            System.out.println("Set the number of columns: (5-30)");
            column = scanner.nextInt();
        } while (column<5 || column>30);


        System.out.println("Set the number of mines");
        mineNumber = scanner.nextInt();
        while(mineNumber<1 || mineNumber>row*column) {
            System.out.println("Error! Please control the number of mines");
            mineNumber = scanner.nextInt();
        }

        setChanged();
        notifyObservers("setup");
    }


    public void move() {

        System.out.println("Choose an operation: 1-open tile / 2-set flag / 3-remove flag");

        int operation = scanner.nextInt();
        while(operation <1 || operation >3) {
            System.out.println("Wrong operation");
            operation = scanner.nextInt();
        }

        switch(operation) {
            case 1:
                System.out.println("Which tile do you want to open?");
                break;

            case 2:
                System.out.println("Where do you want to place the flag?");
                break;

            case 3:
                System.out.println("Which flag do you want to remove?");
                break;
        }


        int position = scanner.nextInt();
        while(position <=0 || position >row*column) {
            System.out.println("Control the selected tile");
            position = scanner.nextInt();
        }

        setChanged();
        notifyObservers(new Move(operation, position-1));
    }


    @Override
    public void run() {
        setGame();
        while(!endGame) {
            move();
        }
    }


    @Override
    public void update(Observable o, Object arg) {
        if(!(o instanceof Model) || !(arg instanceof Board)) {
            throw new IllegalArgumentException();
        }
        print((Board)arg);
    }



    public void print(Board board) {

        for(int i=0;i<row;i++) {
            System.out.print(Colors.BLACK_BRIGHT + "|" + Colors.RESET);

            for(int j=0;j<column;j++) {

                Tile t = board.getTile(i,j);

                if(t.isOpened()) {

                    if (t.isMined()) {
                        System.out.print(Colors.RED_BOLD + "  *");
                    } else {
                        switch (t.getMineAround()) {

                            case 0:
                                System.out.print("   ");
                                break;

                            case 1:
                                System.out.print(Colors.BLUE);
                                System.out.format("%3d", t.getMineAround());
                                break;

                            case 2:
                                System.out.print(Colors.YELLOW);
                                System.out.format("%3d", t.getMineAround());
                                break;

                            case 3:
                                System.out.print(Colors.CYAN);
                                System.out.format("%3d", t.getMineAround());
                                break;

                            default:
                                System.out.print(Colors.PURPLE);
                                System.out.format("%3d", t.getMineAround());
                                break;
                        }
                    }
                }

                if(!t.isOpened() && t.isSigned()) {
                    System.out.print(Colors.RED_BOLD_BRIGHT + "  !");
                }

                if(!t.isOpened() && !t.isSigned()) {
                    System.out.format("%3d",i*column+j+1);
                }

                System.out.print(Colors.BLACK_BRIGHT + "|" + Colors.RESET);
            }

            System.out.println();
        }

        System.out.println();
    }


    public void declareInvalid() {
        System.out.println("Invalid move");
    }


    public void declareLose() {
        System.out.println("You lose!");
        endGame = true;
    }


    public void declareWin() {
        System.out.println("You win!");
        endGame = true;
    }
}
