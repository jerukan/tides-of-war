package io.github.jerukan.game.gameunits.unitdata;

import io.github.jerukan.game.GameState;
import io.github.jerukan.game.Player;
import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.game.gameunits.unitdata.unitactions.UnitAction;
import io.github.jerukan.util.Assets;
import io.github.jerukan.util.Position;

public class VillageUnit extends BaseUnit {

    public VillageUnit() {
        name = "village";
        baseHealth = 20;

        baseCost = 0;

        actions = new UnitAction[]{};

        baseUpkeep = 0;

        type = Type.BUILDING;

        description = "This is your lifeline. Don\'t let it die.";
        setTexture(Assets.getTexture(Assets.village));
        oddAnimation = true;
    }

    @Override
    public boolean _canBuild(Position pos, Player owner) {
        return canBuild(owner);
    }

    @Override
    public boolean canBuild(Player owner) {
        return !GameState.instance.unitState.playerHasUnit(owner, id);
    }

    @Override
    public void onCreation(Unit self) {

    }

    @Override
    public void onDeath(Unit self) {

    }

    @Override
    public void onTurnStart(Unit self) {

    }

    @Override
    public void onTurnEnd(Unit self) {

    }
}
