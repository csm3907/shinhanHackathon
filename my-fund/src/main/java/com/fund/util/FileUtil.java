package com.fund.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

public class FileUtil {

	public static String getFileContent(String path) throws IOException {
		ClassPathResource cPathResource = new ClassPathResource(path);
		byte[] byteArr = FileCopyUtils.copyToByteArray(cPathResource.getInputStream());
		return new String(byteArr, StandardCharsets.UTF_8);
	}
}
