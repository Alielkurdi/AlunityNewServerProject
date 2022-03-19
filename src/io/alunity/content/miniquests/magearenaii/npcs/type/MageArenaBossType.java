package io.alunity.content.miniquests.magearenaii.npcs.type;

public enum MageArenaBossType {

    JUSTICIAR_ZACHARIAH(7858, 28, 2412),
    DERWEN(7859, 29, 2413),
    PORAZDIR(7860, 30, 2414);

    public int npcId, spellRequired, capeRequired;

    MageArenaBossType(int npcId, int spellRequired, int capeRequired) {
        this.npcId = npcId;
        this.spellRequired = spellRequired;
        this.capeRequired = capeRequired;

    }

}
