package Tiles;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TileMap {

    public int width, height;
    Tile map[];
    private BufferedReader reader;

    public TileMap(String filePath) throws IOException {

        try {
            reader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        width = Integer.parseInt(reader.readLine());
        height = Integer.parseInt(reader.readLine());
        map = new Tile[width * height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map[i * height + j] = convertToTile() //TODO make the bufReader parse the line and segment it into nums for each tile
            }
        }

    }

    public Tile convertToTile(int t, int d, int x, int y, int h) {
        switch (t) {
            case 1:
                return new Grass(x, y, h, d);
            case 2:
                return new Water(x, y, h);
            case 3:
                return new RockyWall(x, y, h, d);
            case 4:

            case 5:

            case 6:

            case 7:

            case 8:

            case 9:

            default:
                return null;
        }
    }

}
