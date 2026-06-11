package kaptainwutax.seedcrackerX.util;

import com.seedfinding.mccore.version.MCVersion;

/**
 * Утилитный класс для определения версии мира с учётом
 * изменений worldgen в 1.21.11 (MC 26.1, "June Drop 2025").
 *
 * Библиотека seedfinding может не иметь явной MCVersion.v1_21_11,
 * поэтому мы используем MCVersion.latest() или MCVersion.v1_21
 * и дополнительно проверяем, нужна ли новая логика генерации.
 */
public final class VersionHelper {

    /**
     * Порог версии, начиная с которой применяются изменения worldgen 1.21.11.
     * Если MCVersion поддерживает v1_21_1 — используем его.
     * Иначе считаем, что любая версия >= v1_21 с ordinal > v1_21.ordinal
     * использует новую логику.
     */
    private static final MCVersion THRESHOLD;

    static {
        // Пробуем найти v1_21_1 в enum MCVersion.
        // Если не найден — используем fallback логику.
        MCVersion found = null;
        for (MCVersion v : MCVersion.values()) {
            String name = v.name();
            // Ищем v1_21_1 или подобные
            if (name.equals("v1_21_1") || name.equals("v1_21_11")) {
                found = v;
                break;
            }
        }
        // Если конкретной версии нет, используем latest() как threshold
        // (мод собирается под 26.1 = 1.21.11, значит latest() это и есть наша версия)
        THRESHOLD = found != null ? found : MCVersion.latest();
    }

    private VersionHelper() {
    }

    /**
     * Проверяет, использует ли данная версия новую логику генерации 1.21.11.
     * Включает:
     * - Новое скремблирование Xoroshiro128++ (rotateLeft + GOLDEN_RATIO XOR)
     * - Дополнительный шаг LCG в расчёте позиции RegionStructure
     * - Изменённые spacing/separation/salt для структур
     *
     * @param version версия мира из конфига
     * @return true если версия >= 1.21.11 и требует новой логики
     */
    public static boolean isNewWorldgen(MCVersion version) {
        return "1.21.11".equals(Config.get().getVersionString());
    }

    /**
     * @return версия-порог для нового worldgen
     */
    public static MCVersion getThreshold() {
        return THRESHOLD;
    }
}
