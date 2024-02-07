package com.github.blog.events.analytics;

import com.github.blog.events.BaseEvent;

public class SearchAnalyticsEvent extends BaseEvent<SearchAnalyticsData>
{
    public SearchAnalyticsEvent(SearchAnalyticsData data) 
    {
        this.type = "search";
        this.data = data;
    }
}
