package com.springcore.game;

import org.springframework.stereotype.Component;

@Component
public class Pacman implements GamingConsole{
    @Override
    public void up() {
        System.out.println("Pacman Fly");
    }

    @Override
    public void down() {
        System.out.println("Pacman Land");
    }

    @Override
    public void left() {
        System.out.println("Pacman stop");
    }

    @Override
    public void right() {
        System.out.println("Pacman accelerate");
    }
}
