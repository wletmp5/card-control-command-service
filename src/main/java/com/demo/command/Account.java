package com.demo.command;

import com.demo.coreapi.AccountLockedEvent;
import com.demo.coreapi.CardControlStartedEvent;
import com.demo.coreapi.LockAccountCommand;
import com.demo.coreapi.StartCardControlCommand;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Data
@NoArgsConstructor
@Aggregate
public class Account {

    @AggregateIdentifier
    private String accountId;
    private boolean lock;

    @CommandHandler
    public Account(StartCardControlCommand cmd){
        apply(new CardControlStartedEvent(cmd.getAccountId()));
    }

    @CommandHandler
    public void handle(LockAccountCommand cmd){
        apply(new AccountLockedEvent(cmd.getAccountId()));
    }

    @EventSourcingHandler
    public void on(AccountLockedEvent evt){
        this.lock = true;
    }

    @EventSourcingHandler
    public void on(CardControlStartedEvent evt){
        this.accountId = evt.getAccountId();
        this.lock = true;
    }


}
