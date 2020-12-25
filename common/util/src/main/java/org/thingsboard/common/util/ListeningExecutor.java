package org.thingsboard.common.util;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

public interface ListeningExecutor extends Executor {

    <T> ListenableFuture<T> executeAsync(Callable<T> task);

    default <T> ListenableFuture<T> submit(Callable<T> task) {
        return executeAsync(task);
    }

}
