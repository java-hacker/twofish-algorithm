package nth.api.cryptography.twofish;

/**
 * @author Hau Trung Nguyen
 */
public abstract class Hasher {

    final static int[] h0 = {0, 0, 0, 0};
    final static int length = 16;

    public static byte[] hash(byte[] message) {
        int[] h = h0;
        for (int i = 0; i < message.length; i += length) {
            final int[] Mi = convertInput(message, i);
            final int[] A = new int[]{Mi[0] ^ h[0], Mi[1] ^ h[1], Mi[2] ^ h[2], Mi[3] ^ h[3]};
            final int[] B = h;
            final int[] C = Mi;
            final int[] encrypt = TwoFish.encrypt(A, B);
            h = new int[]{encrypt[0] ^ C[0], encrypt[1] ^ C[1], encrypt[2] ^ C[2], encrypt[3] ^ C[3]};
        }
        byte[] result = new byte[16];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[i * 4 + j] = (byte) (h[i] >>> (8 * j));
            }
        }
        return result;
    }

    /**
     * @param input
     * @param offset
     * @return
     */
    public static int[] convertInput(byte[] input, int offset) {
        int[] result = new int[4];
        for (int i = 0; i < 4; i++) {
            int temp = 0;
            for (int j = 0; j < 4; j++) {
                final int index = 4 * i + j + offset;
                if (index >= input.length) {
                    temp |= 0;
                } else {
                    temp |= ((0xFF & input[index]) << (8 * j));
                }
            }
            result[i] = temp;
        }
        return result;
    }
}
