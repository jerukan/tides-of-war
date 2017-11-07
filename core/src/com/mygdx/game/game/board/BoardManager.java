package com.mygdx.game.game.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.game.util.Constants;
import com.mygdx.game.util.SpriteManager;

public class BoardManager {

    private OrthographicCamera camera;

    private ShapeRenderer highlighter;

    private float camOriginX, camOriginY;

    private Tile[][] board;

    public BoardManager(OrthographicCamera camera) {
        highlighter = new ShapeRenderer();
        this.camera = camera;
    }

    public Tile[][] getBoard() {
        return board;
    }

    public void init() {
        highlighter.setProjectionMatrix(camera.combined);
        camOriginX = Gdx.graphics.getWidth() / 2;
        camOriginY = Gdx.graphics.getHeight() / 2;
    }

    public void resize(int width, int height) {
        camOriginX = Gdx.graphics.getWidth() / 2;
        camOriginY = Gdx.graphics.getHeight() / 2;
        camera.setToOrtho(false, width, height);
    }

    public void resetBoard() {
        board = new Tile[Constants.BOARD_WIDTH][Constants.BOARD_HEIGHT];
        for(int x = 0; x < Constants.BOARD_WIDTH; x++) {
            for(int y = 0; y < Constants.BOARD_HEIGHT; y++) {
                int[] pos = {x, y};
                board[x][y] = new Tile(pos, new Sprite(SpriteManager.assetManager.get(SpriteManager.grass1)));
                board[x][y].setSpritePosition(Constants.TILE_SIZE * x, Constants.TILE_SIZE * y);
            }
        }
    }

    public void render(Batch batch) {
        highlighter.setProjectionMatrix(camera.combined);
        int mousex = Gdx.input.getX();
        int mousey = Gdx.input.getY();
        for(int x = 0; x < Constants.BOARD_WIDTH; x++) {
            for(int y = 0; y < Constants.BOARD_HEIGHT; y++) {
                board[x][y].render(batch);
            }
        }
        int x = (int)(mousex + camera.position.x - camOriginX) / Constants.TILE_SIZE;
        int y = (int)(Gdx.graphics.getHeight() - mousey + camera.position.y - camOriginY) / Constants.TILE_SIZE;

        if(x >= 0 && x < Constants.BOARD_WIDTH && y >= 0 && y < Constants.BOARD_HEIGHT) {
            Gdx.gl20.glEnable(GL20.GL_BLEND);
            Gdx.gl20.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            highlighter.begin(ShapeType.Filled);
            highlighter.setColor(0, 0, 1, 0.5f);
            highlighter.rect(board[x][y].getSprite().getX(), board[x][y].getSprite().getY(), Constants.TILE_SIZE, Constants.TILE_SIZE);
            highlighter.end();
            Gdx.gl20.glDisable(GL20.GL_BLEND);
        }
    }

    public void dispose() {
        for(int x = 0; x < Constants.BOARD_WIDTH; x++) {
            for(int y = 0; y < Constants.BOARD_HEIGHT; y++) {
                board[x][y].dispose();
            }
        }
        highlighter.dispose();
    }
}
