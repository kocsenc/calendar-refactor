package com.std.model.export;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.*;
import java.util.List;

/**
 * Author:      Grant Kurtz
 */
public class XmlExportStrategy implements ExportStrategy{
	public void export(List<Serializable> objects, File file){
		XStream xstream = new XStream(new StaxDriver());
		StringBuilder xmlDocument = new StringBuilder();
		for(Serializable s : objects){
			xmlDocument.append(xstream.toXML(s));
		}
		ObjectOutputStream out = null;
		try{
			out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeBytes(xmlDocument.toString());
			out.flush();
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
}
