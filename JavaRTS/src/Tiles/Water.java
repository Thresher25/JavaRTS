package Tiles;

public class Water extends Tile {

    public Water(int x, int y, int h, int ID) {
        super(x, y, h);
        if (ID == 7) {
            getSpecificTile(3, 10, 7);
        } else if (ID == 4 || ID == 5 || ID == 6) {
            getSpecificTile(0, 12, ID);
        }

    }

}
