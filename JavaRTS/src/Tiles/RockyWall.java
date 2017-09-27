package Tiles;

public class RockyWall extends Tile {

    public RockyWall(int x, int y, int h, int ID) {
        super(x, y, h);
        getSpecificTile(0, 0, ID);
    }

}
