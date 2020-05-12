package com.haobin.netty.buffer;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @Author HaoBin
 * @Create 2020/5/9 11:01
 * @Description: 直接内存
 **/
public class DirectByteBufferTest {

    @Test
    public void allocate() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        ByteBuffer directBuffer = ByteBuffer.allocateDirect(1024);
    }
}
