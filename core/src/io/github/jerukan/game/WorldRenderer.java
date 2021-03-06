package io.github.jerukan.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.jerukan.game.board.BoardRenderer;
import io.github.jerukan.game.gameunits.UnitRenderer;
import io.github.jerukan.game.ui.UIRenderer;
import io.github.jerukan.game.ui.gamescreen.GameScreen;

/** Handles all the renderers and graphics related objects */

public class WorldRenderer {

    public static SpriteBatch batch = new SpriteBatch();

    public static float stateTime = 0;

    public static OrthographicCamera uiCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    public static BoardCamera boardCam = new BoardCamera(new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

    public static ScreenViewport uiViewport = new ScreenViewport(uiCam);
    public static Stage uiStage = new Stage(uiViewport);

    public static InputMultiplexer inputs = new InputMultiplexer();

    public static BoardRenderer boardRenderer = new BoardRenderer(GameState.instance.boardState, boardCam);
    public static UIRenderer uiRenderer = new UIRenderer(uiCam, uiStage);
    public static UnitRenderer unitRenderer = new UnitRenderer(GameState.instance.unitState);

    public static void init() {
        inputs.addProcessor(uiRenderer.getStage());
        inputs.addProcessor(new Input());
        Gdx.input.setInputProcessor(inputs);
        boardRenderer.init();
        uiRenderer.setCurrentScreen(new GameScreen(uiStage));
        uiRenderer.init();
    }

    public static void resize(int width, int height) {
        boardRenderer.resize(width, height);
        uiRenderer.resize(width, height);
        uiViewport.update(width, height, false);
    }

    public static void render() {
        stateTime += Gdx.graphics.getDeltaTime();
        boardCam.update();

        batch.setProjectionMatrix(boardCam.getCamera().combined);
        uiRenderer.updateVisibility();

        boardRenderer.render(batch);
        unitRenderer.render(batch, stateTime);
        uiRenderer.render(batch);
    }

    public static void dispose() {
        batch.dispose();
        boardRenderer.dispose();
        uiRenderer.dispose();
    }
}
