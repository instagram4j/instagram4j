/**
 * Copyright (C) 2016 Bruno Candido Volpato da Cunha (brunocvcunha@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.brunocvcunha.instagram4j.util;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;

import org.apache.http.Header;
import org.apache.http.HttpMessage;

import lombok.extern.log4j.Log4j;

/**
 * Generic utils
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
@Log4j
public class InstagramGenericUtil {

	/**
	 * Generate UUID
	 * 
	 * @param dash If needs to keep dash
	 * @return UUID
	 */
	public static String generateUuid(boolean dash) {
		String uuid = UUID.randomUUID().toString();

		if (dash) {
			return uuid;
		}

		return uuid.replaceAll("-", "");
	}

	/**
	 * Gets image dimensions for given file
	 * 
	 * @param imgFile image file
	 * @return dimensions of image
	 * @throws IOException if the file is not a known image
	 */
	public static Dimension getImageDimension(File imgFile) throws IOException {
		int pos = imgFile.getName().lastIndexOf(".");
		if (pos == -1)
			throw new IOException("No extension for file: " + imgFile.getAbsolutePath());
		String suffix = imgFile.getName().substring(pos + 1);
		Iterator<ImageReader> iter = ImageIO.getImageReadersBySuffix(suffix);
		while (iter.hasNext()) {
			ImageReader reader = iter.next();
			try {
				ImageInputStream stream = new FileImageInputStream(imgFile);
				reader.setInput(stream);
				int width = reader.getWidth(reader.getMinIndex());
				int height = reader.getHeight(reader.getMinIndex());
				return new Dimension(width, height);
			} catch (IOException e) {
				log.warn("Error reading: " + imgFile.getAbsolutePath(), e);
			} finally {
				reader.dispose();
			}
		}

		throw new IOException("Not a known image file: " + imgFile.getAbsolutePath());
	}

	public static Optional<String> getFirstHeaderValue(HttpMessage req, String name) {
		Header[] header = req.getHeaders(name);
		
		if(header.length > 0) {
			return Optional.of(header[0].getValue());
		}
		
		return Optional.empty();
	}
}
