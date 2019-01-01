/*
 * This file is part of ComputerCraft - http://www.computercraft.info
 * Copyright Daniel Ratcliffe, 2011-2019. Do not distribute without permission.
 * Send enquiries to dratcliffe@gmail.com
 */

package dan200.computercraft.core.apis.http;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import dan200.computercraft.shared.util.ThreadUtils;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Just a shared object for executing simple HTTP related tasks.
 */
public final class HTTPExecutor
{
    public static final ListeningExecutorService EXECUTOR = MoreExecutors.listeningDecorator( new ThreadPoolExecutor(
        4, Integer.MAX_VALUE,
        60L, TimeUnit.SECONDS,
        new SynchronousQueue<>(),
        ThreadUtils.builder( "HTTP" )
            .setPriority( Thread.MIN_PRIORITY + (Thread.NORM_PRIORITY - Thread.MIN_PRIORITY) / 2 )
            .build()
    ) );

    public static final EventLoopGroup LOOP_GROUP = new NioEventLoopGroup( 4, ThreadUtils.builder( "Netty" )
        .setPriority( Thread.MIN_PRIORITY + (Thread.NORM_PRIORITY - Thread.MIN_PRIORITY) / 2 )
        .build()
    );

    private HTTPExecutor()
    {
    }
}
