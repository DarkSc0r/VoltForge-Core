package net.dark.voltforgecore.questing;

import java.util.ArrayList;
import java.util.List;

public class QuestManager {
    // List of all quests in the mod
    private static final List<Quest> quests = new ArrayList<>();

    static {
        // Example quests
        quests.add(new Quest("mine_tin", "Mine 1 Tin", "Mine 1 tin ore."));
        quests.add(new Quest("craft_furnace", "Craft a Furnace", "Craft a furnace using 8 cobblestone."));
        quests.add(new Quest("build_shelter", "Build a Shelter", "Build a small shelter to survive your first night."));
        quests.add(new Quest("test_quest", "TEST", "TEST"));
    }

    // Get all quests (for GUI display)
    public static List<Quest> getQuests() {
        return quests;
    }

    // Find a quest by ID
    public static Quest getQuestById(String id) {
        return quests.stream().filter(q -> q.id.equals(id)).findFirst().orElse(null);
    }
}
