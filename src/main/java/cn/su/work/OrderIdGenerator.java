/**
 * ykse.com.cn
 * Copyright (c) 1997-2017 All Rights Reserved.
 */
package cn.su.work;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * @author suyulong
 * @version $Id: OrderIdGenerator.java, v 0.1 2017年8月16日 下午3:12:37 suyulong Exp $
 */
public enum OrderIdGenerator {
    /**
     * instance
     */
    INSTANCE;


    private long workerId;
    private long sequence = 0L;
    private long sequenceBits = 2L;
    private long workerIdShift = 2L;
    private long sequenceMask = -1L ^ (-1L << sequenceBits);
    private long lastTimestamp = -1L;

    OrderIdGenerator() {
        workerId = 0xFFFFFFFF & getWorkerId();
    }

    public synchronized String nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            //throw new BusinessException(ResultCodeEnum.SYSTEM_TIME_ERR);
        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;

        long suffix = (workerId << workerIdShift) | sequence;

        String datePrefix = DateFormatUtils.format(timestamp, "yyMMddHHmmssSSS");
        return datePrefix + String.valueOf(suffix);
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 支持ipv4
     *
     * @return
     */
    private long getWorkerId() {
        long worker = 0L;
        try {
            InetAddress ip = InetAddress.getLocalHost();
            byte[] ipByte = ip.getAddress();
            for (int i = 0; i < ipByte.length && i < 4; i++) {
                worker |= (ipByte[i] & 0xFF) << (3 - i) * 8;
            }
        } catch (UnknownHostException ex) {
            //LoggerUtil.error(ex, logger, ex.getMessage());
            //throw new BusinessException(ResultCodeEnum.SYSTEM_IP_ERR);
        }
        return worker;
    }


    public String genId(){
        StringBuilder sb = new StringBuilder();
        DecimalFormat decimalFormat = new DecimalFormat("000");
        sb.append(System.currentTimeMillis())
            .append(decimalFormat.format(ThreadLocalRandom.current().nextInt(1000)));
        return sb.toString();
    }
}
