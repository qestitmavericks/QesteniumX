/*
 * Copyright (c) 2023 QESTIT
 * QesteniumX
 */

package com.qestit.helpers;

import com.qestit.constants.FrameworkConstants;
import com.qestit.utils.LogUtils;
import org.apache.commons.io.FileUtils;
import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.monte.media.AudioFormatKeys.EncodingKey;
import static org.monte.media.AudioFormatKeys.FrameRateKey;
import static org.monte.media.AudioFormatKeys.KeyFrameIntervalKey;
import static org.monte.media.AudioFormatKeys.MediaTypeKey;
import static org.monte.media.AudioFormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.MIME_AVI;
import static org.monte.media.VideoFormatKeys.*;

public class CaptureHelpers extends ScreenRecorder {

	// ------Record with Monte Media library---------
	private static ScreenRecorder screenRecorder;
	private static String name;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");

	// Construction function
	public CaptureHelpers(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat, Format screenFormat,
			Format mouseFormat, Format audioFormat, File movieFolder, String name) throws IOException, AWTException {
		super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
		this.name = name;
	}

	// This function is required to override the custom function in the pre-written
	// library
	@Override
	protected File createMovieFile(Format fileFormat) throws IOException {

		if (!movieFolder.exists()) {
			movieFolder.mkdirs();
		} else if (!movieFolder.isDirectory()) {
			throw new IOException(movieFolder + " is not a directory.");
		}
		return new File(movieFolder,
				name + "_" + dateFormat.format(new Date()) + "." + Registry.getInstance().getExtension(fileFormat));
	}

	// Start record video function
	public static void startRecord(String methodName) {
		// Create a folder to save video files into
		File file = new File("./" + FrameworkConstants.EXPORT_VIDEO_PATH + "/" + methodName + "/");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;

		System.out.println("width" + width);
		System.out.println("height" + height);

		Rectangle captureSize = new Rectangle(0, 0, width, height);

		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();
		try {
			screenRecorder = new CaptureHelpers(gc, null,
					new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
					new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
							CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
							Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
					new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
					null, file, methodName);

			screenRecorder.start();
		} catch (IOException | AWTException e) {
			e.printStackTrace();
		}
	}

	// Stop record video
	public static void stopRecord() {
		try {
			screenRecorder.stop();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String captureScreenshot(WebDriver driver, String screenName) {
		String screenshotPath = null;
		try {
			String path = Helpers.getCurrentDir() + FrameworkConstants.EXPORT_CAPTURE_PATH;
			File file = new File(path);
			if (!file.exists()) {
				LogUtils.info("No Folder: " + path);
				file.mkdir();
				LogUtils.info("Folder created: " + file);
			}

			LogUtils.info("Driver for Screenshot: " + driver);
			// Create reference of TakesScreenshot
			TakesScreenshot ts = (TakesScreenshot) driver;
			// Call the capture screenshot function - getScreenshotAs
			File source = ts.getScreenshotAs(OutputType.FILE);
			screenshotPath = path + "/" + screenName + "_" + dateFormat.format(new Date()) + ".png";
			// result.getName() gets the name of the test case and assigns it to the
			// screenshot file name
			FileUtils.copyFile(source, new File(screenshotPath));
			LogUtils.info("Screenshot taken: " + screenName);
			LogUtils.info("Screenshot taken current URL: " + driver.getCurrentUrl());
		} catch (Exception e) {
			LogUtils.error("Exception while taking screenshot: " + e.getMessage());
		}
		return screenshotPath;
	}

	public static File getScreenshotFile(String screenshotName) {
		Rectangle allScreenBounds = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		String dateName = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss.SSS").format(new Date());
		BufferedImage image = null;
		try {
			image = new Robot().createScreenCapture(allScreenBounds);
		} catch (AWTException e) {
			throw new RuntimeException(e);
		}

		String path = Helpers.getCurrentDir() + FrameworkConstants.EXTENT_REPORT_FOLDER + File.separator + "images";
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdir();
			LogUtils.info("Folder created: " + folder);
		}

		String filePath = path + File.separator + screenshotName + dateName + ".png";
		File file = new File(filePath);
		try {
			ImageIO.write(image, "PNG", file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return file;
	}

	public static String getScreenshotRelativePath(String screenshotName) {
		Rectangle allScreenBounds = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		String dateName = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss.SSS").format(new Date());
		BufferedImage image = null;
		try {
			image = new Robot().createScreenCapture(allScreenBounds);
		} catch (AWTException e) {
			throw new RuntimeException(e);
		}

		String path = Helpers.getCurrentDir() + FrameworkConstants.EXTENT_REPORT_FOLDER + File.separator + "images";

		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdir();
			LogUtils.info("Folder created: " + folder);
		}

		String filePath = path + File.separator + screenshotName + dateName + ".png";

		File file = new File(filePath);
		try {
			ImageIO.write(image, "PNG", file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		String filePathRelative = FrameworkConstants.EXTENT_REPORT_FOLDER + File.separator + "images" + File.separator
				+ screenshotName + dateName + ".png";
		return filePathRelative;
	}

	public static String getScreenshotAbsolutePath(String screenshotName) {
		Rectangle allScreenBounds = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		String dateName = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss.SSS").format(new Date());
		BufferedImage image = null;
		try {
			image = new Robot().createScreenCapture(allScreenBounds);
		} catch (AWTException e) {
			throw new RuntimeException(e);
		}

		String path = Helpers.getCurrentDir() + FrameworkConstants.EXTENT_REPORT_FOLDER + File.separator + "images";

		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdir();
			LogUtils.info("Folder created: " + folder);
		}

		String filePath = path + File.separator + screenshotName + dateName + ".png";

		File file = new File(filePath);
		try {
			ImageIO.write(image, "PNG", file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return filePath;
	}

}
