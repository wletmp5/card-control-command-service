package com.demo.coreapi

import org.axonframework.commandhandling.TargetAggregateIdentifier

data class LockAccountCommand(@TargetAggregateIdentifier val accountId: String)
data class StartCardControlCommand(@TargetAggregateIdentifier val accountId: String)

data class AccountLockedEvent(@TargetAggregateIdentifier val accountId: String)
data class CardControlStartedEvent(@TargetAggregateIdentifier val accountId: String)