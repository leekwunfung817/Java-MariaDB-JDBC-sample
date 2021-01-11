/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**
 *
 * @author ivan.lee
 */
public class Main {
    
    public static void main(String[] args) {
        Mariadb.query("select 'Hello' str1,'World' str2");
    }

}
