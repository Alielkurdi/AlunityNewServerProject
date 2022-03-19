package io.alunity.content.combat.weapon;

public enum RangedWeaponType {
    SHOT,
    DOUBLE_SHOT,
    THROWN,
    NO_ARROWS
    ;

    public boolean noArrows() {
        return this == NO_ARROWS || this == THROWN;
    }
}
