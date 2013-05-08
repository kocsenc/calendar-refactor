package com.std.model.export;

import java.io.*;
import java.util.List;

/**
 * Author:      Grant Kurtz
 */
public class DCalExportStrategy implements ExportStrategy{
	public void export(List<Serializable> objects, File file){
		ObjectOutputStream out = null;
		try{
			out = new ObjectOutputStream(new FileOutputStream(file));
			for(Serializable object : objects){
				if(object instanceof Integer)
					out.writeInt(((Integer) object).intValue());
				else
					out.writeObject(object);
			}
			out.flush();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			if(out != null)
				try{
					out.close();
				}
				catch(IOException e){
					e.printStackTrace();
				}
		}
	}
}
