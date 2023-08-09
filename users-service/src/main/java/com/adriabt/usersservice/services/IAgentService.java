package com.adriabt.usersservice.services;

import com.adriabt.usersservice.dtos.MfaTokenData;
import com.adriabt.usersservice.entities.Agent;
import com.adriabt.usersservice.exceptions.AgentNotFound;
import com.adriabt.usersservice.exceptions.EmailExist;
import com.adriabt.usersservice.exceptions.MissingInformation;
import dev.samstevens.totp.exceptions.QrGenerationException;

public interface IAgentService {
    Agent findAgentByEmail(String email) throws AgentNotFound;
    MfaTokenData createAgent(Agent agent) throws EmailExist, MissingInformation, QrGenerationException;
    Agent updateAgent(Agent agent) throws AgentNotFound, EmailExist;
}
