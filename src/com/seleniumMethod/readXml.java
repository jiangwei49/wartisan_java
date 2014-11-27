package com.seleniumMethod;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class readXml {
	/**
	 * @param String
	 *            fileName fileName是要读取的xml文件名称。 读取xml文件中的内容，对页面的定位和定位信息类型做出判断。
	 *            属于readXml类
	 */

	/**
	 * @param String
	 *            fileName fileName是要读取的xml文件名称。 读取xml文件中的内容，对页面的定位和定位信息类型做出判断。
	 *            属于readXml类
	 */
	public Map<String, String> readXml(String fileName, String elementRoot) {

		Map<String, String> dbInformation = new HashMap<String, String>();
		try {
			String path = fileName; // 设置路径+文件名(根据个人文件的路径而定)

			File file = new File(path);
			if (! file.exists()) {
				System.err.println("file not exist!");
				System.exit(1);
			}
			SAXReader reader = new SAXReader(); // 使用SAX方式解析XML
			Document doc = reader.read(file); // 将XML文件解析成文档对象
			Element root = doc.getRootElement(); // 取得根节点

			Iterator it = root.elementIterator(elementRoot); // 从第二级节点开始遍历
			int i = 1;
			while (it.hasNext()) {
				Element txtElement = (Element) it.next(); // 获取第二级节点
				List list = (List) txtElement.elements(); // 将第二级节点的子节点放入到list中
				Iterator lit = list.iterator(); // 开始遍历第三级节点

				while (lit.hasNext()) {
					Element property = (Element) lit.next(); // 获取第三级节点
					String key = property.getName();
					String value = property.attributeValue(property.getName()).toString();
					dbInformation.put(key, value);
					i++;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dbInformation;
	}

}
