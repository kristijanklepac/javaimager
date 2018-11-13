package com.chrisvzimager.chrisvzimager.utils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import org.apache.commons.lang3.tuple.Pair;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;


public class Watermark {
	
	/**
	 * Generate a watermarked image.
	 * 
	 * @param originalImage
	 * @param watermarkImage
	 * @param position
	 * @param watermarkSizeMaxPercentage
	 * @return image with watermark
	 * @throws IOException
	 */
	public static BufferedImage watermark(BufferedImage originalImage,
			BufferedImage watermarkImage, WatermarkPosition position,
			double watermarkSizeMaxPercentage) throws IOException {
 
		int imageWidth = originalImage.getWidth();
		int imageHeight = originalImage.getHeight();
 
		int watermarkWidth = getWatermarkWidth(originalImage, watermarkImage,
				watermarkSizeMaxPercentage);
		int watermarkHeight = getWatermarkHeight(originalImage, watermarkImage,
				watermarkSizeMaxPercentage);
 
		// We create a new image because we want to keep the originalImage
		// object intact and not modify it.
		BufferedImage bufferedImage = new BufferedImage(imageWidth,
				imageHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = (Graphics2D) bufferedImage.getGraphics();
		g2d.drawImage(originalImage, 0, 0, null);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
 
		int x = 0;
		int y = 0;
		if (position != null) {
			switch (position) {
			case TOPLEFT:
				x = 0;
				y = 0;
				break;
			case TOPCENTER:
				x = (imageWidth / 2) - (watermarkWidth / 2);
				y = 0;
				break;
			case TOPRIGHT:
				x = imageWidth - watermarkWidth;
				y = 0;
				break;
 
			case MIDDLELEFT:
				x = 0;
				y = (imageHeight / 2) - (watermarkHeight / 2);
				break;
			case MIDDLECENTER:
				x = (imageWidth / 2) - (watermarkWidth / 2);
				y = (imageHeight / 2) - (watermarkHeight / 2);
				break;
			case MIDDLERIGHT:
				x = imageWidth - watermarkWidth;
				y = (imageHeight / 2) - (watermarkHeight / 2);
				break;
 
			case BOTTOMLEFT:
				x = 0;
				y = imageHeight - watermarkHeight;
				break;
			case BOTTOMCENTER:
				x = (imageWidth / 2) - (watermarkWidth / 2);
				y = imageHeight - watermarkHeight;
				break;
			case BOTTOMRIGHT:
				x = imageWidth - watermarkWidth;
				y = imageHeight - watermarkHeight;
				break;
 
			default:
				break;
			}
		}
 
		g2d.drawImage(Scalr.resize(watermarkImage, Method.ULTRA_QUALITY,
				watermarkWidth, watermarkHeight), x, y, null);
 
		return bufferedImage;
 
	}
	
	
	/**
	 * 
	 * @param originalImage
	 * @param watermarkImage
	 * @param maxPercentage
	 * @return
	 */
	private static Pair<Double, Double> calculateWatermarkDimensions(
			BufferedImage originalImage, BufferedImage watermarkImage,
			double maxPercentage) {
 
		double imageWidth = originalImage.getWidth();
		double imageHeight = originalImage.getHeight();
 
		double maxWatermarkWidth = imageWidth / 100.0 * maxPercentage;
		double maxWatermarkHeight = imageHeight / 100.0 * maxPercentage;
 
		double watermarkWidth = watermarkImage.getWidth();
		double watermarkHeight = watermarkImage.getHeight();
 
		if (watermarkWidth > maxWatermarkWidth) {
			double aspectRatio = watermarkWidth / watermarkHeight;
			watermarkWidth = maxWatermarkWidth;
			watermarkHeight = watermarkWidth / aspectRatio;
		}
 
		if (watermarkHeight > maxWatermarkHeight) {
			double aspectRatio = watermarkWidth / watermarkHeight;
			watermarkHeight = maxWatermarkHeight;
			watermarkWidth = watermarkHeight / aspectRatio;
		}
 
		return Pair.of(watermarkWidth, watermarkHeight);
	}
 
	/**
	 * 
	 * @param originalImage
	 * @param watermarkImage
	 * @param maxPercentage
	 * @return
	 */
	public static int getWatermarkWidth(BufferedImage originalImage,
			BufferedImage watermarkImage, double maxPercentage) {
 
		return calculateWatermarkDimensions(originalImage, watermarkImage,
				maxPercentage).getLeft().intValue();
 
	}
 
	/**
	 * 
	 * @param originalImage
	 * @param watermarkImage
	 * @param maxPercentage
	 * @return
	 */
	public static int getWatermarkHeight(BufferedImage originalImage,
			BufferedImage watermarkImage, double maxPercentage) {
 
		return calculateWatermarkDimensions(originalImage, watermarkImage,
				maxPercentage).getRight().intValue();
 
	}
}


