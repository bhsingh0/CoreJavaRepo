package solidPrinciples.ISP.impl;

import solidPrinciples.ISP.Workable;

/*
 * @created 06/10/2025 -15:18
 * @project CoreJavaRepo
 * @author  bhartisingh
 */public class Robot implements Workable {
    @Override
    public void work() {
        System.out.println("Robot can work...");
    }
}
