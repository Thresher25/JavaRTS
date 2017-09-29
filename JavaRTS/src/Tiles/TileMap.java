package Tiles;

import java.awt.*;
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
        String temp[] = new String[height];
        for (int i = 0; i < height; i++) {
            temp[i] = reader.readLine();
        }
        reader.close();
        map = new Tile[width * height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map[i * height + j] = convertToTile(Tile.dimension * j, Tile.dimension * i, getNums(temp[i], j));
            }
        }

    }

    public String getNums(String line, int pos) {
        return (line.substring(line.indexOf(":", pos * 3 + pos) + 1, (line.indexOf(":", pos * 3 + pos)) + 4));
    }

    public Tile convertToTile(int x, int y, String s) {
        int t = Integer.parseInt(s.substring(0, 1));
        int d = Integer.parseInt(s.substring(1, 2));
        int h = Integer.parseInt(s.substring(2));
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

    public void draw(Graphics g) {
        for (int i = 0; i < map.length; i++) {
            map[i].draw(g);
        }
    }

}
