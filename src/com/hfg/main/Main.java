/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hfg.main;

import com.hfg.main.loop.APPLoop;

/**
 *
 * @author dev
 */
public class Main {

    public static void main(String[] args) {
        APPLoop loop = new APPLoop();
        loop.init();
    }
}
