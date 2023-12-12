package com.adriabt.usersservice.services;

import com.adriabt.usersservice.dtos.MfaTokenData;
import com.adriabt.usersservice.entities.Agent;
import com.adriabt.usersservice.exceptions.AgentNotFound;
import com.adriabt.usersservice.exceptions.EmailExist;
import com.adriabt.usersservice.exceptions.MissingInformation;

public interface IAgentService {
    Agent findAgentByEmail(String email) throws AgentNotFound;
    MfaTokenData createAgent(Agent agent) throws EmailExist, MissingInformation;
    Agent updateAgent(Agent agent) throws AgentNotFound, EmailExist;
    MfaTokenData getQrCode(String email) throws AgentNotFound;
}
