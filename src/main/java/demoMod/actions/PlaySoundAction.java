package demoMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import demoMod.sounds.DemoSoundMaster;

public class PlaySoundAction extends AbstractGameAction {
    private String key;

    public PlaySoundAction(String key) {
        this(key, 0.1F);
    }

    public PlaySoundAction(String key, float duration) {
        this.key = key;
        this.duration = duration;
        this.startDuration = this.duration;
    }

    @Override
    public void update() {
        if (this.duration == this.startDuration) {
            DemoSoundMaster.playV(key, 0.1F);
        }
        this.tickDuration();
    }
}
