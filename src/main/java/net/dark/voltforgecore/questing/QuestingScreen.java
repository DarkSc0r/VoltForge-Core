package net.dark.voltforgecore.questing;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;

import java.util.ArrayList;
import java.util.List;

public class QuestingScreen extends Screen {
    private List<Quest> quests;
    private final List<QuestNode> questNodes = new ArrayList<>();
    private int selectedQuestIndex = -1;

    // Layout constants
    private static final int NODE_SIZE = 60;
    private static final int NODE_SPACING = 20;
    private static final int GRID_COLUMNS = 5;

    public QuestingScreen() {
        super(Component.literal("VoltForge Questing"));
        this.quests = QuestManager.getQuests(); // Pull from QuestManager
    }

    @Override
    protected void init() {
        super.init();
        questNodes.clear();

        // --- Grid positioning ---
        int totalGridWidth = (GRID_COLUMNS * NODE_SIZE) + (GRID_COLUMNS - 1) * NODE_SPACING;
        int startX = (this.width / 2) - totalGridWidth / 2 - 100; // shift grid to the left side of the screen
        int startY = 60;

        for (int i = 0; i < quests.size(); i++) {
            Quest quest = quests.get(i);
            int col = i % GRID_COLUMNS;
            int row = i / GRID_COLUMNS;
            int x = startX + col * (NODE_SIZE + NODE_SPACING);
            int y = startY + row * (NODE_SIZE + NODE_SPACING);
            questNodes.add(new QuestNode(quest, x, y, NODE_SIZE, NODE_SIZE));
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // 1. Background
        guiGraphics.fill(0, 0, this.width, this.height, 0xC0101010);

        // 8. Draw widgets (buttons etc.) on top
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        // 2. Title
        guiGraphics.drawCenteredString(this.font, "VoltForge Questing", this.width / 2, 10, 0xFFFFFF);

        // 3. Quest area background
        int leftAreaWidth = this.width / 2 - 60;
        guiGraphics.fill(20, 40, leftAreaWidth, this.height - 40, 0xFF151515); // dark gray left panel

        // 4. Description panel
        int panelX = this.width / 2 + 40;
        int panelY = 60;
        int panelWidth = 200;
        int panelHeight = this.height - 100;
        guiGraphics.fill(panelX, panelY, panelX + panelWidth, panelY + panelHeight, 0xFF202020);

        // 5. Render quest nodes
        for (QuestNode node : questNodes) {
            int color = node.quest.isCompleted() ? 0xFF00AA00 : 0xFF555555;
            guiGraphics.fill(node.x, node.y, node.x + node.width, node.y + node.height, color);

            // Highlight if hovered
            if (node.isHovered(mouseX, mouseY)) {
                guiGraphics.fill(node.x, node.y, node.x + node.width, node.y + node.height, 0x66FFFFFF);
            }

            // Title centered
            guiGraphics.drawCenteredString(
                    this.font,
                    node.quest.title,
                    node.x + node.width / 2,
                    node.y + node.height / 2 - this.font.lineHeight / 2,
                    0xFFFFFFFF
            );
        }

        // 6. Handle quest hover
        selectedQuestIndex = -1;
        for (int i = 0; i < questNodes.size(); i++) {
            if (questNodes.get(i).isHovered(mouseX, mouseY)) {
                selectedQuestIndex = i;
                break;
            }
        }

        // 7. Draw description
        if (selectedQuestIndex >= 0) {
            Quest selected = questNodes.get(selectedQuestIndex).quest;
            FormattedText desc = Component.literal(selected.description);

            guiGraphics.drawString(this.font, "Quest:", panelX + 10, panelY + 10, 0xFFFFFF);
            guiGraphics.drawString(this.font, selected.title, panelX + 10, panelY + 25, 0xAAAAFF);
            guiGraphics.drawWordWrap(this.font, desc, panelWidth - 20, panelX + 10, panelY + 45, 0xFFFFFF);
        }


    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (int i = 0; i < questNodes.size(); i++) {
            QuestNode node = questNodes.get(i);
            if (node.isHovered(mouseX, mouseY)) {
                selectedQuestIndex = i;
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}