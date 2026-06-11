package kaptainwutax.seedcrackerX.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.seedfinding.mccore.version.MCVersion;
import kaptainwutax.seedcrackerX.config.Config;
import kaptainwutax.seedcrackerX.util.Log;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.ChatFormatting;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.*;

public class VersionCommand extends ClientCommand {

    @Override
    public String getName() {
        return "version";
    }

    @Override
    public void build(LiteralArgumentBuilder<FabricClientCommandSource> builder) {
        builder.then(literal("1.21.11").executes(context -> this.setVersionString("1.21.11")));

        for (MCVersion version : MCVersion.values()) {
            if (version.isOlderThan(MCVersion.v1_8)) continue;
            builder.then(literal(version.name).executes(context -> this.setVersionString(version.toString())));
        }
    }

    private int setVersionString(String version) {
        Config.get().setVersionString(version);
        Config.save();
        ClientCommand.sendFeedback(Log.translate("version.setVersion") + " " + version + ".", ChatFormatting.AQUA, true);
        return 0;
    }

}
