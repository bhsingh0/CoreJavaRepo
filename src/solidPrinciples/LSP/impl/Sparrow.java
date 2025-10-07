package solidPrinciples.LSP.impl;

import solidPrinciples.LSP.FlyingBird;

/*
 * @created 06/10/2025 -14:58
 * @project CoreJavaRepo
 * @author  bhartisingh
 */public class Sparrow implements FlyingBird {

    @Override
    public void fly() {
        System.out.println("Sparrow is flying");
    }
}
