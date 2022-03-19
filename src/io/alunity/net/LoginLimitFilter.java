package io.alunity.net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.alunity.Configuration;
import io.alunity.Server;
import io.alunity.net.login.LoginRequestLimit;
import io.alunity.net.login.RS2LoginProtocol;
import io.alunity.util.logging.global.LoginRequestLog;

public class LoginLimitFilter extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        ChannelHandler.incrementActiveConnections();
        String ipAddress = RS2LoginProtocol.getIP(ctx);
        if (LoginRequestLimit.timedOutInvalidLoginRequest(ipAddress)) {
            ctx.close();
            Server.getLogging().batchWrite(new LoginRequestLog(ipAddress, "Invalid login timeout deny"));
            return;
        }

        if (!Configuration.DISABLE_CONNECTION_REQUEST_LIMIT) {
            if (LoginRequestLimit.rejectConnectionRequest()) {
                Server.getLogging().batchWrite(new LoginRequestLog(ipAddress, "Rate limit deny"));
                ctx.close();
                return;
            }

            LoginRequestLimit.addRequest();
        }
    }

}
