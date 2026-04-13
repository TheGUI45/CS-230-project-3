package com.gamingroom;

/**
 * Base type for all named entities in the game domain.
 */
public class Entity {
    private final long id;
    private final String name;

    public Entity() {
        this(0L, "");
    }

    public Entity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s [id=%d, name=%s]", getClass().getSimpleName(), id, name);
    }
}