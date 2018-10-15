/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.cache.hibernate;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.access.RegionAccessStrategy;
import org.hibernate.cache.spi.access.SoftLock;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.jetbrains.annotations.Nullable;

/**
 * Implementation of L2 cache access strategy delegating to {@link HibernateAccessStrategyAdapter}.
 */
public abstract class HibernateAbstractRegionAccessStrategy implements RegionAccessStrategy {
    /** */
    protected final HibernateAccessStrategyAdapter stgy;

    /**
     * @param stgy Access strategy implementation.
     */
    protected HibernateAbstractRegionAccessStrategy(HibernateAccessStrategyAdapter stgy) {
        this.stgy = stgy;
    }

    @Override
    public Object get(SharedSessionContractImplementor session, Object key, long txTimestamp) throws CacheException {
        return stgy.get(key);
    }

    @Override
    public boolean putFromLoad(SharedSessionContractImplementor session, Object key, Object value, long txTimestamp, Object version) throws CacheException {
        stgy.putFromLoad(key, value);
        return true;
    }

    @Override
    public boolean putFromLoad(SharedSessionContractImplementor session, Object key, Object value, long txTimestamp, Object version, boolean minimalPutOverride) throws CacheException {
        stgy.putFromLoad(key, value);
        return true;
    }

    @Override
    public SoftLock lockItem(SharedSessionContractImplementor session, Object key, Object version) throws CacheException {
        stgy.lock(key);
        return null;
    }

    @Override
    public void unlockItem(SharedSessionContractImplementor session, Object key, SoftLock lock) throws CacheException {
        stgy.unlock(key);
    }

    @Override
    public void remove(SharedSessionContractImplementor session, Object key) throws CacheException {
        stgy.remove(key);
    }

    /** {@inheritDoc} */
    @Nullable @Override public SoftLock lockRegion() throws CacheException {
        stgy.lockRegion();

        return null;
    }

    /** {@inheritDoc} */
    @Override public void unlockRegion(SoftLock lock) throws CacheException {
        stgy.unlockRegion();
    }

    /** {@inheritDoc} */
    @Override public void removeAll() throws CacheException {
        stgy.removeAll();
    }

    /** {@inheritDoc} */
    @Override public void evict(Object key) throws CacheException {
        stgy.evict(key);
    }

    /** {@inheritDoc} */
    @Override public void evictAll() throws CacheException {
        stgy.evictAll();
    }

}