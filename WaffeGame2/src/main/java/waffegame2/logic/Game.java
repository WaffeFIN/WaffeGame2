/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waffegame2.logic;

import java.util.ArrayList;
import java.util.List;
import waffegame2.player.*;
import waffegame2.ui.*;

/**
 *
 * @author Walter Grönholm
 * @version 1.0
 * @since 2016-01-02
 */
public class Game implements Runnable {

    private UI ui;
    private GameRules rules;
    private GameLogic logic;

    public Game(UI ui) {
        this.ui = ui;
    }

    public void setGamelogic(GameLogic logic) {
        this.logic = logic;
        this.rules = logic.getRules();
    }

    @Override
    public void run() {
        ui.println("Welcome to WaffeGame2!");
        setGamelogic(new GameLogicWaffeGame2(ui));

        rules.setAllOptions(ui);

        logic.addPlayers(ui.getPlayers(rules.getMinPlayers(), rules.getMaxPlayers()));
        logic.init();

        while (true) {
            logic.setup();
            logic.playGame();
            break;
        }
    }
}
