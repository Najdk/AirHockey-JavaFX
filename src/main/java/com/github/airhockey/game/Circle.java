/*
 * Developed by Boris Kozhuhovskiy (borisgk98@ya.ru) on 11.05.19 22:09.
 * airhockey
 */

/*
 * Developed by Boris Kozhuhovskiy (borisgk98@ya.ru) on 11.05.19 22:09.
 * airhockey
 */

package com.github.airhockey.game;

public interface Circle extends GameMapObject {

    Double getRadius();
    Color getColor();
    Vector2 getCenter();

    Double distance(Circle c);
}
