package solidPrinciples.DIP.impl;

import solidPrinciples.DIP.InputDevice;

/*
 * @created 06/10/2025 -15:29
 * @project CoreJavaRepo
 * @author  bhartisingh
 */public class Keyboard implements InputDevice {
    @Override
    public void input() {
        System.out.println("Typing with keyboard");
    }
}
