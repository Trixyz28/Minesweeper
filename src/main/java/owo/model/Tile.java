package owo.model;

public class Tile {

    private boolean opened;

    public boolean isOpened() {
        return opened;
    }
    public void setOpened(boolean opened) {
        this.opened = opened;
    }


    private boolean mined;

    public boolean isMined() {
        return mined;
    }
    public void setMined(boolean mined) {
        this.mined = mined;
    }


    private boolean signed;
    public boolean isSigned() {
        return signed;
    }
    public void setSigned(boolean signed) {
        this.signed = signed;
    }


    private int mineAround;

    public int getMineAround() {
        return mineAround;
    }
    public void setMineAround(int mineAround) {
        this.mineAround = mineAround;
    }


}
