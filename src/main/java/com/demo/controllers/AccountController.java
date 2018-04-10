package com.demo.controllers;

import com.demo.coreapi.LockAccountCommand;
import com.demo.coreapi.StartCardControlCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

@RestController
public class AccountController {

    private final CommandGateway commandGateway;

    public AccountController(@SuppressWarnings("SpringJavaAutowiringInspection") CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/accounts/{accountId}/start")
    public Future<String> startCardControl(@PathVariable String accountId){
        return commandGateway.send(new StartCardControlCommand(accountId));
    }

    @PostMapping("/accounts/{accountId}/lock")
    public Future<String> lockAccount(@PathVariable String accountId){
        return commandGateway.send(new LockAccountCommand(accountId));
    }

    @GetMapping("/hello")
    public String test(){
        return "Test";
    }
}
