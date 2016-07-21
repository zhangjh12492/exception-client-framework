package com.wfj.exception.client.file;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MessageErrFile {
	private static Logger log = Logger.getLogger(MessageErrFile.class.getName());

	public static String loadFileByChar(String path) {
		StringBuffer sb = new StringBuffer();
		File file = new File(path);
		Reader read = null;
		try {
			log.info("开始读取文件...");
			read = new InputStreamReader(new FileInputStream(file));
			int tempchar;
			while ((tempchar = read.read()) != -1) {
				if ((char) tempchar != '\r') {
					sb.append((char) tempchar);
				}
			}
			log.info("读取文件结束...");
		} catch (IOException e) {
			log.error("读取文件失败," + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				read.close();
			} catch (IOException e) {
				log.error("关闭文件流失败," + e.getMessage());
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public static void writeFile(String fileName, String content, boolean append) {
		FileWriter writer=null;
		try {
			log.info("开始写入文件...");
			writer= new FileWriter(fileName, append);
			writer.write(content);
			log.info("写入文件结束...");
		} catch (IOException e) {
			log.error("写入文件失败," + e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				writer.close();
			} catch (IOException e) {
				log.error("关闭文件流失败");
			}
		}
	}

	public static List<File> readAllFiles(String folder) {
		File file = new File(folder);
		File[] folders = file.listFiles();
		List<File> files = new ArrayList<File>();
		for (int i = 0; i < folders.length; i++) {
			if ((folders[i].exists()) && (folders[i].isFile()) && (folders[i].getName().trim().toLowerCase().endsWith("log"))) {
				files.add(folders[i]);
			}
		}
		return files;
	}

	public static void createdFolder(String path) {
		try {
			File file = new File(path);

			if (!file.exists())
				file.mkdirs();
			else {
				if(!file.isDirectory()){
					file.delete();
					file.mkdirs();
				}
			}
		} catch (Exception e) {
			log.error("创建文件夹失败," + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void createdFile(String fileName) {
		try {
			File file = new File(fileName);
			if (!file.exists())
				try {
					file.createNewFile();
				} catch (IOException e) {
                    createdFolder(file.getParentFile().getPath());
                    createdFile(fileName);
					e.printStackTrace();
				}
		} catch (Exception e) {
			log.error("创建文件失败," + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void delFile(String fileName) {
		try {
			File file = new File(fileName);
			if (file.exists())
				file.delete();
		} catch (Exception e) {
			log.error("删除文件失败," + e.getMessage());
			e.printStackTrace();
		}
	}
}

/* Location:           E:\EclipseEE\test_project\exception-client-framework\target\classes\
 * Qualified Name:     com.wfj.exception.file.MessageErrFile
 * JD-Core Version:    0.6.0
 */