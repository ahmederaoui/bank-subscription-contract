package com.adriabt.usersservice.services.impl;

import com.adriabt.usersservice.entities.Agent;
import com.adriabt.usersservice.enums.AppRole;
import com.adriabt.usersservice.exceptions.AgentNotFound;
import com.adriabt.usersservice.exceptions.EmailExist;
import com.adriabt.usersservice.exceptions.MissingInformation;
import com.adriabt.usersservice.repositories.AgentRepository;
import com.adriabt.usersservice.services.IAgentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AgentServiceImpl implements IAgentService {
    private final AgentRepository agentRepository;
    @Override
    public Agent findAgentByEmail(String email) throws AgentNotFound {
        return agentRepository.findAgentByEmail(email).orElseThrow(()->new AgentNotFound(String.format("The agent %s not found",email)));
    }

    @Override
    public Agent createAgent(Agent agent) throws EmailExist, MissingInformation {
        if (agent.getFirstname()!=null && agent.getLastname()!=null && agent.getEmail()!=null&& agent.getPassword()!=null){
            Optional<Agent> newAgent =agentRepository.findAgentByEmail(agent.getEmail());
            if (newAgent.isPresent()) throw new EmailExist(String.format("This email %s is already exist",agent.getEmail()));
            agent.setRole(AppRole.BACKOFFICE);
            agent.setCreationDate(new Date());
            agent.setUpdateDate(new Date());
            return agentRepository.save(agent);
        }else throw new MissingInformation("Some important information is missing");
    }

    @Override
    public Agent updateAgent(Agent agent) throws AgentNotFound, EmailExist {
        Agent updatedAgent = findAgentByEmail(agent.getEmail());
        if (agent.getFirstname()!=null) updatedAgent.setFirstname(agent.getFirstname());
        if (agent.getLastname()!=null) updatedAgent.setLastname(agent.getLastname());
        if (agent.getPhone()!=null) updatedAgent.setPhone(agent.getPhone());
        if (agent.getAgency()!=null) updatedAgent.setAgency(agent.getAgency());
        if (agent.getEmail()!=null) {
            Optional<Agent> newAgent =agentRepository.findAgentByEmail(agent.getEmail());
            if (newAgent.isPresent()) throw new EmailExist(String.format("This email %s is already exist",agent.getEmail()));
            updatedAgent.setEmail(agent.getEmail());
        }
        updatedAgent.setUpdateDate(new Date());
        return agentRepository.save(updatedAgent);
    }
}
