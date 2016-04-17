/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package driver;

/**
 *
 * @author cadas
 */
import console.Console;
import controller.Controller;
import pbo.Aplikasi;

public class Driver {
    public static void main(String[] args) {
        Aplikasi a = new Aplikasi();
        //Console c = new Console(a);
        //c.menuUtama();
        new Controller(a);
    }
}
