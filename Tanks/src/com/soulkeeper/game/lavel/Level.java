package com.soulkeeper.game.lavel;

import com.soulkeeper.game.Game;
import com.soulkeeper.graphics.TextureAtlas;
import com.soulkeeper.utils.Utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Level {


    public static final int TILE_Scale = 8;
    public static final int TILE_IN_GAME_SCALE = 2;
    public static final int SCALED_TILE_SIZE = TILE_Scale * TILE_IN_GAME_SCALE;
    public static final int TILES_IN_WIDTH = Game.WIDTH / (SCALED_TILE_SIZE);
    public static final int TILES_IN_HIGHT = Game.HEIGHT / (SCALED_TILE_SIZE);

    private Integer[][] tileMap;

    private Map<TileType, Tile> tiles;//складываем тайлы
    private List<Point> grassCords;

    public Level(TextureAtlas atlas) {

        tileMap = new Integer[TILES_IN_WIDTH][TILES_IN_HIGHT];
        tiles = new HashMap<TileType, Tile>();
        tiles.put(TileType.BRICK, new Tile(atlas.cut(32 * TILE_Scale, 0, TILE_Scale, TILE_Scale),
                TILE_IN_GAME_SCALE, TileType.BRICK));
        tiles.put(TileType.METAL, new Tile(atlas.cut(32 * TILE_Scale, 2 * TILE_Scale, TILE_Scale, TILE_Scale),
                TILE_IN_GAME_SCALE, TileType.METAL));
        tiles.put(TileType.WATER, new Tile(atlas.cut(32 * TILE_Scale, 4 * TILE_Scale, TILE_Scale, TILE_Scale),
                TILE_IN_GAME_SCALE, TileType.WATER));
        tiles.put(TileType.GRASS, new Tile(atlas.cut(34 * TILE_Scale, 4 * TILE_Scale, TILE_Scale, TILE_Scale),
                TILE_IN_GAME_SCALE, TileType.GRASS));
        tiles.put(TileType.ICE, new Tile(atlas.cut(36 * TILE_Scale, 4 * TILE_Scale, TILE_Scale, TILE_Scale),
                TILE_IN_GAME_SCALE, TileType.ICE));
        tiles.put(TileType.EMPTY, new Tile(atlas.cut(36 * TILE_Scale, 6 * TILE_Scale, TILE_Scale, TILE_Scale),
                TILE_IN_GAME_SCALE, TileType.EMPTY));

        tileMap = Utils.levelParser("res/level.lvl");
        grassCords = new ArrayList<Point>();
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[i].length; j++) {
                Tile tile = tiles.get(TileType.fromNumeric(tileMap[i][j]));
                if (tile.type() == TileType.GRASS)
                    grassCords.add(new Point(j * SCALED_TILE_SIZE, i * SCALED_TILE_SIZE));

            }
        }
    }

    public void update() {

    }

    public void render(Graphics2D g) {
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[i].length; j++) {
                Tile tile = tiles.get(TileType.fromNumeric(tileMap[i][j]));
                if (tile.type() != TileType.GRASS)
                    tile.render(g, j * SCALED_TILE_SIZE, i * SCALED_TILE_SIZE);
            }
        }

    }

    public void renderGrass(Graphics2D g) {
        for (Point p :grassCords){
            tiles.get(TileType.GRASS).render(g, p.x,p.y);
        }
    }
}
