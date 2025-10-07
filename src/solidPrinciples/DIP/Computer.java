package solidPrinciples.DIP;

import java.util.function.BiPredicate;

/*
 * @created 06/10/2025 -15:30
 * @project CoreJavaRepo
 * @author  bhartisingh
 */public class Computer {
     InputDevice inputDevice;

     Computer(InputDevice inputDevice){
         this.inputDevice = inputDevice;
     }


     void start(){
         inputDevice.input();
     }
}
