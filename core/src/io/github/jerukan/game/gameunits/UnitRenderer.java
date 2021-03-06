package io.github.jerukan.game.gameunits;

import com.badlogic.gdx.graphics.g2d.Batch;
import io.github.jerukan.game.Renderer;

public class UnitRenderer implements Renderer {

    private UnitState unitState;

    public UnitRenderer(UnitState unitState) {
        this.unitState = unitState;
    }

    @Override
    public void init() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render(Batch batch) {

    }

    @Override
    public void render(Batch batch, float stateTime) {
        batch.begin();
        for(Unit unit : unitState.getUnitlist()) {
            unit.render(batch, stateTime);
        }
        batch.end();
    }
}
