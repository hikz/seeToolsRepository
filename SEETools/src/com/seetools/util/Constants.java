package com.seetools.util;

public final class Constants {

	private Constants() {
		throw new AssertionError();
	}

	public final class File {
		
		private File() {
			throw new AssertionError();
		}
		
		public static final String FILE_DOWNLOAD_CONTENT_TYPE = "application/vnd.ms-excel";
		public static final String FILE_DOWNLOAD_HEADER_NAME = "Content-Disposition";
		public static final String FILE_DOWNLOAD_HEADER_ATTACHMENT_VALUE = "attachment";
		public static final int DEFAULT_BUFFER_SIZE = 10240;
		public static final String TOOL_INPUT_FILE_NAMES = "tool_input_file_names.properties";

	}
	
	public final class Chart {
		
		private Chart() {
			throw new AssertionError();
		}
		
		public static final String TOOLS_CHART = "tools_chart.properties";
		
	}
	
	public final class Message {
		
		private Message() {
			throw new AssertionError();
		}
		
		public static final String MESSAGE = "messaging.properties";
		
	}
}
