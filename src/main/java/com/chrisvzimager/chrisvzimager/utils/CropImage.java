/**
 * 
 */
package com.chrisvzimager.chrisvzimager.utils;

import java.awt.image.BufferedImage;
import org.imgscalr.Scalr;

/**
 * @author Kristijan Klepač
 * @email kristijan.klepac@gmail.com
 */
public class CropImage {

	public static BufferedImage center(BufferedImage image, int width, int height) {

		// Fit given image if width or height are larger than image width and height
		// this is not good scenario because crop is intended to crop larger image to smaller

		if(width > image.getWidth()) {
			image = Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_WIDTH, width, Scalr.OP_ANTIALIAS);
		}

		if(height > image.getHeight()) {
			image = Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_HEIGHT, height, Scalr.OP_ANTIALIAS);
		}

		// and then process

		int original_width = image.getWidth();
		int original_height = image.getHeight();
		int bound_width = width;
		int bound_height = height;
		int new_width = original_width;
		int new_height = original_height;
		int x = 0, y = 0;

		// We need these if any resizing is going to happen, as the precision factors
		// are no longer sufficiently precise
		double wfactor = (double)original_width/bound_width;
		double hfactor = (double)original_height/bound_height;

		// Calculating the factor between the original dimensions and the wanted dimensions
		// Using precision down to two decimals.
		double precision = 100d;
		double wprecisionfactor = ((int)((wfactor) * precision)) / precision;
		double hprecisionfactor = ((int)((hfactor) * precision)) / precision;


		if (wprecisionfactor == hprecisionfactor) {
			// they are (relatively) equal
			// just use the original image dimensions
			//return this;
		}

		if (wprecisionfactor < hprecisionfactor) {
			// keep the original width
			// calculate the new height from the wfactor
			new_height = (int) (bound_height * wfactor);

			// calculate new coordinate to keep center
			y = Math.abs(original_height - new_height) >> 1; // divide by 2
		} else if (wprecisionfactor > hprecisionfactor) {
			// keep the original height
			// calculate the new width from the hfactor
			new_width = (int) (bound_width * hfactor);

			// calculate new coordinate to keep center
			x = Math.abs(original_width - new_width) >> 1; // divide by 2
		}

		BufferedImage crop = Scalr.crop(image, x, y, new_width, new_height);
		image.flush();// cannot throw
		image = crop;

		return image;

	}

	public static BufferedImage topLeft(BufferedImage image, int width, int height) {

		// Fit given image if width or height are larger than image width and height
		// this is not good scenario because crop is intended to crop larger image to smaller

		if(width > image.getWidth()) {
			image = Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_WIDTH, width, Scalr.OP_ANTIALIAS);
		}

		if(height > image.getHeight()) {
			image = Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_HEIGHT, height, Scalr.OP_ANTIALIAS);
		}

		//top left:
		BufferedImage crop = Scalr.crop(image, width, height);
		image.flush();// cannot throw
		image = crop;

		return image;
	}

	public static BufferedImage topRight(BufferedImage image, int width, int height) {

		// Fit given image if width or height are larger than image width and height
		// this is not good scenario because crop is intended to crop larger image to smaller

		if(width > image.getWidth()) {
			image = Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_WIDTH, width, Scalr.OP_ANTIALIAS);
		}

		if(height > image.getHeight()) {
			image = Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_HEIGHT, height, Scalr.OP_ANTIALIAS);
		}
		//top right:
		BufferedImage crop = Scalr.crop(image,
				image.getWidth() - width, 0,
				width, height);
		image.flush();// cannot throw
		image = crop;

		return image;
	}

	public static BufferedImage bottomLeft(BufferedImage image, int width, int height) {

		// Fit given image if width or height are larger than image width and height
		// this is not good scenario because crop is intended to crop larger image to smaller

		if(width > image.getWidth()) {
			image = Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_WIDTH, width, Scalr.OP_ANTIALIAS);
		}

		if(height > image.getHeight()) {
			image = Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_HEIGHT, height, Scalr.OP_ANTIALIAS);
		}

		//bottom left:
		BufferedImage crop = Scalr.crop(image,
				0, image.getHeight() - height,
				width, height);
		image.flush();// cannot throw
		image = crop;

		return image;
	}

	public static BufferedImage bottomRight(BufferedImage image, int width, int height) {

		// Fit given image if width or height are larger than image width and height
		// this is not good scenario because crop is intended to crop larger image to smaller

		if(width > image.getWidth()) {
			image = Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_WIDTH, width, Scalr.OP_ANTIALIAS);
		}

		if(height > image.getHeight()) {
			image = Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_HEIGHT, height, Scalr.OP_ANTIALIAS);
		}
		//bottom right:
		BufferedImage crop = Scalr.crop(image,
				image.getWidth() - width, image.getHeight() - height,
				width, height);
		image.flush();// cannot throw
		image = crop;

		return image;
	}

}
