package solidPrinciples.DIP;

import solidPrinciples.DIP.impl.Keyboard;
import solidPrinciples.DIP.impl.Mouse;

/*
 * @created 06/10/2025 -15:29
 * @project CoreJavaRepo
 * @author  bhartisingh
 */public class DIPTest {
    public static void main(String[] args) {
        InputDevice keyBoard = new Keyboard();
        Computer computer = new Computer(keyBoard);
        computer.start();
        InputDevice mouse = new Mouse();
        Computer computer1 = new Computer(mouse);
        computer1.start();
    }
}
