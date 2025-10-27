package net.dark.voltforgecore.questing;

import java.util.ArrayList;
import java.util.List;

public class QuestNode {
    public final Quest quest;
    public final int x, y;
    public final int width, height;

    public QuestNode(Quest quest, int x, int y, int width, int height) {
        this.quest = quest;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Check if mouse is over this node
    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX >= x && mouseX <= x + width &&
                mouseY >= y && mouseY <= y + height;
    }
}
