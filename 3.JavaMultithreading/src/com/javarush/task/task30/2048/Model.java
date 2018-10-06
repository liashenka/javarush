package com.javarush.task.task35.task3513;

import java.lang.reflect.Field;
import java.util.*;

public class Model {    //будет содержать игровую логику и хранить игровое поле
    private static final int FIELD_WIDTH = 4;
    Stack<Tile[][]> previousStates = new Stack();
    Stack<Integer> previousScores = new Stack<>();
    private Tile[][] gameTiles;
    protected int score = 0;
    protected int maxTile = 0;
    private Model model;
    private View view;
    private boolean isSaveNeeded = true;

    public int getScore() {
        return score;
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public Model() {
        resetGameTiles();
    }

    private void saveState(Tile[][] tiles) {
        Tile[][] tempGameTiles = new Tile[tiles.length][tiles[0].length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                tempGameTiles[i][j] = new Tile(tiles[i][j].value);
            }
        }
        previousStates.push(tempGameTiles);
        int tempScore = score;
        previousScores.push(tempScore);
        isSaveNeeded = false;

    }

    public void rollback() {
        if (!previousStates.isEmpty()) {
            gameTiles = (Tile[][]) previousStates.pop();
        }
        if (!previousScores.isEmpty()) {
            score = (int) previousScores.pop();
        }
    }


    private List<Tile> getEmptyTiles() {
        List<Tile> emptyTileList = new ArrayList<>();
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].value == 0)
                    emptyTileList.add(gameTiles[i][j]);
            }
        }
        return emptyTileList;
    }

    private void addTile() {
        List<Tile> listForChanges = getEmptyTiles();
        if (listForChanges.size() != 0 && listForChanges != null) {
            listForChanges.get((int) (listForChanges.size() * Math.random())).value = (Math.random() < 0.9 ? 2 : 4);
        }
    }

    public void resetGameTiles() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean changes = false;
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].value == 0 && i < tiles.length - 1 && tiles[i + 1].value != 0) {
                Tile temp = tiles[i];
                tiles[i] = tiles[i + 1];
                tiles[i + 1] = temp;
                i = -1;
                changes = true;
            }
        }
        return changes;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean changes = false;
        for (int i = 0; i < tiles.length - 1; i++) {
            if (tiles[i].value != 0 && (tiles[i].value == tiles[i + 1].value)) {
                if (tiles[i].value + tiles[i + 1].value > maxTile) {
                    maxTile = tiles[i].value + tiles[i + 1].value;
                }
                tiles[i].value = tiles[i].value + tiles[i + 1].value;
                score += tiles[i].value;
                tiles[i + 1].value = 0;
                changes = true;
                compressTiles(tiles);
            }
        }
        return changes;
    }

    public void left() {
        if (isSaveNeeded) {
            saveState(this.gameTiles);
        }
        boolean isChanged = false;
        for (int i = 0; i < gameTiles.length; i++) {
            if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i])) {
                isChanged = true;
            }
        }
        isSaveNeeded = true;
        if (isChanged) addTile();
    }

    public void right() {
        saveState(this.gameTiles);
        rotation();
        rotation();
        left();
        rotation();
        rotation();
    }

    public void up() {
        saveState(this.gameTiles);
        rotation();
        left();
        rotation();
        rotation();
        rotation();
    }

    public void down() {
        saveState(this.gameTiles);
        rotation();
        rotation();
        rotation();
        left();
        rotation();
    }

    public void rotation() {
        for (int k = 0; k < 2; k++) {
            for (int j = k; j < 3 - k; j++) {
                Tile tmp = gameTiles[k][j];
                gameTiles[k][j] = gameTiles[j][3 - k];
                gameTiles[j][3 - k] = gameTiles[3 - k][3 - j];
                gameTiles[3 - k][3 - j] = gameTiles[3 - j][k];
                gameTiles[3 - j][k] = tmp;
            }
        }
    }

    public void rotate() {
        for (int k = 0; k < 2; k++) {
            for (int j = k; j < 3 - k; j++) {
                Tile tmp = gameTiles[k][j];
                gameTiles[k][j] = gameTiles[j][3 - k];
                gameTiles[j][3 - k] = gameTiles[3 - k][3 - j];
                gameTiles[3 - k][3 - j] = gameTiles[3 - j][k];
                gameTiles[3 - j][k] = tmp;
            }
        }
    }

    public boolean canMove() {
        if (!getEmptyTiles().isEmpty()) return true;

        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles.length - 1; j++) {
                if (gameTiles[i][j].value == gameTiles[i][j + 1].value)
                    return true;
            }
        }

        for (int j = 0; j < gameTiles.length; j++) {
            for (int i = 0; i < gameTiles.length - 1; i++) {
                if (gameTiles[i][j].value == gameTiles[i + 1][j].value)
                    return true;
            }
        }

        return false;
    }

    public void randomMove(){
        
        switch(((int) (Math.random() * 100 % 4))){

            case 0 : left();
            break;

            case 1 : right();
            break;

            case 2 : up();
            break;

            case 3 : down();
            break;
        }
    }

    public boolean hasBoardChanged(){
        int sum1 = 0;
        int sum2 = 0;

        if(!previousStates.isEmpty()){
            Tile[][] previusStatesArray = previousStates.peek();
            for (int i = 0; i < FIELD_WIDTH ; i++) {
                for (int j = 0; j < FIELD_WIDTH ; j++) {
                    sum1 += gameTiles[i][j].value;
                    sum2 += previusStatesArray[i][j].value;
                }
            }
        }
        return sum1 != sum2;
    }

    public MoveEfficiency getMoveEfficiency(Move move){
        MoveEfficiency moveEfficiency;
        move.move();
        if(hasBoardChanged()) moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);
        else moveEfficiency = new MoveEfficiency(-1, 0, move);
        rollback();

        return moveEfficiency;
    }

    public void autoMove(){
        PriorityQueue<MoveEfficiency> queue = new PriorityQueue<>(4, Collections.reverseOrder());
        queue.offer(getMoveEfficiency(this::left));
        queue.offer(getMoveEfficiency(this::right));
        queue.offer(getMoveEfficiency(this::up));
        queue.offer(getMoveEfficiency(this::down));

        queue.peek().getMove().move();
    }

}
