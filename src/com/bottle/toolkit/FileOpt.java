package com.bottle.toolkit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Java文件操作工具，支持文件、文件夹的<strong>复制、删除、移动</strong>操作。 <br>
 * 同时，支持<strong>文件的读写</strong>。
 * 
 * @Author Bottle
 * 
 * @Date 2013-11-21 上午9:13:48
 *
 */
public class FileOpt
{
	/**
	 * 删除文件（夹） 
	 * 
	 * @param fileName 文件（夹） 
	 * @return 删除成功返回true，否则，返回false
	 */
	public static boolean delete(String fileName)
	{
		File file = new File(fileName);
		if (!file.exists())
		{
			System.out.println("文件他妈的不存在啊，删个毛线！");
			return false;
		}
		if (file.isFile())
		{
			file.delete();
		} else
		{
			for (File f : file.listFiles())
			{
				delete(f.getPath());
			}
			file.delete();
		}
		return true;
	}

	/** 
	 * 复制文件（夹）到一个目标文件夹 
	 * 
	 * @param resFileName 源文件（夹） 
	 * @param objFolderFileName 目标文件夹 
	 * @return 复制成功返回true，否则，返回false
	 */
	public static boolean copy(String resFileName, String objFolderFileName)
	{
		File resFile = new File(resFileName);
		File objFolderFile = new File(objFolderFileName);
		if (!resFile.exists())
			return false;
		if (!objFolderFile.exists())
			objFolderFile.mkdirs();
		if (resFile.isFile())
		{
			try
			{
				File objFile = new File(objFolderFile.getPath() + File.separator + resFile.getName());
				//复制文件到目标地 
				InputStream ins = new FileInputStream(resFile);
				FileOutputStream outs = new FileOutputStream(objFile);
				byte[] buffer = new byte[1024 * 512];
				int length;
				while ((length = ins.read(buffer)) != -1)
				{
					outs.write(buffer, 0, length);
				}
				ins.close();
				outs.flush();
				outs.close();
			} catch (Exception e)
			{
				e.printStackTrace();
			}

		} else
		{
			String objFolder = objFolderFile.getPath() + File.separator + resFile.getName();
			File _objFolderFile = new File(objFolder);
			_objFolderFile.mkdirs();
			for (File sf : resFile.listFiles())
			{
				copy(sf.getPath(), objFolder);
			}
		}
		return true;
	}

	/** 
	 * 将文件（夹）移动到令一个文件夹 <br>
	 * 原理：先复制一份到目标文件夹，然后删除原来的文件
	 * 
	 * @param resFileName 源文件（夹） 
	 * @param objFolderFileName 目标文件夹 
	 * @return 移动成功返回true，否则，返回false
	 */
	public static boolean move(String resFileName, String objFolderFileName)
	{
		if (copy(resFileName, objFolderFileName) && delete(resFileName))
		{
			return true;
		}
		return false;

	}

	/** 
	* 向文件中追加内容 
	* 
	* @param fileName 文件名（带路径）
	* @param content 写入的内容
	*/
	public static void append(String fileName, String content)
	{
		try
		{
			//打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件  
			FileWriter writer = new FileWriter(fileName, true);
			writer.write(content);
			writer.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 以字符为单位读取文件，常用于读文本，数字等类型的文件 
	 * 
	 * @param fileName 文件名 （带路径）
	 * @return 文件内容字符串
	 */
	public static String readFileByChars(String fileName)
	{
		File file = new File(fileName);
		Reader reader = null;
		String result = "";
		try
		{
			// 一次读一个字符  
			reader = new InputStreamReader(new FileInputStream(file));
			int tempchar;
			while ((tempchar = reader.read()) != -1)
			{
				result += (char) tempchar;
			}
			reader.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 以行为单位读取文件，常用于读面向<strong>行</strong>的格式化文件 
	 * 
	 * @param fileName 文件名 （带路径）
	 * @return 文件内容的list（每行内容是一个元素）
	 */
	public static List<String> readFileByLines(String fileName)
	{
		File file = new File(fileName);
		BufferedReader reader = null;
		List<String> resultList = new ArrayList<>();
		try
		{
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			//一次读入一行，直到读入null为文件结束  
			while ((tempString = reader.readLine()) != null)
			{
				resultList.add(tempString);
			}
			reader.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (reader != null)
			{
				try
				{
					reader.close();
				} catch (IOException e1)
				{
				}
			}
		}
		return resultList;
	}

}
