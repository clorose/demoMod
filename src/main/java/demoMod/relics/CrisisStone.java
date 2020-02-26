package demoMod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import demoMod.DemoMod;
import demoMod.combo.Combo;
import demoMod.combo.ComboManager;
import demoMod.interfaces.PostReloadSubscriber;
import demoMod.sounds.DemoSoundMaster;

public class CrisisStone extends CustomRelic implements Combo, PostReloadSubscriber {
    public static final String ID = DemoMod.makeID("CrisisStone");
    public static final String IMG_PATH = "relics/crisisStone.png";
    public static final String OUTLINE_IMG_PATH = "relics/crisisStoneOutline.png";

    public static final Texture comboTexture = new Texture(DemoMod.getResourcePath("combos/relics/crisisStone.png"));

    private boolean isRemoving = false;
    private int amount = 0;

    public CrisisStone() {
        super(ID, new Texture(DemoMod.getResourcePath(IMG_PATH)), new Texture(DemoMod.getResourcePath(OUTLINE_IMG_PATH)),
                RelicTier.UNCOMMON, LandingSound.HEAVY);
    }

    @Override
    public void onEquip() {
        ComboManager.detectComboInGame();
    }

    @Override
    public void onUnequip() {
        isRemoving = true;
        ComboManager.detectCombo();
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void onReload() {
        AbstractPlayer p = AbstractDungeon.player;
        if (this.amount > 0) {
            DemoSoundMaster.playA("RELIC_CRISIS_STONE", 0F);
            this.flash();
            for (int i=0;i<this.amount;i++) {
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, 8));
            }
        }
        this.amount = 0;
    }

    public void onReload(int amount) {
        this.amount += amount;
    }

    @Override
    public String getItemId() {
        return ID;
    }

    @Override
    public void onComboActivated(String comboId) {

    }

    @Override
    public void onComboDisabled(String comboId) {

    }

    @Override
    public boolean isRemoving() {
        return isRemoving;
    }

    @Override
    public Texture getComboPortrait() {
        return comboTexture;
    }

    static {
        ComboManager.addCombo(DemoMod.makeID("HumanShield"), CrisisStone.class);
    }
}
