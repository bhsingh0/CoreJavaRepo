package solidPrinciples.ISP;

import solidPrinciples.ISP.impl.Human;
import solidPrinciples.ISP.impl.Robot;

/*
 * @created 06/10/2025 -15:13
 * @project CoreJavaRepo
 * @author  bhartisingh
 */public class ISPTest {
    public static void main(String[] args) {
        Human human = new Human();
        human.eat();
        human.work();

        Robot robot = new Robot();
        robot.work();
    }
}
