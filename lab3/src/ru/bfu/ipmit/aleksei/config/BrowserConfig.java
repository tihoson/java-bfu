package ru.bfu.ipmit.aleksei.config;

import ru.bfu.ipmit.aleksei.Subscription;

public class BrowserConfig {
    private Subscription subscription;

    public BrowserConfig(Subscription subscription) {
        this.subscription = subscription;
    }

    public BrowserConfig() {
        this(Subscription.BASE);
    }

    public Subscription getSubscription() {
        return subscription;
    }
}
