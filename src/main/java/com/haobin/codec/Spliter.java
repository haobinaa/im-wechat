/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.codec;

import com.haobin.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 拆包校验魔数
 * 继承自 LengthFieldBasedFrameDecoder 拆包器，用于自定义协议包含长度域的拆包解析
 * @author HaoBin
 * @version $Id: Spliter.java, v0.1 2019/2/19 10:20 HaoBin 
 */
public class Spliter extends LengthFieldBasedFrameDecoder {

    /**
     * 4字节魔数 + 1字节版本号 + 1字节序列化算法 + 1字节指令 + 4字节长度 + 数据
     * 长度域相对于整个协议偏移量为 7 字节
     */
    private static final int LENGTH_FIELD_OFFSET = 7;
    // 长度域长度为4字节
    private static final int LENGTH_FIELD_LENGTH = 4;

    public Spliter() {
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        // 如果前4个字节(int)不是魔数，则并非规定通讯
        if (in.getInt(in.readerIndex()) != PacketCodeC.MAGIC_NUMBER) {
            ctx.channel().close();
            return null;
        }
        return super.decode(ctx, in);
    }
}