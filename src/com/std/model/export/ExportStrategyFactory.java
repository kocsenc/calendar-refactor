package com.std.model.export;

/**
 * Author:      Grant Kurtz
 */
public class ExportStrategyFactory{

	public static final String CSV = "csv";
	public static final String DCAL = "dcal";
	public static final String XML = "xml";

	public ExportStrategy getExportStrategy(String id){
		ExportStrategy exportStrategy = null;

		if(CSV.equalsIgnoreCase(id))
			exportStrategy = new CsvExportStrategy();
		else if(DCAL.equalsIgnoreCase(id))
			exportStrategy = new DCalExportStrategy();
		else if(XML.equalsIgnoreCase(id))
			exportStrategy = new XmlExportStrategy();
		return exportStrategy;
	}
}