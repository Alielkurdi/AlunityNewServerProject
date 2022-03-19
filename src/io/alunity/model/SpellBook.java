package io.alunity.model;

import io.alunity.util.Misc;

public enum SpellBook {
    MODERN, ANCIENT, LUNAR
    ;

    @Override
    public String toString() {
        return Misc.formatPlayerName(name().toLowerCase());
    }
}
