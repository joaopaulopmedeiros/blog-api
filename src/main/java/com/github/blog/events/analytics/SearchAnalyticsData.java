package com.github.blog.events.analytics;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SearchAnalyticsData 
{
    private List<Integer> postIds;
}
