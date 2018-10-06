package com.javarush.task.task35.task3513;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;

public class Controller extends KeyAdapter {  //будет следить за нажатием клавиш во время игры
    Model model;
    View view;
    private static final int WINNING_TILE = 2048;

    public Controller(Model model) {
        this.model = model;
        this.view = new View(this);
    }

    public Tile[][] getGameTiles() {
        return model.getGameTiles();
    }

    public int getScore() {
        return model.score;
    }

    public void resetGame() {
        view.isGameWon = false;
        view.isGameLost = false;
        model.resetGameTiles();
        model.score = 0;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {
            resetGame();
        }

        if (!model.canMove()) {
            view.isGameLost = true;
        } else {
            if (view.isGameLost == false && view.isGameWon == false) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) model.left();
                if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) model.right();
                if (keyEvent.getKeyCode() == KeyEvent.VK_UP) model.up();
                if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) model.down();
            }
        }

        if(model.maxTile == WINNING_TILE) view.isGameWon = true;

        if(keyEvent.getKeyCode() == KeyEvent.VK_Z) model.rollback();

        if(keyEvent.getKeyCode() == KeyEvent.VK_R) model.randomMove();

        if(keyEvent.getKeyCode() == KeyEvent.VK_A) model.autoMove();


        view.repaint();


    }

    public View getView() {
        return view;
    }
}
