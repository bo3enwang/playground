package com.bow3n.learn.netty.code;

import io.netty.channel.CombinedChannelDuplexHandler;

/**
 * @author bowen
 */
public class CombinedByteCharCodec extends CombinedChannelDuplexHandler<ByteToCharDecoder, CharToByteEncoder> {
    public CombinedByteCharCodec() {
        super(new ByteToCharDecoder(), new CharToByteEncoder());
    }
}
