/**
 * All rights reserved. This material is confidential and proprietary to 7ROAD SQ team.
 */
package com.chuangyou.common.util;

import java.awt.Rectangle;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * <pre>
 * 防外挂拼图
 * </pre>
 */
public class ImageUtil {
	private static final int SIZE = 300;
	private static final int LENGTH = SIZE / 2;
	private static List<int[]> pointSet = new ArrayList<int[]>();
	private static Random random = new Random();

	static {
		int[] onePoint = new int[] { 0, 0 };
		int[] twoPoint = new int[] { LENGTH, 0 };
		int[] thrPoint = new int[] { 0, LENGTH };
		int[] forPoint = new int[] { LENGTH, LENGTH };
		pointSet.add(onePoint);
		pointSet.add(twoPoint);
		pointSet.add(thrPoint);
		pointSet.add(forPoint);
	}

	public static String cut(List<byte[]> images, String srcPath, boolean isBlack) {
		FileInputStream fis = null;
		ImageInputStream iis = null;
		try {
			fis = new FileInputStream(srcPath);
			Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName("jpg");
			ImageReader reader = it.next();
			iis = ImageIO.createImageInputStream(fis);
			reader.setInput(iis, true);
			ImageReadParam param = reader.getDefaultReadParam();
			Rectangle rect = new Rectangle(0, 0, LENGTH, LENGTH);
			List<byte[]> temps = new ArrayList<byte[]>();
			for (int i = 0; i < pointSet.size(); i++) {
				rect.x = pointSet.get(i)[0];
				rect.y = pointSet.get(i)[1];
				param.setSourceRegion(rect);
				BufferedImage bi1 = reader.read(0, param);
				if (isBlack) {
					new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null).filter(bi1, bi1);
				}
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				encoder.encode(bi1);
				out.close();
				temps.add(out.toByteArray());
			}
			return sort(images, temps);
		} catch (Exception e) {
			Log.error("截图异常", e);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					Log.error("关闭FileInputStream异常", e);
				}
			}
			if (iis != null) {
				try {
					iis.close();
				} catch (IOException e) {
					Log.error("关闭ImageInputStream异常", e);
				}
			}
		}
		return null;
	}

	private static String sort(List<byte[]> images, List<byte[]> src) {
		List<Integer> indexs = new ArrayList<Integer>();
		for (int i = 1; i <= src.size(); i++) {
			indexs.add(i);
		}
		String siteResult = "";
		for (int i = 0; i < src.size(); i++) {
			int index = random.nextInt(indexs.size());
			int site = indexs.remove(index);
			siteResult += site + ",";
			images.add(src.get(site - 1));
		}
		siteResult = siteResult.substring(0, siteResult.length() - 1);
		if (siteResult.equals("1,2,3,4")) {
			images.clear();
			return sort(images, src);
		}
		return siteResult;
	}
}
