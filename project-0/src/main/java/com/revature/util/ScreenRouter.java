package com.revature.util;

import com.revature.screens.Screen;

import java.util.HashSet;
import java.util.Set;

public class ScreenRouter {
    // using a set because it fits the needs of the application

    private Set<Screen> screens = new HashSet<>();

    public Set<Screen> getScreens(){
        return screens;
    }

    public ScreenRouter addScreen (Screen screen){
        System.out.println("LOG - loading"+ screen.getName()+" into the router");
        screens.add(screen);
        return this;
    }

    public void navigate(String route){
        screens.stream()
                .filter(screen -> screen.getRoute().equals(route))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No screen found with that route."))
                .render();
    }

}
