/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.pool3.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.pool3.UsageTracking;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * cglib is unmaintained and does not work well (or possibly at all?) in newer JDKs, particularly JDK17+; see https://github.com/cglib/cglib
 * <p>
 * CGLib implementation of the proxy handler.
 * </p>
 *
 * @param <T> type of the wrapped pooled object
 * @since 2.0
 */
final class CglibProxyHandler<T> extends BaseProxyHandler<T> implements MethodInterceptor {

    /**
     * Constructs a CGLib proxy instance.
     *
     * @param pooledObject                    The object to wrap
     * @param usageTracking                   The instance, if any (usually the object pool) to be provided with usage tracking information for this wrapped
     *                                        object
     * @param unwrapInvocationTargetException True to make the proxy throw {@link InvocationTargetException#getTargetException()} instead of
     *                                        {@link InvocationTargetException}
     */
    CglibProxyHandler(final T pooledObject, final UsageTracking<T> usageTracking, final boolean unwrapInvocationTargetException) {
        super(pooledObject, usageTracking, unwrapInvocationTargetException);
    }

    @Override
    public Object intercept(final Object object, final Method method, final Object[] args, final MethodProxy methodProxy) throws Throwable {
        return doInvoke(method, args);
    }
}
