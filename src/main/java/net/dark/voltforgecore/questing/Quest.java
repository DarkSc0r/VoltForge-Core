package net.dark.voltforgecore.questing;

import net.minecraft.network.FriendlyByteBuf;

public class Quest {
    public final String id;
    public final String title;
    public final String description;
    private boolean completed;

    public Quest(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = false;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void complete() {
        this.completed = true;
    }

    // Optional: encode/decode for networking later
    public void encode(FriendlyByteBuf buf) {
        buf.writeUtf(id);
        buf.writeUtf(title);
        buf.writeUtf(description);
        buf.writeBoolean(completed);
    }

    public static Quest decode(FriendlyByteBuf buf) {
        Quest quest = new Quest(
                buf.readUtf(32767),
                buf.readUtf(32767),
                buf.readUtf(32767)
        );
        if (buf.readBoolean()) quest.complete();
        return quest;
    }
}
