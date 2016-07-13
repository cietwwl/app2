package com.chuangyou.xianni.netty.handler.server;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpUtil;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.http.HttpResponseFactory;

public class HttpServerInboundHandler extends ChannelInboundHandlerAdapter {

	private HttpRequest			request;
	private String				rootPath	= "";							// 请求连接端口
	private Map<String, String>	paramMap	= new HashMap<String, String>();

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
		String clientIP = insocket.getAddress().getHostAddress();
		boolean isAdmin = isInner(clientIP);
		if (!isAdmin) {
			Log.info("HTTP访问IP" + clientIP);// 此处需做管理后台验证(IP或者秘钥)
		}
		if (msg instanceof HttpRequest) {
			request = (HttpRequest) msg;
			decodeUri(request.uri());
		}

		if (msg instanceof HttpContent) {
			// 解析post请求参数
			HttpContent content = (HttpContent) msg;
			ByteBuf buf = content.content();
			String params = buf.toString(io.netty.util.CharsetUtil.UTF_8);
			buf.release();
			decodeParams(params);
			Log.info("http request:" + rootPath + params);
			// 回复响应内容
			String json = HttpResponseFactory.getResult(rootPath, paramMap, isAdmin);

			// 响应
			FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK,
					Unpooled.wrappedBuffer(json.getBytes("UTF-8")));
			response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
			response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
			if (HttpUtil.isKeepAlive(request)) {
				response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
			}
			ctx.write(response);
			ctx.flush();
			paramMap.clear();
		}
	}

	/**
	 * 解析请求参数
	 */
	public void decodeParams(String params) {
		try {
			if (params == null || params.equals("")) {
				return;
			}
			params = URLDecoder.decode(params, "UTF-8");
			String[] splitParams = params.split("&");
			for (String param : splitParams) {
				int index = param.indexOf("=");
				if (index == -1 || index >= param.length() - 1) {
					continue;
				}
				String[] key_value = new String[2];
				key_value[0] = param.substring(0, index);
				key_value[1] = param.substring(index + 1, param.length());
				paramMap.put(key_value[0], key_value[1]);

			}
		} catch (Exception e) {
			Log.error("HTTP REQUEST ERROR, params : " + params, e);
		}
	}

	/**
	 * 解析rootPath
	 * 
	 * @param uri
	 */
	public void decodeUri(String uri) {
		try {
			if (uri == null || uri.equals("")) {
				return;
			}
			if (!uri.startsWith("/")) {
				return;
			}
			int paramIndex = uri.indexOf("?");
			if (paramIndex == -1) {
				rootPath = uri.substring(1);
				return;
			}
			rootPath = uri.substring(1, paramIndex);
			String params = uri.substring(paramIndex + 1);
			decodeParams(params);

		} catch (Exception e) {
			Log.error("HTTP REQUEST ERROR, uri : " + uri, e);
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		Log.error(cause.getMessage());
		ctx.close();
	}

	private static final String IP_REGX = "(127\\.0\\.0\\.\\d+)|(localhost)|(10\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})|"
			+ "(172\\.((1[6-9])|(2\\d)|(3[01]))\\.\\d{1,3}\\.\\d{1,3})|(192\\.168\\.\\d{1,3}\\.\\d{1,3})";

	// 访问IP限制
	private static boolean isInner(String ip) {
		if (ip == null) {
			return false;
		}
		return ip.matches(IP_REGX);
	}
}