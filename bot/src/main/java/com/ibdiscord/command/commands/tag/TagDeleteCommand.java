package com.ibdiscord.command.commands.tag;

import com.ibdiscord.command.Command;
import com.ibdiscord.command.CommandContext;
import com.ibdiscord.command.permissions.CommandPermission;
import com.ibdiscord.data.db.DContainer;
import com.ibdiscord.data.db.entries.TagData;
import com.ibdiscord.utils.UInput;
import net.dv8tion.jda.core.Permission;

import java.util.HashSet;
import java.util.List;

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
public final class TagDeleteCommand extends Command {

    /**
     * Creates the command.
     */
    TagDeleteCommand() {
        super("delete",
                new HashSet<>(),
                CommandPermission.discord(Permission.MANAGE_CHANNEL),
                new HashSet<>());
        this.correctUsage = "tag delete \"tag name\"";
    }

    /**
     * Deletes a tag.
     * @param context The command context.
     */
    @Override
    protected void execute(CommandContext context) {
        if(context.getArguments().length == 0) {
            sendUsage(context);
        } else {
            List<String> names = UInput.extractQuotedStrings(context.getArguments());
            if(names.isEmpty()) {
                context.reply("Invalid name, please provide it in quotation marks.");
                return;
            }
            TagData tagData = DContainer.INSTANCE.getGravity().load(new TagData(context.getGuild().getId()));
            tagData.unset(names.get(0));
            DContainer.INSTANCE.getGravity().save(tagData);
            context.reply("The tag has been removed.");
        }

    }
}
