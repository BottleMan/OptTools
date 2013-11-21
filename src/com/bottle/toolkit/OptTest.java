package com.bottle.toolkit;

import java.util.List;

public class OptTest
{
	public static void main(String[] args)
	{
		//测试文件操作
		fileOptTest();
	}

	private static void fileOptTest()
	{
		String fileName = "files/newTemp.txt";
		String fileName2 = "files2/newTemp.txt";

		//测试追加内容
//		FileOpt.append(fileName, "哈哈哈哈哈");
		
		//测试按字符读取文件
//		System.out.println(FileOpt.readFileByChars(fileName));
		
		//测试按行读取文件
//		List<String> strings = FileOpt.readFileByLines(fileName);
//		for (int i = 0; i < strings.size(); i++)
//		{
//			System.out.println(strings.get(i));
//		}

		//测试复制文件
//		FileOpt.copy(fileName, "files2");
		
		//测试移动文件
//		FileOpt.move(fileName, "files3");
		
		//测试删除文件
//		FileOpt.delete(fileName2);
	}
}
