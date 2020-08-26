package com.revature.util;

import com.revature.screens.Screen;

import java.util.HashSet;
import java.util.Set;

public class ScreenRouter {
    // using a set because it fits the needs of the application
    // is instantiated with AppState

    private Set<Screen> screens = new HashSet<>();

    public Set<Screen> getScreens(){
        return screens;
    }

    /**
     * method to addScreens inside of a collection
     *
     * @param screen
     * @return ScreenRouter
     */
    public ScreenRouter addScreen (Screen screen){
        System.out.println("LOG - loading"+ screen.getName()+" into the router");
        screens.add(screen);
        return this;
    }

    /**
     *here you stream the screens
     * and filter the screens with eqivalnt routes
     * find the first one and use the render method
     * or else throw a ScreenNotFoundException
     * @param route
     */
    public void navigate(String route){
        screens.stream()
                .filter(screen -> screen.getRoute().equals(route))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No screen found with that route."))
                .render();
    }

}
