/*******************************************************************************
 * Copyright 2018 pants
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

/* * * * * * * * * * * * * * * * * * * * * * * * * * * */
package gg.discord.ibo.commands.subjects;

import gg.discord.ibo.commands.CommandAbstract;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
/* * * * * * * * * * * * * * * * * * * * * * * * * * * */

/**
 * @author pants
 * @since 2018.03.06
 */
 
/* * * * * * * * * * * * * * * * * * * * * * * * * * * */

public class CMDJoin extends CommandAbstract{
    @Override
    public boolean safe(GuildMessageReceivedEvent event,
                        Guild guild,
                        User user){
        return true;
    }

    @Override
    public void execute(GuildMessageReceivedEvent event,
                        Guild guild,
                        User user,
                        Message message,
                        String[] args,
                        String raw){

        StringBuilder roleBuilder = new StringBuilder();
        for(int i = 0; i < args.length; i++){
            if(i+1 == args.length){
                roleBuilder.append(args[i]);
                break;
            }

            roleBuilder.append(args[i]);
            roleBuilder.append(" ");
        }
        String roleName = roleBuilder.toString();

        boolean perms = false;
        if(!guild.getRolesByName(roleName, true).isEmpty()){
            for(int i = 0; i < guild.getMember(user).getRoles().size(); i++){
                if(guild.getMember(user).getRoles().get(i).getPosition() >
                        guild.getRolesByName(roleName, true).get(0).getPosition()){

                    Role roleToAdd = guild.getRolesByName(roleName, true).get(0);
                    guild.getController().addRolesToMember(message.getMember(), roleToAdd).complete();
                    perms = true;
                }
            }
            if(!perms){
                event.getChannel().sendMessage("You probably don't have permissions to join that role...").queue();
                return;
            }

        }else{
            event.getChannel().sendMessage("A role by the name of \"" + roleName + "\" does not exist!").queue();
            return;
        }

        event.getChannel().sendMessage("Role has been added! (" + roleName + ")").queue();
    }

    @Override
    public void post(GuildMessageReceivedEvent event,
                     Guild guild,
                     User user,
                     Message message,
                     String[] args,
                     String raw){
        ///
    }

    @Override
    public void onPermissionFailure(GuildMessageReceivedEvent event,
                                    Guild guild,
                                    User user,
                                    Message message,
                                    String[] args,
                                    String raw){
        ///
    }
}