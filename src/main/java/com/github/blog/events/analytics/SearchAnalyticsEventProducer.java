package com.github.blog.events.analytics;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class SearchAnalyticsEventProducer 
{
    private final StreamBridge source;

    public SearchAnalyticsEventProducer(StreamBridge source)
    {
        this.source = source;
    }

    public boolean send(SearchAnalyticsEvent event) 
    {
        if (event == null) throw new NullPointerException();
        return this.source.send("blog.search.analytics.ex", MessageBuilder.withPayload(event).build());
    }
}
