package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever {
    Storage storage;
    LRUCache cache = new LRUCache(5);
    OriginalRetriever originalRetriever;


    public CachingProxyRetriever(Storage storage) {
        this.storage = storage;
        this.originalRetriever = new OriginalRetriever(storage);
    }

    @Override
    public Object retrieve(long id) {
        Object value;
        if(cache.find(id) != null) {
            value = cache.find(id);
        } else {
            value = originalRetriever.retrieve(id);
            cache.set(id, value);
        }
        return value;
    }
}
