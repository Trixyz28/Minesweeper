package owo.controller;

import owo.model.Model;
import owo.model.Move;
import owo.view.View;

import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {

    private Model model;
    private View view;

    public Controller (Model model, View view) {
        this.model = model;
        this.view = view;
    }


    @Override
    public void update(Observable o, Object arg) {
        if(o!=view) {
            throw new IllegalArgumentException();
        }

        if(arg.equals("setup")) {
            int row = view.getRow();
            int column = view.getColumn();
            int mineNumber = view.getMine();

            model.initializeBoard(row,column);
            model.initializeMines(mineNumber);
        }


        if(arg instanceof Move) {
            Move move = (Move)arg;

            int operation = move.getOperation();
            int row = move.getPosition() / view.getColumn();
            int column = move.getPosition() % view.getColumn();

            switch(operation) {

                case 1:
                    if(model.canOp(row,column)) {
                        model.openTile(row,column);
                    } else {
                        view.declareInvalid();
                        return;
                    }


                    if(model.checkLose(row,column)) {
                        model.openAll();
                        view.declareLose();
                    }

                    if(model.checkWin()) {
                        view.declareWin();
                    }

                    break;

                case 2:
                    if(model.canOp(row,column)) {
                        model.placeFlag(row,column);
                    } else {
                        view.declareInvalid();
                        return;
                    }
                    break;

                case 3:
                    if(model.canRemove(row,column)) {
                        model.removeFlag(row,column);
                    } else {
                        view.declareInvalid();
                        return;
                    }
                    break;

                default:
                    throw new IllegalArgumentException();
            }
        }
    }


}