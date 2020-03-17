package com.smigic.sensorsReader.util;

import java.nio.ByteBuffer;

public class BytesUtil {

	public static byte[] concatenateByteArrays(byte[] arrayOne, byte[] arrayTwo) {
		byte[] concatenatedArrays = new byte[arrayOne.length + arrayTwo.length];
		System.arraycopy(arrayOne, 0, concatenatedArrays, 0, arrayOne.length);
		System.arraycopy(arrayTwo, 0, concatenatedArrays, arrayOne.length, arrayTwo.length);
		return concatenatedArrays;
	}

	public static byte[] getNextBytes(ByteBuffer bb, int offset, int nlen) {
		byte[] dst = new byte[nlen];
		int j = 0;
		for (int i = offset; i < offset + nlen; i++)
			dst[j++] = bb.get();
		return dst;
	}

	public static int toInt(byte[] bytes) {
		return ((bytes[0] & 0xFF) << 24) | ((bytes[1] & 0xFF) << 16) | ((bytes[2] & 0xFF) << 8)
				| ((bytes[3] & 0xFF) << 0);
	}

	public static int toLong(byte[] bytes) {
		return ((bytes[0] & 0xFF) << 24) | ((bytes[1] & 0xFF) << 16) | ((bytes[2] & 0xFF) << 8)
				| ((bytes[3] & 0xFF) << 0);
	}
}
