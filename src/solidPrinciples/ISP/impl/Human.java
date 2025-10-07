package solidPrinciples.ISP.impl;

import solidPrinciples.ISP.Eatable;
import solidPrinciples.ISP.Workable;

/*
 * @created 06/10/2025 -15:17
 * @project CoreJavaRepo
 * @author  bhartisingh
 */public class Human implements Eatable, Workable {
    @Override
    public void eat() {
        System.out.println("Human can eat...");
    }

    @Override
    public void work() {
        System.out.println("Human can work...");
    }
}
