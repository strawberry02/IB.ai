package com.ibdiscord.command.commands.tag;

import com.ibdiscord.command.Command;
import com.ibdiscord.command.CommandContext;
import com.ibdiscord.command.permissions.CommandPermission;
import net.dv8tion.jda.core.Permission;

import java.util.Set;

/**
 * Copyright 2019 Ray Clark
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public final class TagCommand extends Command {

    /**
     * Creates the command.
     */
    public TagCommand() {
        super("tag",
                Set.of("tags"),
                CommandPermission.discord(Permission.MANAGE_CHANNEL),
                Set.of(new TagCreateCommand(), new TagDeleteCommand(), new TagListCommand())
        );
        this.correctUsage = "tag <create/delete/list> \"tag name\" \"tag value\"";
    }

    /**
     * Shows a list of sub commands.
     * @param context The command context.
     */
    @Override
    protected void execute(CommandContext context) {
        sendUsage(context);
    }

}
