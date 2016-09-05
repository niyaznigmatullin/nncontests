package coding;

public class BuffingPixie {
    public int fastestVictory(int hp, int normalAttack, int buffedAttack) {
        if (normalAttack * 2 >= buffedAttack) {
            return (hp + normalAttack - 1) / normalAttack;
        }
        if (hp <= normalAttack) {
            return 1;
        }
        int turns = (hp + buffedAttack - 1) / buffedAttack;
        turns *= 2;
        int turns2 = (hp - normalAttack + buffedAttack - 1) / buffedAttack;
        turns2 = (turns2 * 2 + 1);
        return Math.min(turns, turns2);
    }
}
