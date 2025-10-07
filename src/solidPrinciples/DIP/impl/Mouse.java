package solidPrinciples.DIP.impl;

import solidPrinciples.DIP.InputDevice;

/*
 * @created 06/10/2025 -15:30
 * @project CoreJavaRepo
 * @author  bhartisingh
 */public class Mouse implements InputDevice {
    @Override
    public void input() {
        System.out.println("Clicking with mouse");
    }
}