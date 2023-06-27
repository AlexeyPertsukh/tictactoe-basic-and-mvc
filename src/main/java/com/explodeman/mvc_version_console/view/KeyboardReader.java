package com.explodeman.mvc_version_console.view;

import com.explodeman.mvc_version.view.Reader;

import java.util.Scanner;

public class KeyboardReader implements Reader {
    @Override
    public String input() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
