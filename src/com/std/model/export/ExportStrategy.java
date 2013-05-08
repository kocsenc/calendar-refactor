package com.std.model.export;

import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * Author:      Grant Kurtz
 *
 * Defines the context for exporting the current calendar to a file on the
 * disk.
 */
public interface ExportStrategy{
	public void export(List<Serializable> objects, File file);
}
