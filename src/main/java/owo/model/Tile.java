package owo.model;

public class Tile {

    private boolean opened;
    private boolean mined;
    private boolean signed;
    private int mineAround;


    public boolean isOpened() {
        return opened;
    }
    public void setOpened(boolean opened) {
        this.opened = opened;
    }


    public boolean isMined() {
        return mined;
    }
    public void setMined(boolean mined) {
        this.mined = mined;
    }


    public boolean isSigned() {
        return signed;
    }
    public void setSigned(boolean signed) {
        this.signed = signed;
    }


    public int getMineAround() {
        return mineAround;
    }
    public void setMineAround(int mineAround) {
        this.mineAround = mineAround;
    }

}
