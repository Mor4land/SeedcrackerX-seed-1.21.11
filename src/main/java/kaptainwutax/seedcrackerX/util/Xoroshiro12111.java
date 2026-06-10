package kaptainwutax.seedcrackerX.util;

/**
 * Утилитный класс для работы с Xoroshiro128++ в Minecraft 1.21.11.
 *
 * В 1.21.11 изменился алгоритм инициализации Xoroshiro128++ в ChunkGeneratorSettings:
 * перед записью в lo/hi состояние проходит дополнительное скремблирование через
 * Golden Ratio constant и циклический сдвиг на 21 бит.
 *
 * Этот класс предоставляет:
 * - forward: вычисление lo/hi из worldSeed (для валидации)
 * - inverse: восстановление worldSeed из lo/hi состояния (для крякера)
 */
public final class Xoroshiro12111 {

    /**
     * Golden Ratio constant (floor(2^64 / φ)), используется как MixConstant.
     */
    public static final long GOLDEN_RATIO = 0x9E3779B97F4A7C15L;

    /**
     * Fractal constant из SHA-256 initial hash, используется как начальный XOR.
     */
    public static final long SILVER_RATIO = 0x6A09E667F3BCC908L;

    private Xoroshiro12111() {
    }

    // ========== Forward (Seed → State) ==========

    /**
     * Вычислить lo-часть Xoroshiro128++ состояния из world seed.
     * Реализация как в MC 1.21.11 WorldgenRandom.
     *
     * @param seed world seed
     * @return lo часть внутреннего состояния
     */
    public static long computeLo(long seed) {
        long mixed = Long.rotateLeft(seed ^ GOLDEN_RATIO, 21);
        return mixed ^ SILVER_RATIO;
    }

    /**
     * Вычислить hi-часть Xoroshiro128++ состояния из world seed.
     *
     * @param seed world seed
     * @return hi часть внутреннего состояния
     */
    public static long computeHi(long seed) {
        return computeLo(seed) + GOLDEN_RATIO;
    }

    // ========== Inverse (State → Seed) ==========

    /**
     * Восстановить исходный world seed из lo-части Xoroshiro128++ состояния.
     * Обратная операция к {@link #computeLo(long)}.
     *
     * Алгоритм:
     * 1. XOR lo с SILVER_RATIO → получаем mixedSeed
     * 2. rotateRight(mixedSeed, 21) → снимаем циклический сдвиг
     * 3. XOR с GOLDEN_RATIO → получаем исходный seed
     *
     * @param lo lo-часть состояния Xoroshiro128++
     * @return восстановленный world seed
     */
    public static long getSeedFromLo(long lo) {
        long mixed = lo ^ SILVER_RATIO;
        return Long.rotateRight(mixed, 21) ^ GOLDEN_RATIO;
    }

    /**
     * Восстановить исходный world seed из hi-части Xoroshiro128++ состояния.
     *
     * @param hi hi-часть состояния Xoroshiro128++
     * @return восстановленный world seed
     */
    public static long getSeedFromHi(long hi) {
        return getSeedFromLo(hi - GOLDEN_RATIO);
    }

    /**
     * Восстановить world seed из полного состояния (lo, hi).
     * Валидирует согласованность lo и hi.
     *
     * @param lo lo-часть состояния
     * @param hi hi-часть состояния
     * @return восстановленный world seed
     * @throws IllegalArgumentException если lo и hi несогласованы
     */
    public static long getSeedFromState(long lo, long hi) {
        if (hi != lo + GOLDEN_RATIO) {
            throw new IllegalArgumentException("Inconsistent Xoroshiro state: hi != lo + GOLDEN_RATIO");
        }
        return getSeedFromLo(lo);
    }

    // ========== Validation ==========

    /**
     * Проверить, является ли пара (lo, hi) валидным начальным состоянием
     * Xoroshiro128++ для 1.21.11.
     *
     * @param lo lo-часть состояния
     * @param hi hi-часть состояния
     * @return true если hi == lo + GOLDEN_RATIO
     */
    public static boolean isValidInitialState(long lo, long hi) {
        return hi == lo + GOLDEN_RATIO;
    }
}
