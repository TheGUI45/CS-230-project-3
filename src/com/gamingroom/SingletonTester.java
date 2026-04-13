package com.gamingroom;

public class SingletonTester {

    public void testSingleton() {
        System.out.println("Testing singleton pattern");

        GameService serviceOne = GameService.getInstance();
        GameService serviceTwo = GameService.getInstance();

        System.out.println("Service one hash: " + serviceOne.hashCode());
        System.out.println("Service two hash: " + serviceTwo.hashCode());
        System.out.println("Single instance confirmed: " + (serviceOne == serviceTwo));
    }
}