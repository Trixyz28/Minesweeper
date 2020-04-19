package owo.model;

public class Move {

    private int operation;
    private int position;

    public Move(int operation, int position) {
        this.operation = operation;
        this.position = position;
    }

    public int getOperation() {
        return operation;
    }

    public int getPosition() {
        return position;
    }

}
