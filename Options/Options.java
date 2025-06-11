package Options;

public class Options {
    private int shipSize2;
    private int shipSize3;
    private int shipSize4;
    private int shipSize5;
    private int boardSize;
    private String playerName1;
    private String playerName2;



    public Options() {
        this.shipSize2 = 2;
        this.shipSize3 = 2;
        this.shipSize4 = 2;
        this.shipSize5 = 1;
        this.boardSize = 10;
    }
    public int getShipSize2() {
        return shipSize2;
    }
    public void setShipSize2(int shipSize2) {
        this.shipSize2 = shipSize2;
    }
    public int getShipSize3() {
        return this.shipSize3;
    }
    public void setShipSize3(int shipSize3) {
        this.shipSize3 = shipSize3;
    }
    public int getShipSize4() {
        return this.shipSize4;
    }
    public void setShipSize4(int shipSize4) {this.shipSize4 = shipSize4;
    }

    public int getShipSize5() {
        return this.shipSize5;
    }

    public void setShipSize5(int shipSize5) {
        this.shipSize5 = shipSize5;
    }

    public int getBoardSize() {
        return this.boardSize;
    }
    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public String getPlayerName1() {
        return this.playerName1;
    }
    public void setPlayerName1(String playerName1) {
        this.playerName1 = playerName1;
    }
    public String getPlayerName2() {
        return this.playerName2;
    }
    public void setPlayerName2(String playerName2) {
        this.playerName2 = playerName2;
    }







}
