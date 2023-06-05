package org.example;

import java.util.List;

public interface BallStrategy {
    List<Components> ballIn(boolean in, List<Components> balls, List<List<Double>> pockets, GameWindow w);
}
