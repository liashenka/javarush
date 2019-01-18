package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Wall extends CollisionObject {
    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(new Color(72, 58, 52));

        int leftUpperCornerX = getX();
        int leftUpperCornerY = getY();

        graphics.drawRect(leftUpperCornerX, leftUpperCornerY, getWidth() * 20, getHeight() * 20);
        graphics.fillRect(leftUpperCornerX, leftUpperCornerY, getWidth() * 20, getHeight() * 20);
    }
}
