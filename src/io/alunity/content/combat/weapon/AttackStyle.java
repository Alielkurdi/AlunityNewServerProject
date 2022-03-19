package io.alunity.content.combat.weapon;

public enum AttackStyle {
    ACCURATE, AGGRESSIVE, CONTROLLED, DEFENSIVE;

    /**
     * Fetches the AttackStyle for the Attack style
     * @param style
     * @return
     */
    public static AttackStyle forStyle(int style) {
        return AttackStyle.values()[style];
    }

}
