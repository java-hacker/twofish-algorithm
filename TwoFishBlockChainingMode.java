package nth.api.cryptography.twofish;

/**
 * @author Hau Trung Nguyen
 */
public class TwoFishBlockChainingMode extends BlockChainingMode {

    @Override
    protected int[] encrypt(int[] block, int[] key) {
        return TwoFish.encrypt(block, key);
    }

    @Override
    protected int[] decrypt(int[] block, int[] key) {
        return TwoFish.decrypt(block, key);
    }
}
