package services;

import com.google.inject.Singleton;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Charl on 2015-01-26.
 */
@Singleton
public class AsyncController {

    private volatile ConcurrentHashMap<Long, Instant> updateMap= new ConcurrentHashMap<>();

    public void GameUpdated(long id)
    {
        Instant instant= Instant.now();
        updateMap.put(id,instant);
    }
}
