package io.github.jerukan.game.ui.gamescreen.menus;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import io.github.jerukan.game.GameState;
import io.github.jerukan.game.WorldRenderer;
import io.github.jerukan.game.board.BoardManager;
import io.github.jerukan.game.ui.ButtonGroup;
import io.github.jerukan.util.Assets;
import io.github.jerukan.util.NamedFlag;
import io.github.jerukan.util.Constants;

/** The menu that pops up when a tile is selected
 * TODO radial menu would be cool */

public class TileSelectMenu extends ButtonGroup {

    private final TextButton buildButton;

    public TileSelectMenu(NamedFlag[] flags) {
        super(flags);

        buildButton = new TextButton("Build", Assets.uiskin, "default");
        buildButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                flagFromArray("build").setState(true);
            }
        });
        table.align(Align.left);

        table.add(buildButton).pad(10).row();
    }

    @Override
    public void updateVisibility() {
        // when a valid tile is selected
        if(flagFromArray("build").getState()) {
            table.setVisible(false);
        }
        else if(BoardManager.getSelectedPosition().isValid()) {
            if(GameState.instance.unitState.unitFromPosition(BoardManager.getSelectedPosition()) == null) {
                WorldRenderer.boardCam.updateOffsets();
                Sprite s = GameState.instance.boardState.tileFromPosition(BoardManager.getSelectedPosition()).getSprite();
                table.setPosition(s.getX() + s.getWidth() + Constants.TILE_MENU_OFFSET - WorldRenderer.boardCam.camOffsetX, s.getY() - WorldRenderer.boardCam.camOffsetY);
                table.setVisible(true);
            }
            else {
                table.setVisible(false);
            }
        }
        else {
            table.setVisible(false);
        }
    }
}
