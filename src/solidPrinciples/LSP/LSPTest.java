package solidPrinciples.LSP;

import solidPrinciples.LSP.impl.Ostrich;
import solidPrinciples.LSP.impl.Sparrow;

/*
 * @created 06/10/2025 -14:55
 * @project CoreJavaRepo
 * @author  bhartisingh
 */public class LSPTest {
    public static void main(String[] args) {
        FlyingBird f = new Sparrow();
        f.fly();
        Ostrich ostrich = new Ostrich();
        ostrich.run();
    }
}
